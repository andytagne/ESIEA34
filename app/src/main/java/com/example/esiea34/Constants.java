package com.example.esiea34;

public class Constants {
   private static String KEY_POKEMON_LIST = "jsonPokemonList";
   private static String BASE_URL = "https://pokeapi.co/";

   public String getPokemonKey(){
       return this.KEY_POKEMON_LIST;
   }

   public String getBaseUrl(){
       return this.BASE_URL;
   }
}
