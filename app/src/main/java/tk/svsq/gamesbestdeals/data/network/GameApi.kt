package tk.svsq.gamesbestdeals.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import tk.svsq.gamesbestdeals.data.model.DealApiModel
import tk.svsq.gamesbestdeals.data.model.GameApiModel
import tk.svsq.gamesbestdeals.data.model.StoreApiModel

interface GameApi {

    @GET("stores")
    suspend fun getStoresList(): Response<List<StoreApiModel>>

    @GET("alerts")
    suspend fun editAlert(
        @Query("action") action: String,
        @Query("email") email: String,
        @Query("gameID") gameID: Int,
        @Query("price") price: Float,
    ): Response<Boolean>

    @GET("deals")
    suspend fun getDealsList(
        @Query("storeId") storeId: String,
        @Query("upperPrice") upperPrice: String
    ): Response<List<DealApiModel>>

    @GET("games")
    suspend fun getListOfGames(
        @Query("title") title: String,
        @Query("steamAppID") steamAppID: String? = null,
        @Query("limit") limit: Int,
        @Query("exact") exact: Int,
    ): Response<List<GameApiModel>>

}