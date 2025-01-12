package edu.vt.cs5254.fancygallery.api

import edu.vt.cs5254.fancygallery.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

//private const val API_KEY = "yourApiKeyHere"
interface FlickrApi {

    @GET("services/rest/")
    suspend fun fetchPhotos(
        @Query("method") method: String = "flickr.interestingness.getList",
        @Query("api_key") apiKey: String = BuildConfig.FLICKR_API_KEY,
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noJsonCallback: Int = 1,
        @Query("extras") extras: String = "url_s, geo",
        @Query("per_page") perPage: Int // Add the per_page query parameter
    ): FlickrResponse}
