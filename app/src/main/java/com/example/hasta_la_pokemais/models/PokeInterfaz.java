package com.example.hasta_la_pokemais.models;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeInterfaz {

    @GET("pokemon?offset=20&limit=1292")
    Call<Pokemon> getObjetoBase();

}
