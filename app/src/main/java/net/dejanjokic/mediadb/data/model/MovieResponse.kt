package net.dejanjokic.mediadb.data.model

import com.squareup.moshi.Json
import net.dejanjokic.mediadb.util.Constants.API.POSTER_BASE_URL

data class MovieResponse(
    @field:Json(name = "page") val page: Int,
    @field:Json(name = "results") val movies: List<Movie>
)

data class Movie(
    @field:Json(name = "id") val id: Long,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "poster_path") val posterPath: String
)

fun Movie.fullPosterPath(): String? = POSTER_BASE_URL + this.posterPath