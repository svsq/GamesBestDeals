package tk.svsq.gamesbestdeals.domain.interactor

import tk.svsq.gamesbestdeals.data.network.tools.exceptions.asRetrofitException
import tk.svsq.gamesbestdeals.domain.model.game.Game
import tk.svsq.gamesbestdeals.domain.model.game.GameCount
import tk.svsq.gamesbestdeals.domain.model.game.GameMarker
import tk.svsq.gamesbestdeals.domain.repository.GameRepository
import javax.inject.Inject

class SearchGamesUseCase @Inject constructor(
    private val repository: GameRepository,
) : UseCase<SearchGamesUseCase.Params, List<GameMarker>>() {

    data class Params(
        val title: String,
        val steamAppID: String? = null,
        val limit: Int,
        val exact: Boolean,
    )

    override suspend fun run(): Result<List<GameMarker>> {
        if (params == null) throw IllegalArgumentException("Parameter required")
        return try {
            params!!.run {
                val games = repository.getListOfGames(title, steamAppID, limit, if(exact) 1 else 0).getOrDefault(emptyList())
                Result.success(mergeData(games))
            }
        } catch (e: Exception) {
            Result.failure(asRetrofitException(e, null))
        }
    }

    private fun mergeData(games: List<Game>): List<GameMarker> {
        return mutableListOf<GameMarker>().apply {
            add(GameCount(games.size))
            addAll(games)
        }
    }

}