package tk.svsq.gamesbestdeals.presentation.ui.deals

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import tk.svsq.gamesbestdeals.data.common.Resource
import tk.svsq.gamesbestdeals.domain.interactor.GetDealsListUseCase
import tk.svsq.gamesbestdeals.domain.model.Deal
import javax.inject.Inject

@HiltViewModel
class DealsViewModel @Inject constructor(
    private val getDealsListUseCase: GetDealsListUseCase
) : ViewModel() {

    internal val deals = MutableStateFlow<Resource<List<Deal>>>(Resource.success(emptyList()))

    init {
        getDealsList()
    }

    private fun getDealsList() {
        deals.value = Resource.loading()
        getDealsListUseCase {
            it.onSuccess {
                deals.value = Resource.success(it)
            }.onFailure {
                deals.value = Resource.error(it)
            }
        }
    }
}