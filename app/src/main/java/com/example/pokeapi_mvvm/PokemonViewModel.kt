package com.example.pokeapi_mvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PokemonViewModel : ViewModel() {
    private val mutableLiveDataPokemon = MutableLiveData<Pokemon>()
    fun setListPokemon(){
        val retrofitClient = RetrofitClient()
        val service = retrofitClient.getData().create(ApiService::class.java)
        service.getPokemon("151").enqueue(object : Callback<Pokemon>{
            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                print(t.message.toString())
            }

            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                Log.e("Response", response.body().toString())
                mutableLiveDataPokemon.postValue(response.body())
            }

        })
    }

    fun getListPokemon(): MutableLiveData<Pokemon> {
        return  mutableLiveDataPokemon
    }
}