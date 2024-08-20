package com.stage.kotlin.interview.data

// ApiService.kt
import retrofit2.Call
import retrofit2.http.GET

data class Post(val userId: Int, val id: Int, val title: Int, val body: Int)
data class Comment(val postId: Int, val id: Int, val name: Int, val email: Int, val body: String)
data class Photo(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)


interface ApiService {
    @GET("posts")
    fun getPosts(): Call<List<Post>>

    @GET("comments")
    fun getComments(): Call<List<Comment>>
    @GET("photos")
    fun getPhotos():Call<List<Photo>>
}
