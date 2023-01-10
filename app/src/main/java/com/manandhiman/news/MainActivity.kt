package com.manandhiman.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.manandhiman.news.databinding.ActivityMainBinding
import com.manandhiman.news.helper.Post
import com.manandhiman.news.helper.RecyclerViewAdapter
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private var postsList = mutableListOf<Post>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.swipeRefreshLayout.setOnRefreshListener {
            getJSONArray()
            binding.swipeRefreshLayout.isRefreshing = false
        }
//        val dummyPost = Post("title1","content1","url1")
//        postsList.add(dummyPost)
        getJSONArray()
    }

    private fun getJSONArray() {
       val url = "https://inshorts.deta.dev/news?category=technology"
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        val request = JsonObjectRequest(Request.Method.GET, url, null,
            {
                val data = it.getJSONArray("data")
                for(i in 0 until data.length()){
                    val dataObject = data.getJSONObject(i)

                    val post = Post(
                        dataObject.getString("title"),
                        dataObject.getString("content"),
                        dataObject.getString("readMoreUrl")
                    )
                    postsList.add(post)
                }
                val adapter = RecyclerViewAdapter(postsList, this)
                binding.recyclerView.adapter = adapter
            },
            {
                Log.d("response", it.toString())

            }
        )
        Volley.newRequestQueue(this).add(request)

    }

}