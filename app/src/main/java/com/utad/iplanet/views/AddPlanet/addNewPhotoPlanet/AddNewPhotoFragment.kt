package com.utad.iplanet.views.AddPlanet.addNewPhotoPlanet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utad.iplanet.databinding.FragmentAddNewPhotoBinding
import com.utad.iplanet.databinding.FragmentPlanetDetailBinding


class AddNewPhotoFragment : Fragment() {
    private var _binding: FragmentAddNewPhotoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNewPhotoBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}