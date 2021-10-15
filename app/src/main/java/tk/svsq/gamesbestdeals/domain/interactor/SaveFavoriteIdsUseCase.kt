package tk.svsq.gamesbestdeals.domain.interactor

import tk.svsq.gamesbestdeals.data.network.tools.exceptions.asRetrofitException
import tk.svsq.gamesbestdeals.domain.repository.PrefRepository
import javax.inject.Inject

class SaveFavoriteIdsUseCase @Inject constructor(
    private val prefRepository: PrefRepository,
) : UseCase<SaveFavoriteIdsUseCase.Params, Unit>() {

    data class Params(val id: String)

    override suspend fun run(): Result<Unit> {
        if (params == null) throw IllegalArgumentException("Parameter required")
        return try {
            Result.success(prefRepository.saveFavoritesGameIds(params!!.id))
        } catch (e: Exception) {
            Result.failure(asRetrofitException(e, null))
        }
    }

}