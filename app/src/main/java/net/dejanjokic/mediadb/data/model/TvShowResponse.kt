package net.dejanjokic.mediadb.data.model

import com.squareup.moshi.Json
import net.dejanjokic.mediadb.util.Constants.API.POSTER_BASE_URL

data class TvShowResponse(
    @field:Json(name = "page") val page: Int,
    @field:Json(name = "results") val tvShows: List<TvShow>
)

data class TvShow(
    @field:Json(name = "id") val id: Long,
    @field:Json(name = "name") val title: String,
    @field:Json(name = "poster_path") val posterPath: String
)

fun TvShow.fullPosterPath(): String? = POSTER_BASE_URL + this.posterPath