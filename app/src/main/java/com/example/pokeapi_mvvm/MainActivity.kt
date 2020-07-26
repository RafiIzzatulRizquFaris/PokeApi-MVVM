
package com.example.pokeapi_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var pokemonViewModel: PokemonViewModel
    private lateinit var adapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = PokemonAdapter {}
        adapter.notifyDataSetChanged()

        rv_pokemon.layoutManager = GridLayoutManager(this, 2)
        rv_pokemon.adapter = adapter


        pokemonViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(PokemonViewModel::class.java)
        pokemonViewModel.setListPokemon()
        pokemonViewModel.getListPokemon().observe(this, Observer { pokemon ->
            if (pokemon == null){
                Log.e("Pokemon", "was on null")
            }else {
                adapter.setAdapter(pokemon)
                Log.e("Pokemon", pokemon.toString())
            }
        })

    }
}