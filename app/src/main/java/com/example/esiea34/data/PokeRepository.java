package com.example.esiea34.data;

import android.content.SharedPreferences;

import com.example.esiea34.presentation.model.model.RestPokemonResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokeRepository {
    private PokeApi pokeApi;
    private SharedPreferences sharedPreferences;

    public PokeRepository(PokeApi pokeApi,SharedPreferences sharedPreferences) {
        this.pokeApi = pokeApi;
        this.sharedPreferences=sharedPreferences;
    }

    public void getPokemonResponse(final PokeCallback callback){
        pokeApi.getPokemonResponse().enqueue(new Callback<RestPokemonResponse>() {
            @Override
            public void onResponse(Call<RestPokemonResponse> call, Response<RestPokemonResponse> response) {
                if(response.isSuccessful() && response.body()!=null){
                    callback.onSuccess(response.body());
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
