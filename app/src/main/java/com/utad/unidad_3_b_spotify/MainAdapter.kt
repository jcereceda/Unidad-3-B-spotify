package com.utad.unidad_3_b_spotify

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MainAdapter(
    private val miDataSet: List<Album>,
    val onClick: (Album) -> Int?
    ):RecyclerView.Adapter<MainAdapter.ViewHolder>() {


    class ViewHolder(var view: View):RecyclerView.ViewHolder(view) {

        fun bindItems(album: Album) {
            val imgAndroid = view.findViewById<View>(R.id.imgLista) as ImageView
            Picasso.get().load(album.linkFoto).placeholder(R.drawable.ic_espera).into(imgAndroid)
            val tvNombre = view.findViewById<View>(R.id.titleList) as TextView
            tvNombre.text = album.titulo
            val tvNumfollow =  view.findViewById<View>(R.id.numFollowers) as TextView
            tvNumfollow.text = album.seguidores.toString() + " seguidores"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_block_principal, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = miDataSet.get(position)
        holder.bindItems(data)
        var elemento = holder.itemView.findViewById<View>(R.id.elemento) as LinearLayout
        elemento.setOnClickListener{
            onClick(data)
        }
    }

    override fun getItemCount(): Int {
        return miDataSet.count()
    }

}