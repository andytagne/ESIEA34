package com.example.esiea34.data;

import com.example.esiea34.presentation.model.model.RestPokemonResponse;

public interface PokeCallback {
    public void onSuccess(RestPokemonResponse restPokemonResponse);
    public void onFailed();
}
