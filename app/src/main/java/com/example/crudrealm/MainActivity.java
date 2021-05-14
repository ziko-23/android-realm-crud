package com.example.crudrealm;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import io.realm.*;

public class MainActivity extends Activity {
   public static Realm realm;
    RecyclerView recyclerView;
  public static  RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<Frog> data;
    private Button btnAdd;
    Spinner spinner;
    ArrayList<String> spiceis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .allowQueriesOnUiThread(true)
                .allowWritesOnUiThread(true)
                .build();
        realm = Realm.getInstance(config);
        recyclerView = (RecyclerView) findViewById(R.id.recycleViewContainer);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        data = getData();
        mAdapter = new CustomRecyclerAdapter(this, data);
        System.out.println(data.toString());
        recyclerView.setAdapter(mAdapter);

        //////
        spinner = (Spinner)findViewById(R.id.spinner);
        spiceis = getSpices();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item,spiceis);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(this);
    }

    private List<Frog> getData() {
        List<Frog> list = new ArrayList<>();
        list.addAll(realm.where(Frog.class).findAll());
//        list.addAll(realm.where(Frog.class).findAll().get(0).getSpecies());
        return  list;
    }
    private ArrayList<String> getSpices() {
        List<Frog> list =getData();
        List<String> listSpices = new ArrayList<>();
        for (Frog frog : list) {
            listSpices.add(frog.getSpecies());
        }
        return (ArrayList<String>) listSpices;
    }

    @Override
    protected void onStart() {
        super.onStart();
        btnAdd.setOnClickListener(v -> {
            Intent send = new Intent(MainActivity.this,SingleProduct.class);
            send.putExtra("action","add");
            startActivity(send);
        });

//        insertFrog(new Frog("", 1, "", "")).toString();
//        System.out.println(countFrogs());
//        updateFrog(realm.where(Frog.class).equalTo("name","").findFirst().getId()," l7mar");
//        deleteFrog(realm.where(Frog.class).equalTo("name", "").findFirst().getId());
//        show();
//        System.out.println(countFrogs());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public static AtomicReference<Frog> insertFrog(Frog f) {
        // create an reference to a frog
        AtomicReference<Frog> frog = new AtomicReference<Frog>();
// insert a new frog into the database and store it in our reference
        realm.executeTransaction(transactionRealm -> {
            Frog result = transactionRealm.createObject(Frog.class, UUID.randomUUID().toString());
            result.set(f);
            frog.set(result);
        });
        return frog;
    }

    private int countFrogs() {
        return (int) realm.where(Frog.class).count();
    }

    private void show() {
        List<Frog> list = new ArrayList<>();
        list.addAll(realm.where(Frog.class).findAll());
        for (Frog f : list) {
            System.out.println(f);
        }
    }

    public void updateFrog(String id, String name) {
        Frog f = realm.where(Frog.class).equalTo("id", id).findFirst();
        realm.executeTransaction(transactionRealm -> {
            f.setName(name);
        });
    }

    public static void deleteFrog(String id) {
        realm.executeTransaction(transactionRealm -> {
            realm.where(Frog.class).equalTo("id", id).findFirst().deleteFromRealm();
        });
    }

}