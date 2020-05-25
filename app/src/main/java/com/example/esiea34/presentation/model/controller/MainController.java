package com.example.esiea34.presentation.model.controller;

import android.content.SharedPreferences;

import com.example.esiea34.Constants;
import com.example.esiea34.Singletons;
import com.example.esiea34.data.PokeCallback;
import com.example.esiea34.data.PokeRepository;
import com.example.esiea34.presentation.model.model.Pokemon;
import com.example.esiea34.presentation.model.model.RestPokemonResponse;
import com.example.esiea34.presentation.model.view.DetailActivity;
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
    //private SharedPreferences sharedPreferences;
    //private Gson gson;
    private MainActivity view;
    private DetailActivity view_1;
    PokeRepository repository;
    private Constants constants=new Constants();

    public MainController(MainActivity mainActivity_1, PokeRepository repository){
        this.view=mainActivity_1;
        this.repository=repository;
        //this.gson=gson;
    }

    public MainController(DetailActivity mainActivity, PokeRepository repository){
        this.view_1=mainActivity;
        this.repository=repository;
        //this.gson=gson;
    }

    public void onStart(){

        repository.getPokemonResponse(new PokeCallback() {
            @Override
            public void onSuccess(List<Pokemon> restPokemonResponse) {
                view.showList(restPokemonResponse);
            }

            @Override
            public void onFailed() {

            }
        });
        //List<Pokemon> pokemons = getDataFromCash();
        view.showErrors();
    }

   /* private void makeApiCall(){


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
    }*/






    public void onItemClick(Pokemon item) {
         view.navigateToDetails(item);
    }
}
