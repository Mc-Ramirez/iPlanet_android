package com.utad.iplanet.views.EditPlanet.addPhotoPlanet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utad.iplanet.databinding.FragmentEditPhotoPlanetBinding
import com.utad.iplanet.databinding.FragmentPlanetDetailBinding

class editPhotoPlanetFragment : Fragment() {
    private var _binding: FragmentEditPhotoPlanetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditPhotoPlanetBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}