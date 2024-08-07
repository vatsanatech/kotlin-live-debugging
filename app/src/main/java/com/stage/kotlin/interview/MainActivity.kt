package com.stage.kotlin.interview

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stage.kotlin.interview.data.Comment
import com.stage.kotlin.interview.data.Post
import com.stage.kotlin.interview.data.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var postRecyclerView: RecyclerView
    private lateinit var commentRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        postRecyclerView = findViewById(R.id.postRecyclerView)
        commentRecyclerView = findViewById(R.id.commentRecyclerView)

        postRecyclerView.layoutManager = LinearLayoutManager(this)
        commentRecyclerView.layoutManager = LinearLayoutManager(this)

        fetchPosts()
        fetchComments()
        fetchCSV()
    }

    private fun fetchPosts() {
        RetrofitInstance.api.getPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    val posts = response.body()!!
                    postRecyclerView.adapter = PostAdapter(posts)
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed to load posts", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun fetchComments() {
        RetrofitInstance.api.getComments().enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (response.isSuccessful) {
                    val comments = response.body()!!
                    commentRecyclerView.adapter = CommentAdapter(comments)
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed to load comments", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun fetchCSV() {
        RetrofitInstance.api.getCSV().enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    val csvData = response.body()!!
                    // Handle CSV data here
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed to load CSV", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
