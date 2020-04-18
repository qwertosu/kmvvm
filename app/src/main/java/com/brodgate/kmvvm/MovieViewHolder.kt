package com.brodgate.kmvvm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.movie_item.view.*


class MovieViewHolder(v : View) : RecyclerView.ViewHolder(v) {

    private val tvId = v.tvId
    private val tvTitle = v.tvTitle
    private val tvRank = v.tvRank

    fun bind(item: Movie?) {
        tvId.text = "id: ${item?.id}"
        tvTitle.text = "title: ${item?.title}"
        tvRank.text = "rank: ${item?.rank}"
    }

    companion object{
        fun create(parent: ViewGroup) : MovieViewHolder{
            return MovieViewHolder(
                LayoutInflater.from(parent.context).inflate
                    (R.layout.movie_item, parent, false)
            )
        }
    }
}