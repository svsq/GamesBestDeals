package tk.svsq.gamesbestdeals.domain.interactor

import tk.svsq.gamesbestdeals.data.network.tools.exceptions.asRetrofitException
import tk.svsq.gamesbestdeals.domain.repository.PrefRepository
import javax.inject.Inject

class GetFavoriteIdsUseCase @Inject constructor(
    private val prefRepository: PrefRepository,
) : UseCase<UseCase.None, Set<String>>() {

    override suspend fun run(): Result<Set<String>> {
        return try {
            Result.success(prefRepository.getFavoritesGameIds())
        } catch (e: Exception) {
            Result.failure(asRetrofitException(e, null))
        }
    }

}