package com.utad.iplanet.views.AddPlanet.addNewPhotoPlanet

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.utad.iplanet.databinding.FragmentAddNewPhotoPlanetBinding
import com.utad.iplanet.imageURL
import com.utad.iplanet.views.AddPlanet.AddNewPlanetFragmentDirections
import java.lang.Exception


class AddNewPhotoFragment : Fragment() {
    private var _binding: FragmentAddNewPhotoPlanetBinding? = null
    private val binding get() = _binding!!
    private val defaultImg: String = "https://cdn.pixabay.com/photo/2017/03/02/15/09/planet-2111528_960_720.png"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNewPhotoPlanetBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.PlanetImage.imageURL(defaultImg)

        var userUrlImage = defaultImg

        binding.btnLoadPhoto.setOnClickListener(){

                binding.PlanetImage.imageURL(binding.tfNewPlanetURL.text.toString())
                userUrlImage = binding.tfNewPlanetURL.text.toString()
                binding.btnAddThePhoto.isEnabled = true

        }

        binding.btnAddThePhoto.setOnClickListener(){
            Snackbar.make(binding.root,"Picture change", BaseTransientBottomBar.LENGTH_SHORT
            ).setBackgroundTint(Color.BLACK).show()


            val action = AddNewPhotoFragmentDirections.actionAddNewPhotoFragmentToAddNewPlanetFragment(userUrlImage)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}