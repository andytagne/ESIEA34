package com.example.esiea34;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.esiea34.data.PokeApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singletons {
     private static Gson gsonInstance;
     private static PokeApi pokeApiInstance;
     private static SharedPreferences sharedPreferencesInstance;
     private Constants constants=new Constants();

     public static Gson getGson(){
         if(gsonInstance==null){
            gsonInstance= new GsonBuilder()
                     .setLenient()
                     .create();

         }
         return gsonInstance;
     }

     public static SharedPreferences getSharedPreferences(Context context ){
         if(sharedPreferencesInstance==null){
             sharedPreferencesInstance=context.getSharedPreferences("application_esiea", Context.MODE_PRIVATE);
         }
         return sharedPreferencesInstance;
     }


    public static PokeApi getPokeApi(){
        if(pokeApiInstance==null){
             Constants constants=new Constants();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(constants.getBaseUrl())
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();

            pokeApiInstance = retrofit.create(PokeApi.class);
        }
        return pokeApiInstance;
    }
}
