package com.example.esiea34;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PokemonTest {
    private SharedPreferences sharedPreferences;
    private Gson gson;

    private List<Pokemon> getDataFromCash() {
        String jsonPokemon=sharedPreferences.getString(Constants.KEY_POKEMON_LIST,null);
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
