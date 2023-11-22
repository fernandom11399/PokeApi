package com.example.hasta_la_pokemais.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hasta_la_pokemais.R;
import com.example.hasta_la_pokemais.models.Pokemon;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.transform.Result;
public class PokeAdapter extends RecyclerView.Adapter<PokeAdapter.ViewHolder> {
    List<com.example.hasta_la_pokemais.models.Result> pokemons;
    public PokeAdapter(List<com.example.hasta_la_pokemais.models.Result> results) {
        this.pokemons = results;
    }
    @NonNull
    @Override
    public PokeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater pokeLI = LayoutInflater.from(parent.getContext());
        View pokeV = pokeLI.inflate(R.layout.pokedex, parent, false);
        return new ViewHolder(pokeV);
    }
    @Override
    public void onBindViewHolder(@NonNull PokeAdapter.ViewHolder holder, int position) {
        com.example.hasta_la_pokemais.models.Result pokemon = pokemons.get(position);
        holder.setData(pokemon);
    }
    @Override
    public int getItemCount() {
        return pokemons.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        com.example.hasta_la_pokemais.models.Result poke;
        ImageView pokefoto;
        TextView pokenombre;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pokefoto = itemView.findViewById(R.id.IVPokefoto);
            pokenombre = itemView.findViewById(R.id.TVPokenombre);
        }
        public void setData(com.example.hasta_la_pokemais.models.Result pokemon) {
            this.poke = pokemon;
            pokenombre.setText(pokemon.getName());
            String url = pokemon.geturl();
            Pattern pattern = Pattern.compile("\\/\\d+\\/");
            Matcher matcher = pattern.matcher(url);
            if (matcher.find()) {
                String number = matcher.group().replaceAll("/", "");
                Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + number + ".png")
                        .into(pokefoto);
            }
        }
    }
}