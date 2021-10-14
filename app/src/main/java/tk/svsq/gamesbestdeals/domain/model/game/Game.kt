package tk.svsq.gamesbestdeals.domain.model.game

data class Game(
    val gameID: String,
    val steamAppID: String? = null,
    val cheapest: String,
    val cheapestDealID: String,
    val external: String,
    val internalName: String,
    val thumb: String,
) : GameMarker