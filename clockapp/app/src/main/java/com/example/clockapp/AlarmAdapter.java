package com.example.clockapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder> {

    private List<Alarm> alarms = new ArrayList<>();
    private Context context;
    private AlarmManager alarmManager;

    public AlarmAdapter(Context context, AlarmManager alarmManager) {
        this.context = context;
        this.alarmManager = alarmManager;
    }

    @NonNull
    @Override
    public AlarmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alarm_item, parent, false);
        return new AlarmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmViewHolder holder, int position) {
        Alarm alarm = alarms.get(position);
        holder.bind(alarm);
    }

    @Override
    public int getItemCount() {
        return alarms.size();
    }

    public void setAlarms(List<Alarm> alarms) {
        this.alarms = alarms;
        notifyDataSetChanged();
    }

    class AlarmViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTime;
        private TextView tvLabel;
        private Switch switchOnOff;
        private ImageButton btnEdit;
        private ImageButton btnDelete;

        public AlarmViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvLabel = itemView.findViewById(R.id.tvLabel);
            switchOnOff = itemView.findViewById(R.id.switchOnOff);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }

        public void bind(final Alarm alarm) {
            tvTime.setText(String.format("%02d:%02d", alarm.getHour(), alarm.getMinute()));
            tvLabel.setText(alarm.getLabel());
            switchOnOff.setChecked(alarm.isEnabled());

            btnEdit.setOnClickListener(v -> {
                Intent intent = new Intent(context, SetAlarm.class);
                intent.putExtra("alarmId", alarm.getId()); // Pass alarm ID to SetAlarm activity
                context.startActivity(intent);
            });

            btnDelete.setOnClickListener(v -> {
                alarmManager.deleteAlarm(alarm.getId());
                alarms.remove(getAdapterPosition());
                notifyItemRemoved(getAdapterPosition());
                Toast.makeText(context, "Alarm deleted", Toast.LENGTH_SHORT).show();
            });

            switchOnOff.setOnCheckedChangeListener((buttonView, isChecked) -> {
                alarm.setEnabled(isChecked);
                alarmManager.updateAlarm(alarm);
            });
        }
    }
}
