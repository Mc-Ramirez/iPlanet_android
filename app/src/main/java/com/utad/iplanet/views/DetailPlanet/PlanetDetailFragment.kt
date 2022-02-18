package com.utad.iplanet.views.DetailPlanet


import android.graphics.Color.BLACK
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.utad.iplanet.databinding.FragmentPlanetDetailBinding
import com.utad.iplanet.imageURL
import com.utad.iplanet.model.PlanetItem
import com.utad.iplanet.model.PlanetService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PlanetDetailFragment : Fragment() {
    private var _binding: FragmentPlanetDetailBinding? = null
    private val binding get() = _binding!!
    private val args: PlanetDetailFragmentArgs by navArgs()
    private var planetImageURL = ""

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://iplanet-api.herokuapp.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val service = retrofit.create(PlanetService::class.java)

    private fun requestItemById(id:String){
        service.getItemById(id).enqueue(object : Callback<PlanetItem>{

            override fun onResponse(call: Call<PlanetItem>, response: Response<PlanetItem>) {
                if (response.isSuccessful){
                    binding.tvPlanetName.text = response.body()?.planetName ?: "No encontrado"
                    binding.tvCathegory.text = response.body()?.category ?: "No encontrado"
                    binding.tvDensity.text = response.body()?.planetDensity ?: "No encontrado"
                    binding.tvDistance.text = response.body()?.planetDistanceMio ?: "No encontrado"
                    binding.tvMass.text = response.body()?.planetMassKg ?: "No encontrado"
                    binding.tvRadius.text = response.body()?.planetEquatorialRadius ?: "No encontrado"
                    binding.tvRotation.text = response.body()?.planetRotationPeriod ?: "No encontrado"
                    planetImageURL = response.body()?.planetUrlImage.toString()
                    response.body()?.planetUrlImage?.let { binding.ivPlanetImage.imageURL(it) }
                }
            }

            override fun onFailure(call: Call<PlanetItem>, t: Throwable) {

            }
        })
    }

    private fun deleteItemById(id:String){
        service.deletePlanetById(id).enqueue(object : Callback<String>{

            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.d("RESPONSE","Planet with $id has been deleted")
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e("requestData", "error")
            }
        })
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlanetDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestItemById(args.planetId)
        binding.btnDelete.setOnClickListener(){
            deleteItemById(args.planetId)

                Snackbar.make(binding.root,"Planeta eliminado", BaseTransientBottomBar.LENGTH_SHORT
                ).setBackgroundTint(BLACK).show()

            moveBackToDetail()
        }

        binding.btnEditPlanet.setOnClickListener(){
            val action = PlanetDetailFragmentDirections.actionPlanetDetailFragmentToEditPlanetFragment(args.planetId,planetImageURL)
            findNavController().navigate(action)
        }

        binding.btnGoBackToList.setOnClickListener(){
            moveBackToDetail()
        }
    }

    fun moveBackToDetail(){
        val action = PlanetDetailFragmentDirections.actionPlanetDetailFragmentToPlanetsListFragment()
        findNavController().navigate(action)

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
