package tk.svsq.gamesbestdeals.domain.interactor

import tk.svsq.gamesbestdeals.data.network.tools.exceptions.asRetrofitException
import tk.svsq.gamesbestdeals.domain.model.Deal
import tk.svsq.gamesbestdeals.domain.repository.GameRepository
import javax.inject.Inject

class GetDealsListUseCase @Inject constructor(
    private val repository: GameRepository,
) : UseCase<UseCase.None, List<Deal>>() {

    override suspend fun run(): Result<List<Deal>> {
        return try {
            repository.getDealsList("1", "15") // TODO change hardcoded params to real ones
        } catch (e: Exception) {
            Result.failure(asRetrofitException(e, null))
        }
    }
}