package pl.arturborowy.rnm.character.list

import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import pl.arturborowy.rnm.base.di.definitionModule
import pl.arturborowy.rnm.base.rx.RemoteFetchSchedulerProvider
import pl.arturborowy.rnm.base.rx.SchedulerProvider
import pl.arturborowy.rnm.data.remote.RnmService
import pl.arturborowy.rnm.testutils.MockSchedulerProvider

class CharacterListViewModelTest : AutoCloseKoinTest() {

    private val mockRnmService = mockk<RnmService>()

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
            })
        }
    }

    @Test
    fun `onViewCreated sets characters property`() {
        every { mockRnmService.getCharacters() } returns Single.just(StubCharactersData.LIST.DTO)

        characterListViewModel.onViewCreated()

        assertEquals(
            listOf(StubCharactersData.CHARACTER.ENTITY),
            characterListViewModel.characters
        )
    }
}