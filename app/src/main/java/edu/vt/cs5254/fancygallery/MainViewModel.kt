package edu.vt.cs5254.fancygallery

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.vt.cs5254.fancygallery.api.GalleryItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val TAG = "GalleryViewModel"

class MainViewModel : ViewModel() {
    private val photoRepository = PhotoRepository()
    private val _galleryItems: MutableStateFlow<List<GalleryItem>> =
        MutableStateFlow(emptyList())
    val galleryItems: StateFlow<List<GalleryItem>>
        get() = _galleryItems.asStateFlow()

    init {
        // Load initial gallery items
        viewModelScope.launch {
            _galleryItems.value = loadPhotos()
        }
    }

    // Function to reload gallery items
    fun reloadGalleryItems() {
        viewModelScope.launch {
            _galleryItems.value = emptyList() // Clear the current list
            Log.d("GalleryAdapter", "Cleared gallery items:")
            _galleryItems.update { loadPhotos() } // Fetch and update with new items
            Log.d("GalleryFragment", "Reload triggered")
        }
    }

    // Private helper function to fetch photos
    private suspend fun loadPhotos(): List<GalleryItem> {
        return try {
            val items = photoRepository.fetchPhotos(perPage = 99) // Fetch photos with pagination
            Log.d(TAG, "Items received: $items")
            items
        } catch (ex: Exception) {
            Log.e(TAG, "Failed to fetch gallery items", ex)
            emptyList() // Return an empty list in case of failure
        }
    }
}