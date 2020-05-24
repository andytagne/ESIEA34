package com.example.esiea34;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity { // une activity c'est un ecran
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static String BASE_URL = "https://pokeapi.co/";
    private SharedPreferences sharedPreferences;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
   // Cat cat1 = new Cat("titi");
        sharedPreferences = getSharedPreferences("application_esiea", Context.MODE_PRIVATE);
         gson = new GsonBuilder()
                .setLenient()
                .create();

         List<Pokemon> pokemons = getDataFromCash();
         if(pokemons!= null){
             showList(pokemons);
         }
         else {
             makeApiCall();
         }
    }

    private List<Pokemon> getDataFromCash() {
        String jsonPokemon=sharedPreferences.getString("jsonPokemonList",null);
        if(jsonPokemon==null){
            return null;
        }
        else{
            Type listType = new TypeToken<ArrayList<Pokemon>>(){}.getType();
            return gson.fromJson(jsonPokemon,listType);
        }

    }

    private void showList(List<Pokemon> pokemonList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);// on fait refernce a l'id de recyclerview de activity_main
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);




        /*
        List<String> input = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            input.add("Test" + i);// on cree une liste de 100 elements qui sera notre liste d'elements
        }// define an adapter */

        mAdapter = new MyAdapter(pokemonList);// on construit avec notre liste
        recyclerView.setAdapter(mAdapter);
    }

    private void makeApiCall(){


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PokeApi pokeApi = retrofit.create(PokeApi.class);

        Call<RestPokemonResponse> call = pokeApi.getPokemonResponse();
         call.enqueue(new Callback<RestPokemonResponse>() {
             @Override
             public void onResponse(Call<RestPokemonResponse> call, Response<RestPokemonResponse> response) {
                 if(response.isSuccessful() && response.body()!=null){
                     List<Pokemon> pokemonList  = response.body().getResults(); // ici response on prend les resultats du corps
                     //Toast.makeText(getApplicationContext(), "API Success", Toast.LENGTH_SHORT).show();
                     saveList(pokemonList);
                     showList(pokemonList);
                 }
                 else{
                     showErrors();
                 }
             }

             @Override
             public void onFailure(Call<RestPokemonResponse> call, Throwable t) {
                showErrors();
             }
         });
    }

    private void saveList(List<Pokemon> pokemonList) {
        String jsonString = gson.toJson(pokemonList);
        sharedPreferences
                .edit()
                .putString("jsonPokemonList", jsonString)
                .apply();
        Toast.makeText(getApplicationContext(), "Liste sauvegardee", Toast.LENGTH_SHORT).show();

    }

    private void showErrors() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();// get application dit ou on est
    }
}
