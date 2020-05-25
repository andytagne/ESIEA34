package com.example.esiea34.data;

import com.example.esiea34.presentation.model.model.Pokemon;
import com.example.esiea34.presentation.model.model.RestPokemonResponse;

import java.util.List;

public interface PokeCallback {
    public void onSuccess(List<Pokemon> restPokemonResponse);
    public void onFailed();
}
