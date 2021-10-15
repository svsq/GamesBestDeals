package tk.svsq.gamesbestdeals.presentation.ui.favorites_flow

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import tk.svsq.gamesbestdeals.data.common.Resource
import tk.svsq.gamesbestdeals.domain.interactor.EditAlertUseCase
import tk.svsq.gamesbestdeals.domain.interactor.GetFavoriteIdsUseCase
import tk.svsq.gamesbestdeals.domain.interactor.SaveFavoriteIdsUseCase
import tk.svsq.gamesbestdeals.domain.model.favorites.Alert
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val editAlertUseCase: EditAlertUseCase,
    private val saveFavoriteIdsUseCase: SaveFavoriteIdsUseCase,
    private val getFavoriteIdsUseCase: GetFavoriteIdsUseCase,
) : ViewModel() {

    internal val result = MutableStateFlow<Resource<Boolean>>(Resource.loading())
    internal val favoritesIds = MutableStateFlow<Resource<Set<String>>>(Resource.success(emptySet()))

    internal fun editAlert(item: Alert) {
            result.value = Resource.loading()
            val params = EditAlertUseCase.Params(item)
            editAlertUseCase(params) {
                it.onSuccess {
                    result.value = Resource.success(it)
                }.onFailure {
                    result.value = Resource.error(it)
                }
            }
    }

    internal fun saveId(id: String) {
        val params = SaveFavoriteIdsUseCase.Params(id)
        saveFavoriteIdsUseCase(params) {
            it.onSuccess {

            }.onFailure {

            }
        }
    }

    internal fun getIds() {
        getFavoriteIdsUseCase {
            it.onSuccess {
                favoritesIds.value = Resource.success(it)
            }.onFailure {

            }
        }
    }

}