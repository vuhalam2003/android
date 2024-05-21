package com.example.bai15;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.activity.EdgeToEdge;

public class MainActivity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    private SQLiteDatabase database;
    private EditText edtMaLop, edtTenLop, edtSiSo;
    private Button btnInsert, btnDelete, btnUpdate, btnQuery;
    private ListView lv;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        dbHelper = new MyDatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        edtMaLop = findViewById(R.id.edtmalop);
        edtTenLop = findViewById(R.id.edttenlop);
        edtSiSo = findViewById(R.id.edtsiso);
        btnInsert = findViewById(R.id.btninsert);
        btnDelete = findViewById(R.id.btndelete);
        btnUpdate = findViewById(R.id.btnupdate);
        btnQuery = findViewById(R.id.btnquery);
        lv = findViewById(R.id.lv);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
            }
        });

        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                queryData();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.textView), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void insertData() {
        String maLop = edtMaLop.getText().toString();
        String tenLop = edtTenLop.getText().toString();
        int siSo = Integer.parseInt(edtSiSo.getText().toString());

        database.execSQL("INSERT INTO " + MyDatabaseHelper.TABLE_NAME + " (" +
                        MyDatabaseHelper.COLUMN_MA_LOP + ", " +
                        MyDatabaseHelper.COLUMN_TEN_LOP + ", " +
                        MyDatabaseHelper.COLUMN_SI_SO + ") VALUES (?, ?, ?)",
                new Object[]{maLop, tenLop, siSo});
    }

    private void deleteData() {
        String maLop = edtMaLop.getText().toString();

        database.execSQL("DELETE FROM " + MyDatabaseHelper.TABLE_NAME +
                        " WHERE " + MyDatabaseHelper.COLUMN_MA_LOP + " = ?",
                new Object[]{maLop});
    }

    private void updateData() {
        String maLop = edtMaLop.getText().toString();
        String tenLop = edtTenLop.getText().toString();
        int siSo = Integer.parseInt(edtSiSo.getText().toString());

        database.execSQL("UPDATE " + MyDatabaseHelper.TABLE_NAME + " SET " +
                        MyDatabaseHelper.COLUMN_TEN_LOP + " = ?, " +
                        MyDatabaseHelper.COLUMN_SI_SO + " = ? WHERE " +
                        MyDatabaseHelper.COLUMN_MA_LOP + " = ?",
                new Object[]{tenLop, siSo, maLop});
    }

    private void queryData() {
        Cursor cursor = database.rawQuery("SELECT * FROM " + MyDatabaseHelper.TABLE_NAME, null);
        String[] from = {MyDatabaseHelper.COLUMN_MA_LOP, MyDatabaseHelper.COLUMN_TEN_LOP, MyDatabaseHelper.COLUMN_SI_SO};
        int[] to = {R.id.txtMaLop, R.id.txtTenLop, R.id.txtSiSo};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.list_item, cursor, from, to, 0);
        lv.setAdapter(adapter);
    }
}
