package com.example.qlsvfirebase;

import com.google.firebase.database.DataSnapshot;

public class Student {
    private String id;
    private  String name;
    private String email;

    public Student(){
    }
    public Student(String id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setAge(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }
}
