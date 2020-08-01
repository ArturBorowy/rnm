package pl.arturborowy.rnm.data.remote

import io.reactivex.Single
import pl.arturborowy.rnm.data.remote.characters.model.CharactersListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RnmService {

    companion object {
        const val API_BASE_URL = "https://rickandmortyapi.com/"
    }

    @GET("api/character/")
    fun getCharacters(@Query("page") pageIndex: Int): Single<CharactersListDto>
}