package tk.svsq.gamesbestdeals.presentation.ui.search

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import tk.svsq.gamesbestdeals.data.common.Resource
import tk.svsq.gamesbestdeals.domain.interactor.SearchGamesUseCase
import tk.svsq.gamesbestdeals.domain.model.game.GameMarker
import tk.svsq.gamesbestdeals.domain.model.game.SearchParams
import javax.inject.Inject

@HiltViewModel
class SearchGamesViewModel @Inject constructor(
    private val searchGamesUseCase: SearchGamesUseCase,
) : ViewModel() {

    internal val games = MutableStateFlow<Resource<List<GameMarker>>>(Resource.loading())

    internal var searchParams: SearchParams = SearchParams("", null)

    internal fun searchGames() {
        val params = SearchGamesUseCase.Params(searchParams)
        searchGamesUseCase(params) {
            it.onSuccess {
                games.value = Resource.success(it)
            }.onFailure {
                games.value = Resource.error(it)
            }
        }
    }

}