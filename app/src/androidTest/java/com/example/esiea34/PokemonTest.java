package com.example.esiea34;

import android.content.SharedPreferences;

import com.example.esiea34.presentation.model.model.Pokemon;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PokemonTest {
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private Constants constants;

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

    public void test(){
        List<Pokemon> pokemons = getDataFromCash();
        assertNotNull(pokemons);
    }



}
