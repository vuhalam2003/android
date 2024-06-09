package com.example.clockapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.clockapp.databinding.FragmentAlarmBinding;
import java.util.List;

public class AlarmFragment extends Fragment {

    private FragmentAlarmBinding binding;
    private AlarmManager alarmManager;
    private AlarmAdapter alarmAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAlarmBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        alarmManager = new AlarmManager(getContext());
        alarmAdapter = new AlarmAdapter(getContext(), alarmManager);

        binding.recyclerViewAlarms.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewAlarms.setAdapter(alarmAdapter);

        binding.fabAddAlarm.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SetAlarm.class);
            startActivity(intent);
        });

        loadAlarms();
    }

    private void loadAlarms() {
        List<Alarm> alarms = alarmManager.getAllAlarms();
        alarmAdapter.setAlarms(alarms);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
