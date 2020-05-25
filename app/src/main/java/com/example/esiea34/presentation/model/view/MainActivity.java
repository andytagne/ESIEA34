package com.example.esiea34.presentation.model.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.esiea34.R;
import com.example.esiea34.Singletons;
import com.example.esiea34.presentation.model.controller.MainController;
import com.example.esiea34.presentation.model.model.Pokemon;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity { // une activity c'est un ecran
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity mainActivity;
        controller = new MainController(MainActivity.this, Singletons.getGson(),Singletons.getSharedPreferences(getApplicationContext()));
        controller.onStart();
   // Cat cat1 = new Cat("titi");

    }


    public void showList(List<Pokemon> pokemonList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);// on fait refernce a l'id de recyclerview de activity_main
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        /*
        List<String> input = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            input.add("Test" + i);// on cree une liste de 100 elements qui sera notre liste d'elements
        }// define an adapter */

        mAdapter = new MyAdapter(pokemonList, new MyAdapter.OnItemClickListener(){
            public void onItemClick(Pokemon item){
                controller.onItemClick(item);
            }
        });// on construit avec notre liste
        recyclerView.setAdapter(mAdapter);
    }





    public void showErrors() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();// get application dit ou on est
    }

    public void navigateToDetails(Pokemon item) {
        Intent myIntent = new Intent(MainActivity.this, DetailActivity.class);
        myIntent.putExtra("pokemonKey", Singletons.getGson().toJson(item)); //Optional parameters
        MainActivity.this.startActivity(myIntent);



    }
}
