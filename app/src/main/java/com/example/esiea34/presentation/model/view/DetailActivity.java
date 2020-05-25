package com.example.esiea34.presentation.model.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.esiea34.R;
import com.example.esiea34.Singletons;
import com.example.esiea34.presentation.model.controller.MainController;
import com.example.esiea34.presentation.model.model.Pokemon;

public class DetailActivity extends AppCompatActivity {
    private MainController controller;
    private TextView textView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        controller = new MainController(DetailActivity.this, Singletons.getGson(),Singletons.getSharedPreferences(getApplicationContext()));
        controller.onStart();

        Intent intent = getIntent();
        String pokemonJson = intent.getStringExtra("pokemonKey");
        Pokemon pokemon = Singletons.getGson().fromJson(pokemonJson, Pokemon.class);
        showDetails(pokemon);
    }

    private void showDetails(Pokemon pokemon) {
        textView.setText(pokemon.getName());
    }
}
