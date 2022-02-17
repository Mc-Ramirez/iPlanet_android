package com.utad.iplanet.views.AddPlanet

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.utad.iplanet.R
import com.utad.iplanet.databinding.FragmentAddNewPlanetBinding
import com.utad.iplanet.databinding.FragmentPlanetDetailBinding
import com.utad.iplanet.model.PlanetBody
import com.utad.iplanet.model.PlanetItem
import com.utad.iplanet.model.PlanetService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AddNewPlanetFragment : Fragment() {

    private var _binding: FragmentAddNewPlanetBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentAddNewPlanetBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    private fun addNewPlanetToDB(){
        val thePlanetItem = PlanetBody("Quakers","23323","elradio","el periodo","los kilos", "la densidad","lo que sea", "lo que sea2")
        service.addNewPlanet(thePlanetItem).enqueue(object :Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful){
                    val addedPlanet = response.body()
                    Log.d("RESPONSE", addedPlanet.toString())

                } else {
                    Log.d("RESPONSE", "${response.body()}")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("RESPONSE", "$t")
            }

        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAddImg2.setOnClickListener(){
            addNewPlanetToDB()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}