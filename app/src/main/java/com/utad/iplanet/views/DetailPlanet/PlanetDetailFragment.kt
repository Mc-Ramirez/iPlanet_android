package com.utad.iplanet.views.DetailPlanet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utad.iplanet.R
import com.utad.iplanet.databinding.FragmentPlanetDetailBinding
import com.utad.iplanet.databinding.FragmentPlanetsListBinding

class PlanetDetailFragment : Fragment() {
    private var _binding: FragmentPlanetDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlanetDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}