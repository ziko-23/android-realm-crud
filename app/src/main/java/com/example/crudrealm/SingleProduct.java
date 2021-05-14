package com.example.crudrealm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SingleProduct extends AppCompatActivity {
    private EditText editTextTextPersonName;
    private Button btnSave;
    private Button btnDelete;
    private Button btnInsert;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product);
        editTextTextPersonName = (EditText) findViewById(R.id.editTextTextPersonName);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnSave.setVisibility(View.GONE);
        btnDelete.setVisibility(View.GONE);
        btnInsert.setVisibility(View.GONE);
        intent = getIntent();
        editTextTextPersonName.setText(intent.getStringExtra("name"));
        switch (intent.getStringExtra("action")) {
            case "add":
                btnInsert.setVisibility(View.VISIBLE);
                break;
            case "update-delete":
                btnDelete.setVisibility(View.VISIBLE);
                btnSave.setVisibility(View.VISIBLE);
                break;
            default:
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Frog f = MainActivity.realm.where(Frog.class).equalTo("id", intent.getStringExtra("id")).findFirst();
                MainActivity.realm.executeTransaction(transactionRealm -> {
                    f.setName(editTextTextPersonName.getText().toString());
                });
                MainActivity.mAdapter.notifyDataSetChanged();
                Intent intent1 = new Intent(SingleProduct.this, MainActivity.class);
                startActivity(intent1);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.deleteFrog(intent.getStringExtra("id"));
                MainActivity.mAdapter.notifyDataSetChanged();
                Intent intent1 = new Intent(SingleProduct.this, MainActivity.class);
                startActivity(intent1);
            }
        });
        btnInsert.setOnClickListener(v -> {
            MainActivity.insertFrog(new Frog(editTextTextPersonName.getText().toString(), 1, "", ""));
            MainActivity.mAdapter.notifyDataSetChanged();
            Intent intent1 = new Intent(SingleProduct.this, MainActivity.class);
            startActivity(intent1);
        });
    }
}