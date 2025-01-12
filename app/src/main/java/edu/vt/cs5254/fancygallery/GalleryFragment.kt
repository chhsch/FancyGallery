package edu.vt.cs5254.fancygallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import coil.imageLoader
import edu.vt.cs5254.fancygallery.databinding.FragmentGalleryBinding
import kotlinx.coroutines.launch

private const val TAG = "GalleryFragment"
class GalleryFragment: Fragment() {
    private var _binding: FragmentGalleryBinding? = null
    private val binding
        get() = checkNotNull(_binding) { "FragmentGalleryBinding is null!!!" }

    private val vm: MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)

        // Set up the menu provider for the fragment
        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_gallery, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.reload_menu -> {
                        clearImageCacheAndReload()
                        true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner)
        binding.photoGrid.layoutManager = GridLayoutManager(context, 3)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Collect gallery items and update the RecyclerView adapter
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.galleryItems.collect { items ->
                    binding.photoGrid.adapter = GalleryListAdapter(items) { photoPageUri ->
                        // Navigate to the photo page using NavController
                        findNavController().navigate(
                            GalleryFragmentDirections.showPhoto(photoPageUri)
                        )
                    }
                }
            }
        }
    }

    private fun clearImageCacheAndReload() {
        // Access the shared Coil ImageLoader instance
        val imageLoader = context?.imageLoader
        if (imageLoader == null) {
            Log.w(TAG, "ImageLoader is null. Cache clear failed.")
            return
        }

        // Clear the memory cache
        imageLoader.memoryCache?.clear()
        Log.d(TAG, "Image cache cleared")

        // Trigger ViewModel reload directly
        vm.reloadGalleryItems()

        // Show a confirmation toast
        Toast.makeText(requireContext(), "Cache cleared and reloading images", Toast.LENGTH_SHORT).show()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}