package pl.arturborowy.rnm.character.details

import io.mockk.*
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.get
import org.koin.test.inject
import pl.arturborowy.rnm.base.di.definitionModule
import pl.arturborowy.rnm.base.error.ThrowableHandler
import pl.arturborowy.rnm.base.rx.RemoteFetchSchedulerProvider
import pl.arturborowy.rnm.base.rx.SchedulerProvider
import pl.arturborowy.rnm.character.list.StubCharactersData
import pl.arturborowy.rnm.data.remote.RnmService
import pl.arturborowy.rnm.domain.characters.CharactersInteractor
import pl.arturborowy.rnm.testutils.MockSchedulerProvider

class CharacterDetailsViewModelTest : AutoCloseKoinTest() {

    private val mockRnmService = mockk<RnmService>()
    private val mockThrowableHandler = mockk<ThrowableHandler>()

    private val characterDetailsViewModel
            by inject<CharacterDetailsViewModel>()

    @Before
    fun setUp() {
        startKoin {
            modules(definitionModule + module {
                single<SchedulerProvider>(
                    override = true,
                    qualifier = named<RemoteFetchSchedulerProvider>()
                ) { MockSchedulerProvider() }

                single(override = true) { mockRnmService }
                single(override = true) { mockThrowableHandler }
            })
        }
    }

    @Test
    fun `onViewCreated sets correct values to ObservableFields when character is cached`() {
        get<CharactersInteractor>().cacheCharacter(StubCharactersData.CHARACTER.ENTITY).test()

        characterDetailsViewModel.onViewCreated()

        assertEquals(
            StubCharactersData.CHARACTER.NAME,
            characterDetailsViewModel.name.get()
        )
        assertEquals(
            StubCharactersData.CHARACTER.IMAGE_URL,
            characterDetailsViewModel.avatarUrl.get()
        )
        assertEquals(
            StubCharactersData.CHARACTER.GENDER,
            characterDetailsViewModel.gender.get()
        )
        assertEquals(
            StubCharactersData.CHARACTER.STATUS,
            characterDetailsViewModel.statusOfLife.get()
        )
        assertEquals(
            StubCharactersData.CHARACTER.CURRENT_LOCATION_NAME,
            characterDetailsViewModel.currentLocationName.get()
        )
        assertEquals(
            StubCharactersData.CHARACTER.ORIGIN_LOCATION_NAME,
            characterDetailsViewModel.originLocationName.get()
        )
    }

    @Test
    fun `onViewCreated calls handle on throwableHandler when there is no cached character`() {
        every { mockThrowableHandler.handle(any()) } just Runs

        characterDetailsViewModel.onViewCreated()

        verify(exactly = 1) { mockThrowableHandler.handle(any()) }
    }
}