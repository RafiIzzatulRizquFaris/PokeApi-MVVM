package com.example.pokeapi_mvvm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokemonAdapter(private val listener: (Result) -> Unit) :
    RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    private val data = ArrayList<Result>()

    fun setAdapter(pokemon: Pokemon) {
        data.clear()
        data.addAll(pokemon.results)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(result: Result, listener: (Result) -> Unit) {
            itemView.apply {
                txt_name_pokemon_item.text = result.name
                Glide.with(context).load(
                    "https://pokeres.bastionbot.org/images/pokemon/${result.url.substring(34)
                        .replace("/", "")}.png"
                ).into(img_pokemon_item)
            }
            itemView.setOnClickListener {
                listener(result)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(data[position], listener)
    }
}