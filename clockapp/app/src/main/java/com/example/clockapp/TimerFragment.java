package com.example.clockapp;import android.os.Bundle;


import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TimerFragment extends Fragment {

    private Button btnStartTimer;
    private Button btnCancelTimer;
    private Button btnPauseTimer;
    private NumberPicker npHours;
    private NumberPicker npMinutes;
    private NumberPicker npSeconds;
    private TextView tvCountdown;

    private CountDownTimer countDownTimer;
    private long totalTimeInMillis;
    private boolean isPaused = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timer, container, false);

        btnStartTimer = view.findViewById(R.id.btnStartTimer);
        btnCancelTimer = view.findViewById(R.id.btnCancelTimer);
        btnPauseTimer = view.findViewById(R.id.btnPauseTimer);
        npHours = view.findViewById(R.id.npHours);
        npMinutes = view.findViewById(R.id.npMinutes);
        npSeconds = view.findViewById(R.id.npSeconds);
        tvCountdown = view.findViewById(R.id.tvCountdown);

        npHours.setMinValue(0);
        npHours.setMaxValue(23);

        npMinutes.setMinValue(0);
        npMinutes.setMaxValue(59);

        npSeconds.setMinValue(0);
        npSeconds.setMaxValue(59);

        btnStartTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });

        btnCancelTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelTimer();
            }
        });

        btnPauseTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseTimer();
            }
        });

        return view;
    }

    private void startTimer() {
        int totalSeconds = (npHours.getValue() * 3600) + (npMinutes.getValue() * 60) + npSeconds.getValue();
        totalTimeInMillis = totalSeconds * 1000;

        countDownTimer = new CountDownTimer(totalTimeInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                totalTimeInMillis = millisUntilFinished;
                updateTimerUI();
            }

            @Override
            public void onFinish() {
                // Timer finished, handle UI update or any other actions
                btnStartTimer.setEnabled(true);
                btnCancelTimer.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                tvCountdown.setText("00:00:00");
                npHours.setVisibility(View.VISIBLE);
                npMinutes.setVisibility(View.VISIBLE);
                npSeconds.setVisibility(View.VISIBLE);
                tvCountdown.setVisibility(View.GONE);
            }
        }.start();

        btnStartTimer.setEnabled(false);
        btnCancelTimer.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
        npHours.setVisibility(View.GONE);
        npMinutes.setVisibility(View.GONE);
        npSeconds.setVisibility(View.GONE);
        tvCountdown.setVisibility(View.VISIBLE);
    }

    private void updateTimerUI() {
        int hours = (int) (totalTimeInMillis / 3600000);
        int minutes = (int) (totalTimeInMillis % 3600000) / 60000;
        int seconds = (int) (totalTimeInMillis % 60000) / 1000;

        String timerText = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        tvCountdown.setText(timerText);
    }

    private void cancelTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            btnStartTimer.setEnabled(true);
            btnCancelTimer.setBackgroundColor(getResources().getColor(android.R.color.transparent));
            tvCountdown.setText("00:00:00");
            npHours.setVisibility(View.VISIBLE);
            npMinutes.setVisibility(View.VISIBLE);
            npSeconds.setVisibility(View.VISIBLE);
            tvCountdown.setVisibility(View.GONE);
        }
    }

    private void pauseTimer() {
        if (!isPaused) {
            countDownTimer.cancel();
            isPaused = true;
            btnPauseTimer.setText("Resume");
        } else {
            startTimer();
            isPaused = false;
            btnPauseTimer.setText("Pause");
        }
    }
}

