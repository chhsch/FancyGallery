package edu.vt.cs5254.fancygallery.api

import android.net.Uri

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GalleryItem(
    val title: String,
    val id: String,
    @Json(name = "url_s") val url: String,
    val owner: String,
    val latitude: Double,
    val longitude: Double

)
{
    val photoPageUri: Uri = Uri.parse("https://www.flickr.com/photos/")
        .buildUpon()
        .appendPath(owner)
        .appendPath(id)
        .build()

}
@JsonClass(generateAdapter = true)
data class PhotoResponse(
    @Json(name = "photo") val galleryItems: List<GalleryItem>
)
@JsonClass(generateAdapter = true)
data class FlickrResponse(
    val photos: PhotoResponse
)