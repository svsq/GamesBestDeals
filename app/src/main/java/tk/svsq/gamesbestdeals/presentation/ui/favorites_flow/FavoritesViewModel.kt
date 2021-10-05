package tk.svsq.gamesbestdeals.presentation.ui.favorites_flow

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import tk.svsq.gamesbestdeals.data.common.Resource
import tk.svsq.gamesbestdeals.domain.interactor.EditAlertUseCase
import tk.svsq.gamesbestdeals.domain.model.favorites.Alert
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val editAlertUseCase: EditAlertUseCase,
) : ViewModel() {

    internal val result = MutableStateFlow(Resource.success(false))

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

}