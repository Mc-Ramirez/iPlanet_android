package com.utad.iplanet.views.ListPlanet

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.chip.Chip

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

    private val adapter =  PlanetAdapter {
        val action = PlanetsListFragmentDirections.actionPlanetsListFragmentToPlanetDetailFragment()
        findNavController().navigate(action)
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://iplanet-api.herokuapp.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val service = retrofit.create(PlanetService::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlanetsListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }



    fun requestAllItems(){
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

    fun requestItemByCategory(category: String){
        service.getItemsByCategory(category).enqueue(object : Callback<List<PlanetItem>>{
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

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatingActionButton3.setOnClickListener(){
            requestAllItems()
            adapter.notifyDataSetChanged()
        }

        binding.cg.setOnCheckedChangeListener { group, checkedId ->
            val chip:Chip? = group.findViewById(checkedId)

            if (chip?.isChecked == true){
                if (chip.text.equals("Planets")){
                    requestItemByCategory("Planet")
                    adapter.notifyDataSetChanged()
                } else if (chip.text.equals("Stars")){
                    requestItemByCategory("Star")
                    adapter.notifyDataSetChanged()
                }else if (chip.text.equals("Moons")){
                    requestItemByCategory("Moon")
                    adapter.notifyDataSetChanged()
                }
            } else {
                requestAllItems()
                adapter.notifyDataSetChanged()
            }
        }

        requestAllItems()
        configRv()
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