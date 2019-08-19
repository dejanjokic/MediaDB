package net.dejanjokic.mediadb.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import net.dejanjokic.mediadb.util.Constants.API.BACKDROP_BASE_URL
import net.dejanjokic.mediadb.util.Constants.API.POSTER_BASE_URL
import net.dejanjokic.mediadb.util.Constants.DB.TABLE_MOVIE_DETAILS

@Entity(tableName = TABLE_MOVIE_DETAILS)
data class MovieDetails(
    @PrimaryKey @field:Json(name = "id") val id: Long,
    @field:Json(name = "title") val title: String?,
    @field:Json(name = "imdb_id") val imdbId: String?,
    @field:Json(name = "original_title") val originalTitle: String?,
    @field:Json(name = "overview") val overview: String?,
    @field:Json(name = "release_date") val releaseDate: String?,
    @field:Json(name = "revenue") val revenue: Long?,
    @field:Json(name = "runtime") val runtime: Int?,
    @field:Json(name = "status") val status: String?,
    @field:Json(name = "tagline") val tagline: String?,
    @field:Json(name = "vote_average") val voteAverage: Double,
    @field:Json(name = "poster_path") val posterPath: String?,
    @field:Json(name = "backdrop_path") val backdropPath: String?,
    @field:Json(name = "homepage") val homepage: String?
)

fun MovieDetails.fullPosterPath(): String? = POSTER_BASE_URL + this.posterPath
fun MovieDetails.fullBackdropPath(): String? = BACKDROP_BASE_URL + this.backdropPath