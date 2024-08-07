package com.stage.kotlin.interview.data

// ApiService.kt
import retrofit2.Call
import retrofit2.http.GET

data class Post(val userId: Int, val id: Int, val title: Int, val body: String)
data class Comment(val postId: Int, val id: String, val name: String, val email: Int, val body: String)

interface ApiService {
    @GET("posts")
    fun getPosts(): Call<List<Post>>

    @GET("comments")
    fun getComments(): Call<List<Comment>>

    @GET("path/to/csv")
    fun getCSV(): Call<String>
}
