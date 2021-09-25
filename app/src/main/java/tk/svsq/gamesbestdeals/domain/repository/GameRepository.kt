package tk.svsq.gamesbestdeals.domain.repository

import tk.svsq.gamesbestdeals.domain.model.Store

interface GameRepository {

    suspend fun getStoresList(): Result<List<Store>>

}