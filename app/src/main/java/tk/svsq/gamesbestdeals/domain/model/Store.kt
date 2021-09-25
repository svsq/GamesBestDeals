package tk.svsq.gamesbestdeals.domain.model

data class Store(
    val storeID: String,
    val storeName: String,
    val isActive: Boolean,
    val images: StoreImages? = null
)