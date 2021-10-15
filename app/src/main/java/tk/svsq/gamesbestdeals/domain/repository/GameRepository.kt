package tk.svsq.gamesbestdeals.domain.repository

import tk.svsq.gamesbestdeals.domain.model.favorites.Alert
import tk.svsq.gamesbestdeals.domain.model.Deal
import tk.svsq.gamesbestdeals.domain.model.game.Game
import tk.svsq.gamesbestdeals.domain.model.Store

interface GameRepository {

    suspend fun getStoresList(): Result<List<Store>>

    suspend fun editAlert(item: Alert): Result<Boolean>

    suspend fun getDealsList(storeId: String, upperPrice: String): Result<List<Deal>>

    suspend fun getListOfGames(
        title: String,
        steamAppID: String? = null,
        limit: Int,
        exact: Int,
    ): Result<List<Game>>

}