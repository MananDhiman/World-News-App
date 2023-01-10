package com.manandhiman.news.helper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.manandhiman.news.R

class RecyclerViewAdapter(private val postsList: List<Post>, val context: Context):
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        val tvContent: TextView = itemView.findViewById(R.id.textViewContent)
        val layout: ConstraintLayout = itemView.findViewById(R.id.constraint_layout)

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
            Model.openWebView(url)
        }
    }

    override fun getItemCount(): Int {
     return postsList.size
    }
}