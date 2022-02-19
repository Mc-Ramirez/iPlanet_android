package com.utad.iplanet.views.EditPlanet

import android.graphics.Color
import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.utad.iplanet.databinding.FragmentEditPlanetBinding
import com.utad.iplanet.imageURL
import com.utad.iplanet.model.PlanetBody
import com.utad.iplanet.model.PlanetItem
import com.utad.iplanet.model.PlanetService
import com.utad.iplanet.views.AddPlanet.AddNewPlanetFragmentDirections
import com.utad.iplanet.views.DetailPlanet.PlanetDetailFragmentArgs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EditPlanetFragment : Fragment() {
    private var _binding: FragmentEditPlanetBinding? = null
    private val binding get() = _binding!!
    private val args: EditPlanetFragmentArgs by navArgs()
    private var imageTaken =""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditPlanetBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://iplanet-api.herokuapp.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val service = retrofit.create(PlanetService::class.java)

    private fun requestItemById(id:String){
        service.getItemById(id).enqueue(object : Callback<PlanetItem> {

            override fun onResponse(call: Call<PlanetItem>, response: Response<PlanetItem>) {
                if (response.isSuccessful){
                    binding.tfname.setText(response.body()?.planetName)
                    binding.tfCategory.setText(response.body()?.category)
                    binding.tfDensity.setText(response.body()?.planetDensity)
                    binding.tfMass.setText(response.body()?.planetMassKg)
                    binding.tfDistanceToSun.setText(response.body()?.planetDistanceMio)
                    binding.tfEquatorial.setText(response.body()?.planetEquatorialRadius)
                    binding.tfRotationPeriod.setText(response.body()?.planetRotationPeriod)
                    imageTaken = response.body()?.planetUrlImage.toString()
                }
            }

            override fun onFailure(call: Call<PlanetItem>, t: Throwable) {

            }
        })
    }

    private fun editPlanetToDB(id: String,thePlanetItemEdit: PlanetBody){
        service.editPlanet(id,thePlanetItemEdit).enqueue(object :Callback<PlanetItem>{
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestItemById(args.planetId)

        binding.imageView3.imageURL(args.planetImage)
        binding.btnSavePlanet.setOnClickListener(){
            val planetEdited = PlanetBody("${binding.tfname.text}",
            "${binding.tfDistanceToSun.text}",
            "${binding.tfEquatorial.text}",
            "${binding.tfRotationPeriod.text}",
            "${binding.tfMass.text}",
            "${binding.tfDensity.text}",
            "${binding.tfCategory.text}",
            args.planetImage)
            editPlanetToDB(args.planetId, planetEdited)

            Snackbar.make(binding.root,"Planeta Editado", BaseTransientBottomBar.LENGTH_SHORT
            ).setBackgroundTint(Color.BLACK).show()

            goBackToDetail()
        }
        binding.btnGoBackToList.setOnClickListener(){
            goBackToDetail()
        }

        binding.btnEditPicture.setOnClickListener(){
            val action = EditPlanetFragmentDirections.actionEditPlanetFragmentToEditPhotoPlanetFragment(imageTaken,args.planetId)
            findNavController().navigate(action)
        }
    }

    fun goBackToDetail(){
        val action = EditPlanetFragmentDirections.actionEditPlanetFragmentToPlanetDetailFragment(args.planetId)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}