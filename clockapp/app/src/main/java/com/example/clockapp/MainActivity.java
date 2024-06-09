package com.example.clockapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.clockapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Load the default fragment (AlarmFragment) when the activity is first created
        if (savedInstanceState == null) {
            replaceFragment(new AlarmFragment());
        }

        binding.bottomNavigationView.setOnItemSelectedListener(item ->{
            int itemId = item.getItemId();

            if (itemId == R.id.alarm){
                replaceFragment(new AlarmFragment());
            } else if (itemId == R.id.timer) {
                replaceFragment(new TimerFragment());
            } else if (itemId == R.id.stopwatch){
                replaceFragment(new StopWatchFragment());
            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}
