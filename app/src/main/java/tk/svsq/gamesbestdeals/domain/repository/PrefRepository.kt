package tk.svsq.gamesbestdeals.domain.repository

interface PrefRepository {

    suspend fun saveFavoritesGameIds(id: String)

    suspend fun getFavoritesGameIds(): Set<String>

    suspend fun clearAll()

}