package com.utad.iplanet.views.EditPlanet.addPhotoPlanet

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.utad.iplanet.databinding.FragmentEditPhotoPlanetBinding
import com.utad.iplanet.imageURL
import com.utad.iplanet.views.AddPlanet.AddNewPlanetFragmentArgs
import com.utad.iplanet.views.EditPlanet.EditPlanetFragmentDirections


class editPhotoPlanetFragment : Fragment() {
    private var _binding: FragmentEditPhotoPlanetBinding? = null
    private val binding get() = _binding!!
    private val args: editPhotoPlanetFragmentArgs by navArgs()
    var pictureToSend = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditPhotoPlanetBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageView3.imageURL(args.imageUrlEdit)

        binding.btnEditPicture.setOnClickListener(){
            binding.imageView3.imageURL(binding.tfNewPlanetURL.text.toString())
        }


        binding.btnCreateUser.setOnClickListener(){
            if (binding.tfNewPlanetURL.text.isNullOrBlank()){
                pictureToSend = args.imageUrlEdit
            } else {
                pictureToSend = binding.tfNewPlanetURL.text.toString()
            }
            Snackbar.make(binding.root,"Picture change", BaseTransientBottomBar.LENGTH_SHORT
            ).setBackgroundTint(Color.BLACK).show()

            goBackToEdit()
        }

    }

    fun goBackToEdit(){
        val action = editPhotoPlanetFragmentDirections.actionEditPhotoPlanetFragmentToEditPlanetFragment(args.planetId,pictureToSend)
        findNavController().navigate(action)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}