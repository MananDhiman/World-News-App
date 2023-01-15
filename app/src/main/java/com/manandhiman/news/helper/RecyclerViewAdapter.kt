package com.manandhiman.news.helper

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.manandhiman.news.R
import com.manandhiman.news.WebViewActivity

class RecyclerViewAdapter(private val postsList: List<Post>, val context: Context):
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        val tvContent: TextView = itemView.findViewById(R.id.textViewContent)
        val layout: LinearLayout = itemView.findViewById(R.id.linear_layout)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_post_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val posts = postsList[position]

        holder.tvTitle.text = posts.title
        holder.tvContent.text = posts.content

        val url = posts.readMoreUrl

        holder.layout.setOnClickListener {
            openDetails(url)
        }
    }

    private fun openDetails(url: String) {
        val intent = Intent(context, WebViewActivity::class.java).apply {
            putExtra("url",url)
        }
        context.startActivity(intent)
    }

    override fun getItemCount(): Int {
     return postsList.size
    }
}