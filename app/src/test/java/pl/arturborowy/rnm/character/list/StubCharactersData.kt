package pl.arturborowy.rnm.character.list

import pl.arturborowy.rnm.data.remote.characters.model.*
import pl.arturborowy.rnm.domain.characters.model.CharacterDetailsEntity
import pl.arturborowy.rnm.domain.characters.model.CharacterListEntity
import pl.arturborowy.rnm.domain.characters.model.CharacterLocationEntity
import pl.arturborowy.rnm.domain.characters.model.PagingInfoEntity
import java.util.*

object StubCharactersData {

    object PAGING {
        private const val COUNT = 40
        private const val NEXT = "url/to/next"
        private const val PAGES = 2
        private const val PREVIOUS = "url/to/previous"

        val DTO = PagingInfoDto(COUNT, NEXT, PAGES, PREVIOUS)
        val ENTITY = PagingInfoEntity(COUNT, NEXT, PAGES, PREVIOUS)
    }

    object CHARACTER {
        private const val CREATED_STRING = "2017-11-04T18:48:46.250Z"
        private val EPISODES = listOf("EP1", "EP2")
        const val GENDER = "FEMALE"
        private const val ID = 1234
        const val IMAGE_URL = "image/url"
        const val CURRENT_LOCATION_NAME = "Paris"
        private const val CURRENT_LOCATION_URL = "url/to/paris"
        private val CURRENT_LOCATION_DTO = CurrentLocationDto(CURRENT_LOCATION_NAME, CURRENT_LOCATION_URL)
        const val NAME = "Thomas Vinterberg"
        const val ORIGIN_LOCATION_NAME = "Venice"
        private const val ORIGIN_LOCATION_URL = "url/to/venice"
        private val ORIGIN_LOCATION_DTO = OriginLocationDto(ORIGIN_LOCATION_NAME, ORIGIN_LOCATION_URL)
        private const val SPICES = "Fox"
        const val STATUS = "Alive"
        private const val TYPE = "unknown"
        private const val URL = "url/to/character"

        val DTO = CharacterDetailsDto(
            CREATED_STRING,
            EPISODES,
            GENDER,
            ID,
            IMAGE_URL,
            CURRENT_LOCATION_DTO,
            NAME,
            ORIGIN_LOCATION_DTO,
            SPICES,
            STATUS,
            TYPE,
            URL
        )

        private val CREATED_DATE =
            getDate(2017, 10, 4, 48, 18, 46, 250, 0)
        private val CURRENT_LOCATION_ENTITY =
            CharacterLocationEntity(CURRENT_LOCATION_NAME, CURRENT_LOCATION_URL)
        private val ORIGIN_LOCATION_ENTITY =
            CharacterLocationEntity(ORIGIN_LOCATION_NAME, ORIGIN_LOCATION_URL)

        val ENTITY = CharacterDetailsEntity(
            CREATED_DATE,
            EPISODES,
            GENDER,
            ID,
            IMAGE_URL,
            CURRENT_LOCATION_ENTITY,
            NAME,
            ORIGIN_LOCATION_ENTITY,
            SPICES,
            STATUS,
            TYPE,
            URL
        )
    }

    object LIST {

        val DTO = CharactersListDto(PAGING.DTO, listOf(CHARACTER.DTO))

        val ENTITY = CharacterListEntity(PAGING.ENTITY, listOf(CHARACTER.ENTITY))
    }

    private fun getDate(
        year: Int,
        month: Int,
        dayOfMonth: Int,
        minute: Int = 0,
        hour: Int = 0,
        second: Int = 0,
        millisecond: Int = 0,
        dayOfWeek: Int = 0
    ): Date {
        val givenLocale = Locale.US
        val calendar = Calendar.getInstance(givenLocale)
        calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek)
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.SECOND, second)
        calendar.set(Calendar.MILLISECOND, millisecond)
        return calendar.time
    }
}