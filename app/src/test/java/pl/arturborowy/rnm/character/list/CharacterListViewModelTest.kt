package pl.arturborowy.rnm.character.list

import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.get
import org.koin.test.inject
import pl.arturborowy.rnm.R
import pl.arturborowy.rnm.base.di.definitionModule
import pl.arturborowy.rnm.base.error.ThrowableHandler
import pl.arturborowy.rnm.base.rx.RemoteFetchSchedulerProvider
import pl.arturborowy.rnm.base.rx.SchedulerProvider
import pl.arturborowy.rnm.data.remote.RnmService
import pl.arturborowy.rnm.domain.characters.CharactersInteractor
import pl.arturborowy.rnm.domain.characters.model.CharacterDetailsEntity
import pl.arturborowy.rnm.testutils.MockSchedulerProvider

class CharacterListViewModelTest : AutoCloseKoinTest() {

    private val mockRnmService = mockk<RnmService>()
    private val mockThrowableHandler = mockk<ThrowableHandler>()

    private val characterListViewModel by inject<CharacterListViewModel>()

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
    fun `onCharacterClick sets action_characterList_to_characterDetails to desiredDestination`() {
        val character = mockk<CharacterDetailsEntity>()

        characterListViewModel.onCharacterClick(character)

        assertEquals(
            R.id.action_characterList_to_characterDetails,
            characterListViewModel.desiredDestination.get()
        )
    }

    @Test
    fun `onCharacterClick caches character`() {
        val character = mockk<CharacterDetailsEntity>()

        characterListViewModel.onCharacterClick(character)

        get<CharactersInteractor>()
            .getCachedCharacter()
            .test()
            .assertValue(character)
    }
}