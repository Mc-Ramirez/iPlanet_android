package com.utad.iplanet.views.AddPlanet

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.utad.iplanet.R
import com.utad.iplanet.databinding.FragmentAddNewPlanetBinding
import com.utad.iplanet.databinding.FragmentPlanetDetailBinding
import com.utad.iplanet.imageURL
import com.utad.iplanet.model.PlanetBody
import com.utad.iplanet.model.PlanetItem
import com.utad.iplanet.model.PlanetService
import com.utad.iplanet.views.DetailPlanet.PlanetDetailFragmentDirections
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AddNewPlanetFragment : Fragment() {

    private var _binding: FragmentAddNewPlanetBinding? = null
    private val binding get() = _binding!!
    private val defaultImg: String = "https://cdn.pixabay.com/photo/2017/03/02/15/09/planet-2111528_960_720.png"

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

    private fun addNewPlanetToDB(thePlanetItem: PlanetBody){
            service.addNewPlanet(thePlanetItem).enqueue(object :Callback<PlanetItem>{
            override fun onResponse(call: Call<PlanetItem>, response: Response<PlanetItem>) {
                if (response.isSuccessful){
                    val addedPlanet = response.body()
                    Log.d("RESPONSE", addedPlanet.toString())

                } else {
                    Log.d("RESPONSE", "${response.body()}")
                }
            }

            override fun onFailure(call: Call<PlanetItem>, t: Throwable) {
                Log.d("RESPONSE", "$t")
            }

        })

    }

    fun moveBackToDetail(){
        val action = AddNewPlanetFragmentDirections.actionAddNewPlanetFragmentToPlanetsListFragment()
        findNavController().navigate(action)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGoBackToList.setOnClickListener(){
            val action = AddNewPlanetFragmentDirections.actionAddNewPlanetFragmentToPlanetsListFragment()
            findNavController().navigate(action)
        }

        binding.btnNewPicture.setOnClickListener(){
            val action = AddNewPlanetFragmentDirections.actionAddNewPlanetFragmentToAddNewPhotoFragment()
            findNavController().navigate(action)
        }
        binding.imageView3.imageURL("https://cdn.pixabay.com/photo/2017/03/02/15/09/planet-2111528_960_720.png")
        binding.btnCreateUser.setOnClickListener(){
            val thePlanetItem = PlanetBody("${binding.tfName.text}",
                "${binding.tfDistanceToSun.text}",
                "${binding.tfEquatorial.text}",
                "${binding.tfRotationPeriod.text}",
                "${binding.tfName.text}",
                "${binding.tfDensity.text}",
                "${binding.tfCategory.text}",
                "$defaultImg")


            addNewPlanetToDB(thePlanetItem)
            Snackbar.make(binding.root,"Planeta creado", BaseTransientBottomBar.LENGTH_SHORT
            ).setBackgroundTint(Color.BLACK).show()
            moveBackToDetail()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}