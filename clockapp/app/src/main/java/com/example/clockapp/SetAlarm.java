package com.example.clockapp;

import android.os.Bundle;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.clockapp.databinding.ActivitySetAlarmBinding;

public class SetAlarm extends AppCompatActivity {

        private ActivitySetAlarmBinding binding;
        private AlarmManager alarmManager;
        private Alarm alarm;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                binding = ActivitySetAlarmBinding.inflate(getLayoutInflater());
                setContentView(binding.getRoot());

                alarmManager = new AlarmManager(this);
                int alarmId = getIntent().getIntExtra("alarmId", -1);

                if (alarmId != -1) {
                        alarm = alarmManager.getAlarm(alarmId);
                        if (alarm != null) {
                                binding.timePicker.setCurrentHour(alarm.getHour());
                                binding.timePicker.setCurrentMinute(alarm.getMinute());
                                binding.tvLabel.setText(alarm.getLabel());
                                binding.switchSnooze.setChecked(alarm.isSnoozeEnabled());
                        }
                } else {
                        alarm = new Alarm();
                }

                binding.btnSave.setOnClickListener(v -> saveAlarm());
                binding.btnCancel.setOnClickListener(v -> finish());
        }

        private void saveAlarm() {
                TimePicker timePicker = binding.timePicker;
                alarm.setHour(timePicker.getCurrentHour());
                alarm.setMinute(timePicker.getCurrentMinute());
                alarm.setLabel(binding.tvLabel.getText().toString());
                alarm.setSnoozeEnabled(binding.switchSnooze.isChecked());

                if (alarm.getId() == -1) {
                        alarmManager.addAlarm(alarm);
                        Toast.makeText(this, "Alarm added", Toast.LENGTH_SHORT).show();
                } else {
                        alarmManager.updateAlarm(alarm);
                        Toast.makeText(this, "Alarm updated", Toast.LENGTH_SHORT).show();
                }

                finish();
        }
}
