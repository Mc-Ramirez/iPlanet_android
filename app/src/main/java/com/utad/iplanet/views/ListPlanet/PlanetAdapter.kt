package com.utad.iplanet.views.ListPlanet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.utad.iplanet.databinding.ItemPlanetBinding
import com.utad.iplanet.imageURL
import com.utad.iplanet.model.PlanetItem

class PlanetAdapter(private val onItemClicked:(PlanetItem) -> Unit ):ListAdapter<PlanetItem, PlanetAdapter.ViewHolder>(PlanetItemCallback()) {

    inner class ViewHolder(val binding: ItemPlanetBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPlanetBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val planetItem = getItem(position)
        holder.binding.imageView2.imageURL(planetItem.planetUrlImage)
        holder.binding.textView.text = planetItem.planetName
        holder.binding.tvCategory.text = planetItem.category
        holder.binding.root.setOnClickListener{
            onItemClicked(planetItem)
        }
    }
}

class PlanetItemCallback : DiffUtil.ItemCallback<PlanetItem>(){

    override fun areItemsTheSame(oldItem: PlanetItem, newItem: PlanetItem): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: PlanetItem, newItem: PlanetItem): Boolean = oldItem.id == newItem.id

}