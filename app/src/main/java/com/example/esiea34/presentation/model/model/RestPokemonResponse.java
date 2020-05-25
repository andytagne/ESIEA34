package com.example.esiea34.presentation.model.model;

import java.util.List;

public class RestPokemonResponse {
    private int count;
    private String next;
    private List<Pokemon> results;

    public String getNext() {
        return next;
    }

    public List<Pokemon> getResults() {
        return results;
    }

    public int getCount() {
        return count;
    }
}
