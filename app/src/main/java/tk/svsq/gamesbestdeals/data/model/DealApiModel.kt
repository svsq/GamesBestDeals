package tk.svsq.gamesbestdeals.data.model

import com.google.gson.annotations.SerializedName

data class DealApiModel(
    @SerializedName("internalName") val internalName: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("metacriticLink") val metacriticLink: String?,
    @SerializedName("dealID") val dealID: String?,
    @SerializedName("storeID") val storeID: String?,
    @SerializedName("gameID") val gameID: String?,
    @SerializedName("salePrice") val salePrice: String?,
    @SerializedName("normalPrice") val normalPrice: String?,
    @SerializedName("isOnSale") val isOnSale: String?,
    @SerializedName("savings") val savings: String?,
    @SerializedName("metacriticScore") val metacriticScore: String?,
    @SerializedName("steamRatingText") val steamRatingText: String?,
    @SerializedName("steamRatingPercent") val steamRatingPercent: String?,
    @SerializedName("steamRatingCount") val steamRatingCount: String?,
    @SerializedName("steamAppID") val steamAppID: String?,
    @SerializedName("releaseDate") val releaseDate: Long?,
    @SerializedName("lastChange") val lastChange: Long?,
    @SerializedName("dealRating") val dealRating: String?,
    @SerializedName("thumb") val thumb: String?
)