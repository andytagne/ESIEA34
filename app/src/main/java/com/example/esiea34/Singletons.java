package com.example.esiea34;

import com.example.esiea34.data.PokeApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singletons {
     private static Gson gsonInstance;
     private static PokeApi pokeApiInstance;
     private Constants constants=new Constants();

     public static Gson getGson(){
         if(gsonInstance==null){
            gsonInstance= new GsonBuilder()
                     .setLenient()
                     .create();

         }
         return gsonInstance;
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
