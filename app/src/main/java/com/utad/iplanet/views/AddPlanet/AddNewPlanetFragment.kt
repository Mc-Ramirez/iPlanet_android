package com.utad.iplanet.views.AddPlanet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utad.iplanet.R
import com.utad.iplanet.databinding.FragmentAddNewPlanetBinding
import com.utad.iplanet.databinding.FragmentPlanetDetailBinding


class AddNewPlanetFragment : Fragment() {

    private var _binding: FragmentAddNewPlanetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNewPlanetBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}