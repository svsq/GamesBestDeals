package tk.svsq.gamesbestdeals.domain.interactor

import tk.svsq.gamesbestdeals.data.network.tools.exceptions.asRetrofitException
import tk.svsq.gamesbestdeals.domain.model.Store
import tk.svsq.gamesbestdeals.domain.repository.GameRepository
import javax.inject.Inject

class GetStoresListUseCase @Inject constructor(
    private val repository: GameRepository,
) : UseCase<UseCase.None, List<Store>>() {

    override suspend fun run(): Result<List<Store>> {
        return try {
            repository.getStoresList()
        } catch (e: Exception) {
            Result.failure(asRetrofitException(e, null))
        }
    }

}