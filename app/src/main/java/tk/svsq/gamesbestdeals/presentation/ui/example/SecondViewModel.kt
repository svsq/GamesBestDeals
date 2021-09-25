package tk.svsq.gamesbestdeals.presentation.ui.example

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import tk.svsq.gamesbestdeals.domain.interactor.GetStoresListUseCase
import tk.svsq.gamesbestdeals.domain.model.Store
import tk.svsq.gamesbestdeals.presentation.base.Resource
import javax.inject.Inject

@HiltViewModel
class SecondViewModel @Inject constructor(
    private val getStoresListUseCase: GetStoresListUseCase,
) : ViewModel() {

    internal val stores = MutableStateFlow<Resource<List<Store>>>(Resource.success(emptyList()))

    init {
        getStoreList()
    }

    private fun getStoreList() {
        stores.value = Resource.loading()
        getStoresListUseCase {
            it.onSuccess {
                stores.value = Resource.success(it)
            }.onFailure {
                stores.value = Resource.error(it)
            }
        }
    }

}