package net.dejanjokic.mediadb.ui.movie.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_movie.view.*
import net.dejanjokic.mediadb.R
import net.dejanjokic.mediadb.data.model.Movie
import net.dejanjokic.mediadb.data.model.fullPosterPath
import net.dejanjokic.mediadb.util.glide.GlideApp

class MovieAdapter(private val clickListener: (Movie) -> Unit)
    : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_movie, parent, false))

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie) = with(itemView) {

            GlideApp.with(this)
                .load(movie.fullPosterPath())
                .placeholder(R.drawable.ic_movie)
                .into(imageViewMovieItemPoster)

            setOnClickListener { clickListener(movie) }
        }
    }

    private class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem == newItem
    }
}