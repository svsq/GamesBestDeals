package tk.svsq.gamesbestdeals.domain.interactor

import tk.svsq.gamesbestdeals.data.network.tools.exceptions.asRetrofitException
import tk.svsq.gamesbestdeals.domain.model.game.Game
import tk.svsq.gamesbestdeals.domain.repository.GameRepository
import javax.inject.Inject

class SearchGamesUseCase @Inject constructor(
    private val repository: GameRepository,
) : UseCase<SearchGamesUseCase.Params, List<Game>>() {

    data class Params(
        val title: String,
        val steamAppID: String? = null,
        val limit: Int,
        val exact: Boolean,
    )

    override suspend fun run(): Result<List<Game>> {
        if (params == null) throw IllegalArgumentException("Parameter required")
        return try {
            params!!.run {
                repository.getListOfGames(title, steamAppID, limit, if(exact) 1 else 0)
            }
        } catch (e: Exception) {
            Result.failure(asRetrofitException(e, null))
        }
    }

}