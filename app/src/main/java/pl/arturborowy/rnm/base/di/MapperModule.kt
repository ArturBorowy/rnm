package pl.arturborowy.rnm.base.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import pl.arturborowy.rnm.base.date.YearMonthDayHourMinuteSecondMillisStringToDateConverter
import pl.arturborowy.rnm.data.remote.characters.mapper.*

val mapperModule = module {
    single {
        CharacterDetailsDtoToEntityMapper(
            get(named<YearMonthDayHourMinuteSecondMillisStringToDateConverter>()),
            get(),
            get()
        )
    }
    single { OriginLocationDtoToEntityMapper() }
    single { PagingInfoDtoToEntityMapper() }
    single { CurrentLocationDtoToEntityMapper() }
    single { CharacterListDtoToEntityMapper(get(), get()) }
}