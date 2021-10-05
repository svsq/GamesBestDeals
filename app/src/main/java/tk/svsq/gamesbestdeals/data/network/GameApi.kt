package tk.svsq.gamesbestdeals.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import tk.svsq.gamesbestdeals.data.model.DealApiModel
import tk.svsq.gamesbestdeals.data.model.StoreApiModel

interface GameApi {

    @GET("stores")
    suspend fun getStoresList(): Response<List<StoreApiModel>>

    @GET("deals")
    suspend fun getDealsList(
        @Query("storeId") storeId: String,
        @Query("upperPrice") upperPrice: String): Response<List<DealApiModel>>

}