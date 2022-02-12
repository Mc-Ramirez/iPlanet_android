package com.utad.iplanet.views.ListPlanet

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.utad.iplanet.R
import com.utad.iplanet.databinding.FragmentEditPlanetBinding
import com.utad.iplanet.databinding.FragmentPlanetsListBinding
import com.utad.iplanet.model.PlanetItem
import com.utad.iplanet.model.PlanetService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PlanetsListFragment : Fragment() {
    private var _binding: FragmentPlanetsListBinding? = null
    private val binding get() = _binding!!
    private val adapter =  PlanetAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlanetsListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    private fun requestData(){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://iplanet-api.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(PlanetService::class.java)

        service.getAllPlanets().enqueue(object : Callback<List<PlanetItem>>{
            override fun onResponse(
                call: Call<List<PlanetItem>>,
                response: Response<List<PlanetItem>>
            ) {
                if (response.isSuccessful){
                    adapter.submitList(response.body())
                } else {
                    Toast.makeText(context, "Error en la respuesta", Toast.LENGTH_LONG)
                }
            }

            override fun onFailure(call: Call<List<PlanetItem>>, t: Throwable) {
                Toast.makeText(context, "Error en la conexion", Toast.LENGTH_LONG)
                Log.e("requestData", "error")
            }
        })
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestData()
        configRv()

        val planets = mutableListOf<PlanetItem>()
        for (index in 1 .. 10){
            PlanetItem(index.toString(), "name:${index}",index,index, index, "planetMassKg:${index}", index, "category:${index}","planetUrlImage:${index}")
        }
        adapter.submitList(planets)
    }

    private fun configRv(){
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}