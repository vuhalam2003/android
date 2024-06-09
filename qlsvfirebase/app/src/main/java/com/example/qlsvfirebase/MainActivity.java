package com.example.qlsvfirebase;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button buttonAdd, buttonUpdate, buttonDelete;
    private EditText editTextName, editTextEmail;
    private ListView listViewStudents;
    private FirebaseDatabaseHelper databaseHelper;
    private List<Student> studentList;
    private StudentAdapter studentAdapter;
    private Student selectedStudent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        listViewStudents = findViewById(R.id.listViewStudents);

        databaseHelper = new FirebaseDatabaseHelper();
        studentList = new ArrayList<>();
        studentAdapter = new StudentAdapter(this, studentList);
        listViewStudents.setAdapter(studentAdapter);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStudent();
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateStudent();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteStudent();
            }
        });

        listViewStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedStudent = studentList.get(position);
                editTextName.setText(selectedStudent.getName());
                editTextEmail.setText(selectedStudent.getEmail());
            }
        });

        loadStudents();
    }
        String name = editTextName.getText().toString();
    }

    private void updateStudent() {
        if (selectedStudent != null) {
            String name = editTextName.getText().toString();
            String email = editTextEmail.getText().toString();
            Student updatedStudent = new Student(selectedStudent.getId(), name, email);
            databaseHelper.updateStudent(selectedStudent.getId(), updatedStudent);
            loadStudents(); // Reload students after updating
        } else {
            Toast.makeText(this, "No student selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteStudent() {
        if (selectedStudent != null) {
            databaseHelper.deleteStudent(selectedStudent.getId());
            loadStudents(); // Reload students after deleting
        } else {
            Toast.makeText(this, "No student selected", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadStudents() {
        databaseHelper.getReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                studentList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Student student = postSnapshot.getValue(Student.class);
                    studentList.add(student);
                }
                studentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error
            }
        });
    }
}
