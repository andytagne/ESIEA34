package com.example.esiea34.presentation.model.controller;

import android.content.SharedPreferences;

import com.example.esiea34.Constants;
import com.example.esiea34.Singletons;
import com.example.esiea34.presentation.model.model.Pokemon;
import com.example.esiea34.presentation.model.model.RestPokemonResponse;
import com.example.esiea34.presentation.model.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainController {
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainActivity view;
    private Constants constants=new Constants();

    public MainController(MainActivity mainActivity,Gson gson,SharedPreferences sharedPreferences){
        this.view=mainActivity;
        this.sharedPreferences= sharedPreferences;
        this.gson=gson;
    }

    public void onStart(){


        List<Pokemon> pokemons = getDataFromCash();
        if(pokemons!= null){
            view.showList(pokemons);
        }
        else {
            makeApiCall();
        }
    }

    private void makeApiCall(){


        Call<RestPokemonResponse> call = Singletons.getPokeApi().getPokemonResponse();
        call.enqueue(new Callback<RestPokemonResponse>() {
            @Override
            public void onResponse(Call<RestPokemonResponse> call, Response<RestPokemonResponse> response) {
                if(response.isSuccessful() && response.body()!=null){
                    List<Pokemon> pokemonList  = response.body().getResults(); // ici response on prend les resultats du corps
                    //Toast.makeText(getApplicationContext(), "API Success", Toast.LENGTH_SHORT).show();
                    saveList(pokemonList);
                    view.showList(pokemonList);
                }
                else{
                    view.showErrors();
                }
            }

            @Override
            public void onFailure(Call<RestPokemonResponse> call, Throwable t) {
                view.showErrors();
            }
        });
    }

    private void saveList(List<Pokemon> pokemonList) {
        String jsonString = gson.toJson(pokemonList);
        sharedPreferences
                .edit()
                .putString(constants.getPokemonKey(), jsonString)
                .apply();
        //Toast.makeText(getApplicationContext(), "Liste sauvegardée", Toast.LENGTH_SHORT).show();

    }


    private List<Pokemon> getDataFromCash() {
        String jsonPokemon=sharedPreferences.getString(constants.getPokemonKey(),null);
        if(jsonPokemon==null){
            return null;
        }
        else{
            Type listType = new TypeToken<ArrayList<Pokemon>>(){}.getType();
            return gson.fromJson(jsonPokemon,listType);
        }

    }
}
