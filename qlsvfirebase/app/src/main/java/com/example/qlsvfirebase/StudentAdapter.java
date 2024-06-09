package com.example.qlsvfirebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {
    private Context mContext;
    private List<Student> studentList;

    public StudentAdapter(Context context, List<Student> list) {
        super(context, 0, list);
        mContext = context;
        studentList = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.student_item, parent, false);
        }

        Student student = studentList.get(position);

        TextView nameTextView = convertView.findViewById(R.id.textViewName);
        TextView emailTextView = convertView.findViewById(R.id.textViewEmail);

        nameTextView.setText(student.getName());
        emailTextView.setText(student.getEmail());

        return convertView;
    }
}
