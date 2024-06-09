package com.example.qlsvfirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceStudents;

    public FirebaseDatabaseHelper(){
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceStudents = mDatabase.getReference("students");
    }


    public void addStudent(String id, Student student){
        mReferenceStudents.child(id).setValue(student);
    }

    public void deleteStudent(String id){
        mReferenceStudents.child(id).removeValue();
    }

    public DatabaseReference getReference(){
        return mReferenceStudents;
    }

    public void updateStudent(String id, Student student) {
        mReferenceStudents.child(id).setValue(student);
    }
}
