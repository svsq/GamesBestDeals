package tk.svsq.gamesbestdeals.presentation.ui.search

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import tk.svsq.gamesbestdeals.data.common.Resource
import tk.svsq.gamesbestdeals.domain.interactor.SearchGamesUseCase
import tk.svsq.gamesbestdeals.domain.model.game.Game
import javax.inject.Inject

@HiltViewModel
class SearchGamesViewModel @Inject constructor(
    private val searchGamesUseCase: SearchGamesUseCase,
) : ViewModel() {

    internal val games = MutableStateFlow<Resource<List<Game>>>(Resource.success(emptyList()))

    internal fun searchGames(query: String, steamAppID: String? = null, limit: Int, exact: Boolean) {
        val params = SearchGamesUseCase.Params(query, steamAppID, limit, exact)
        searchGamesUseCase(params) {
            it.onSuccess {
                games.value = Resource.success(it)
            }.onFailure {
                games.value = Resource.error(it)
            }
        }
    }

}