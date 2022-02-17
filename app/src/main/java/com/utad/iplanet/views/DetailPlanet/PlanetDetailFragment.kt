package com.utad.iplanet.views.DetailPlanet


import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
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

    private var planetNameDetail : String = "No encontrado"

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://iplanet-api.herokuapp.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val service = retrofit.create(PlanetService::class.java)

    private fun requestItemById(id:String){
        service.getItemById(id).enqueue(object : Callback<PlanetItem>{

            override fun onResponse(call: Call<PlanetItem>, response: Response<PlanetItem>) {
                if (response.isSuccessful){
                    binding.planetNameInDetail.text = response.body()?.planetName?.toUpperCase() ?: "No se ha encontrado"
                    response.body()?.planetUrlImage?.let { binding.ivPlanetImageInDetail.imageURL(it) }

                } else {
                    Toast.makeText(context, "Error en la respuesta", Toast.LENGTH_LONG)
                }
            }

            override fun onFailure(call: Call<PlanetItem>, t: Throwable) {
                Toast.makeText(context, "Error en la conexion", Toast.LENGTH_LONG)
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
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestItemById(args.planetId)
        Log.d("Response","Hola ${args.planetId}")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
