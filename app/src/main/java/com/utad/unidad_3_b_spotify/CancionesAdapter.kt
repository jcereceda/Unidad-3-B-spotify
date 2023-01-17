package com.utad.unidad_3_b_spotify

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.util.ArrayList

class CancionesAdapter(private val nombreGrupo: String,
                                private val linkFoto: String,
                                private val canciones: ArrayList<String>
):RecyclerView.Adapter<CancionesAdapter.ViewHolderCanciones>() {

    class ViewHolderCanciones(var view: View):RecyclerView.ViewHolder(view) {
        fun bindItems(cancion: String) {
            val nombreCancion = view.findViewById<View>(R.id.nombreCancion) as TextView
            nombreCancion.text = cancion

            var imgFav = view.findViewById<ImageView>(R.id.favourite)
            var fav = false
            imgFav.setOnClickListener{
                if(!fav) {
                    imgFav.setImageResource(R.drawable.ic_corazon_lleno)
                    fav = true
                } else {
                    imgFav.setImageResource(R.drawable.ic_corazon_vacio)
                    fav = false
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCanciones {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_block_canciones, parent, false)
        return ViewHolderCanciones(view)
    }

    override fun onBindViewHolder(holder: CancionesAdapter.ViewHolderCanciones, position: Int) {
        val tfnombreGrupo = holder.view.findViewById<View>(R.id.nombreGrupo) as TextView
        tfnombreGrupo.text = nombreGrupo
        val fotoAlbum = holder.view.findViewById<View>(R.id.imgAlbum) as ImageView
        Picasso.get().load(linkFoto).placeholder(R.drawable.ic_espera)
            .into(fotoAlbum)
        val data = canciones?.get(position)
        if (data != null) {
            holder.bindItems(data)
        }



    }

    override fun getItemCount(): Int {
        return canciones.count()
    }

}