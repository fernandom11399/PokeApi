package com.example.hasta_la_pokemais;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.hasta_la_pokemais.adapters.PokeAdapter;
import com.example.hasta_la_pokemais.models.PokeInterfaz;
import com.example.hasta_la_pokemais.models.Pokemon;
import com.example.hasta_la_pokemais.models.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvpokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvpokemon = findViewById(R.id.RVPokemones);

        Retrofit pokeretro = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PokeInterfaz pokeinterfaz = pokeretro.create(PokeInterfaz.class);

        Call<Pokemon> pokecall = pokeinterfaz.getObjetoBase();
        pokecall.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (response.isSuccessful()) {
                    rvpokemon.setAdapter(new PokeAdapter(response.body().getResults()));
                    rvpokemon.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    rvpokemon.setHasFixedSize(true);
                }
            }
            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {

            }
        });
    }
}