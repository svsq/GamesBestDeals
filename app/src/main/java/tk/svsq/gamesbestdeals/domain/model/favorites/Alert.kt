package tk.svsq.gamesbestdeals.domain.model.favorites

data class Alert(
    val action: AlertEditType,
    val email: String,
    val gameID: Int,
    val price: Float,
)