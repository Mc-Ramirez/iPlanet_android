package com.utad.iplanet.views.ListPlanet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utad.iplanet.R
import com.utad.iplanet.databinding.FragmentEditPlanetBinding
import com.utad.iplanet.databinding.FragmentPlanetsListBinding

class PlanetsListFragment : Fragment() {
    private var _binding: FragmentPlanetsListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlanetsListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}