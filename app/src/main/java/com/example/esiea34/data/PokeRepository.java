package com.example.esiea34.data;

import android.content.SharedPreferences;

import com.example.esiea34.Constants;
import com.example.esiea34.presentation.model.model.Pokemon;
import com.example.esiea34.presentation.model.model.RestPokemonResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokeRepository {
    private PokeApi pokeApi;
    private SharedPreferences sharedPreferences;
    private Constants constants = new Constants();
    private Gson gson;

    public PokeRepository(PokeApi pokeApi,SharedPreferences sharedPreferences,Gson gson) {
        this.pokeApi = pokeApi;
        this.sharedPreferences=sharedPreferences;
        this.gson=gson;
    }

    public void getPokemonResponse(final PokeCallback callback){
        List<Pokemon> list=getDataFromCash();
        if(list!=null){
            callback.onSuccess(list);
        }
        else{
            pokeApi.getPokemonResponse().enqueue(new Callback<RestPokemonResponse>() {
                @Override
                public void onResponse(Call<RestPokemonResponse> call, Response<RestPokemonResponse> response) {
                    if(response.isSuccessful() && response.body()!=null){
                        callback.onSuccess(response.body().getResults());
                    }
                    else{
                        callback.onFailed();
                    }
                }

                @Override
                public void onFailure(Call<RestPokemonResponse> call, Throwable t) {
                    callback.onFailed();
                }
            });
        }

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

    private void saveList(List<Pokemon> pokemonList) {
        String jsonString = gson.toJson(pokemonList);
        sharedPreferences
                .edit()
                .putString(constants.getPokemonKey(), jsonString)
                .apply();
        //Toast.makeText(getApplicationContext(), "Liste sauvegardée", Toast.LENGTH_SHORT).show();

    }
}
