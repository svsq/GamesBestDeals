package tk.svsq.gamesbestdeals.data.model

import com.google.gson.annotations.SerializedName

data class StoreApiModel(
    @SerializedName("storeID") val storeID: String,
    @SerializedName("storeName") val storeName: String,
    @SerializedName("isActive") val isActive: Int,
    @SerializedName("images") val images: StoreImagesApiModel? = null
)
