package net.dejanjokic.mediadb.ui.tv.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_tv_show.view.*
import net.dejanjokic.mediadb.R
import net.dejanjokic.mediadb.data.model.TvShow
import net.dejanjokic.mediadb.data.model.fullPosterPath
import net.dejanjokic.mediadb.util.glide.GlideApp

class TvShowAdapter(private val clickListener: (TvShow) -> Unit)
    : ListAdapter<TvShow, TvShowAdapter.TvShowViewHolder>(TvShowDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder =
        TvShowViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_tv_show, parent, false))

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(tvShow: TvShow) = with(itemView) {

            GlideApp.with(this)
                .load(tvShow.fullPosterPath())
                .placeholder(R.drawable.ic_video_label)
                .into(imageViewTvShowItemPoster)

            setOnClickListener { clickListener(tvShow) }
        }
    }

    private class TvShowDiffCallback : DiffUtil.ItemCallback<TvShow>() {
        override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean =
            oldItem == newItem
    }
}