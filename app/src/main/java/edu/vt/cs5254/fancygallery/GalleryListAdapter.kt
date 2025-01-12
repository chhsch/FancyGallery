package edu.vt.cs5254.fancygallery

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.CachePolicy
import edu.vt.cs5254.fancygallery.api.GalleryItem
import edu.vt.cs5254.fancygallery.databinding.ListItemGalleryBinding

class GalleryItemHolder(

    private val binding: ListItemGalleryBinding
) : RecyclerView.ViewHolder(binding.root) {
    // Private variable to store the currently bound gallery item
    private var currentGalleryItem: GalleryItem? = null

    // Public read-only property to expose the bound gallery item
    val boundGalleryItem: GalleryItem?
        get() = currentGalleryItem
    fun bind(galleryItem: GalleryItem, onItemClicked: (Uri) -> Unit) {
        currentGalleryItem = galleryItem
        binding.itemImageView.load(galleryItem.url) {
            placeholder(R.drawable.ic_placeholder)
            diskCachePolicy(CachePolicy.DISABLED)
        }
        binding.root.setOnClickListener{
            onItemClicked(galleryItem.photoPageUri)
        }
    }

}
class GalleryListAdapter(
    private val galleryItems: List<GalleryItem>,
    private val onItemClicked: (Uri) -> Unit
) : RecyclerView.Adapter<GalleryItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryItemHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemGalleryBinding.inflate(inflater, parent, false)
        return GalleryItemHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryItemHolder, position: Int) {
        holder.bind(galleryItems[position], onItemClicked)
    }

    override fun getItemCount(): Int = galleryItems.size
}





