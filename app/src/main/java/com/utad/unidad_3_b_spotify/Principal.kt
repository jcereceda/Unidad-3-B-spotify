package com.utad.unidad_3_b_spotify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.utad.unidad_3_b_spotify.databinding.FragmentPrincipalBinding
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import com.google.gson.Gson

class Principal : Fragment() {
    private var _binding: FragmentPrincipalBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPrincipalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val json = readJsonFromFile("albumes.json")
        val todos = Gson().fromJson(json, AlbumResponse::class.java)

        val myAdapter = MainAdapter(todos.data) {
            var album = it
            var fragment = Lista()
            val bundle = Bundle()
            bundle.putStringArrayList("canciones",album.canciones)
            bundle.putString("nombreGrupo",album.grupo)
            bundle.putString("linkFoto",album.linkFoto)
            bundle.putString("album",album.titulo)
            fragment.arguments = bundle
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.mainContainer, fragment)?.addToBackStack(null)?.commit()
        }
        var mainRecyclerView = binding.mainRecyclerView
        mainRecyclerView.layoutManager = GridLayoutManager(this.context,2)
        mainRecyclerView.adapter = myAdapter
    }

    private fun readJsonFromFile(fileName: String): String {
        var json = ""
        try {
            val bufferedReader = BufferedReader(
                InputStreamReader(activity?.assets?.open(fileName))
            )
            val paramsBuilder = StringBuilder()
            var line: String? = bufferedReader.readLine()
            while (line != null) {
                paramsBuilder.append(line)
                line = bufferedReader.readLine()
            }
            json = paramsBuilder.toString()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return json
    }
}