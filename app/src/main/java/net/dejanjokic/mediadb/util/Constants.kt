package net.dejanjokic.mediadb.util

object Constants {

    object API {
        const val API_KEY = "49a9cd27cbcf91ef9f90cdfbbe70652d"
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w500"
        const val BACKDROP_BASE_URL = "https://image.tmdb.org/t/p/w780"
    }

    object DB {
        const val DB_NAME = "media.db"
        const val DB_VERSION = 1
        const val TABLE_MOVIE_DETAILS = "movie_details"
        const val TABLE_TV_SHOW_DETAILS = "tv_show_details"
    }

    object UI {
        const val KEY_MOVIE_ID = "MOVIE_ID"
        const val KEY_MOVIE_QUERY = "MOVIE_QUERY"
        const val KEY_TV_SHOW_ID = "TV_SHOW_ID"
        const val KEY_TV_SHOW_QUERY = "TV_SHOW_QUERY"
        const val KEY_MOVIE_FRAGMENT = "MOVIE_FRAGMENT"
        const val KEY_TV_FRAGMENT = "TV_FRAGMENT"
    }
}