package net.dejanjokic.mediadb.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import net.dejanjokic.mediadb.util.Constants.API.BACKDROP_BASE_URL
import net.dejanjokic.mediadb.util.Constants.API.POSTER_BASE_URL
import net.dejanjokic.mediadb.util.Constants.DB.TABLE_TV_SHOW_DETAILS

@Entity(tableName = TABLE_TV_SHOW_DETAILS)
data class TvShowDetails(
    @PrimaryKey @field:Json(name = "id") val id: Long,
    @field:Json(name = "name") val title: String,
    @field:Json(name = "overview") val overview: String?,
    @field:Json(name = "poster_path") val posterPath: String?,
    @field:Json(name = "backdrop_path") val backdropPath: String?,
    @field:Json(name = "first_air_date") val firstAirDate: String,
    @field:Json(name = "last_air_date") val lastAirDate: String,
    @field:Json(name = "in_production") val inProduction: Boolean,
    @field:Json(name = "number_of_episodes") val numberOfEpisodes: Int,
    @field:Json(name = "number_of_seasons") val numberOfSeasons: Int,
    @field:Json(name = "status") val status: String?,
    @field:Json(name = "type") val type: String?,
    @field:Json(name = "vote_average") val voteAverage: Double
)

fun TvShowDetails.fullPosterPath(): String? = POSTER_BASE_URL + this.posterPath
fun TvShowDetails.fullBackdropPath(): String? = BACKDROP_BASE_URL + this.backdropPath