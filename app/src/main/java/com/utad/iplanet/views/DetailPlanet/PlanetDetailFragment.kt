package com.utad.iplanet.views.DetailPlanet


import android.os.Bundle
import android.util.Log
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

private lateinit var planeta : PlanetItem
class PlanetDetailFragment : Fragment() {
    private var _binding: FragmentPlanetDetailBinding? = null
    private val binding get() = _binding!!
    private val args : PlanetDetailFragmentArgs by navArgs()
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
        _binding = FragmentPlanetDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getDetails()
    }

    private fun getDetails() {
        service.getItemByName(args.planetname).enqueue(object : Callback<List<PlanetItem>> {
            override fun onResponse(
                call: Call<List<PlanetItem>>,
                response: Response<List<PlanetItem>>
            ) {
                if (response.isSuccessful) {
                    binding.tvNameIcon.text = planeta.planetName
                    binding.ivPlanet.imageURL(planeta.planetUrlImage)
                } else {
                    Toast.makeText(context, "Error al cargar datos", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<PlanetItem>>, t: Throwable) {
                Toast.makeText(context, "Error al cargar datos", Toast.LENGTH_LONG).show()
                Log.e("requestData", "error")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
