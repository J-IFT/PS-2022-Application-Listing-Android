package com.example.tp1

import android.net.Uri
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.parcel.Parcelize
import java.net.URI

@Parcelize
    data class Animaux (
    val id : Long,
    var nom : String,
    var type: String,
    var description : String,
    var portrait: String
    ) : Parcelable

    class AnimauxAdapter(val AnimauxAfficher : Array<Animaux>,val listener: (Animaux, Int) -> Unit)
        : RecyclerView.Adapter<AnimauxAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
            val ma_ligne = LayoutInflater.from(parent.context).inflate(R.layout.item_animaux,parent, false)
            return ViewHolder(ma_ligne)
        }

        override fun getItemCount(): Int = AnimauxAfficher.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            Log.i("XXX","onBindViewHolder")
            holder.bind(AnimauxAfficher[position],listener, position)
        }

        class ViewHolder(val elementDeListe : View) : RecyclerView.ViewHolder(elementDeListe)
        {
            fun bind(animaux: Animaux, listener: (Animaux, Int) -> Unit, position: Int) = with(itemView)
            {
                Log.i("XXX","bind")
                itemView.findViewById<TextView>(R.id.nom_animal).text = animaux.nom
                itemView.findViewById<TextView>(R.id.type).text = animaux.type
                Glide.with(this)
                    .load(animaux.portrait)
                    .into(itemView.findViewById<ImageView>(R.id.portrait))
                setOnClickListener { listener(animaux, position) }
            }
        }
}