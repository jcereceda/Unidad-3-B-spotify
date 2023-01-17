package com.utad.unidad_3_b_spotify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.MaterialToolbar
import com.utad.unidad_3_b_spotify.databinding.FragmentListaBinding


class Lista : Fragment() {

    private var _binding: FragmentListaBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var toolBar = view.findViewById<MaterialToolbar>(R.id.toolBarCanciones)
        val nombreGrupo= arguments?.getString("nombreGrupo").toString()
        val linkFoto = arguments?.getString("linkFoto").toString()
        val canciones = arguments?.getStringArrayList("canciones") as ArrayList<String>
        val myAdapter = CancionesAdapter(nombreGrupo, linkFoto, canciones)
        toolBar.title = arguments?.getString("album")
        var ListasRecyclerView = binding.cancionesRecyclerView

        ListasRecyclerView.layoutManager =  LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        ListasRecyclerView.adapter = myAdapter


        toolBar.setNavigationOnClickListener{
            activity?.supportFragmentManager?.popBackStack()
        }
    }
}
