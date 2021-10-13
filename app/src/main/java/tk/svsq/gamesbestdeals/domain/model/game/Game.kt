package tk.svsq.gamesbestdeals.domain.model.game

import tk.svsq.gamesbestdeals.presentation.ui.search.items.GameMarker

data class Game(
    val gameID: String,
    val steamAppID: String? = null,
    val cheapest: String,
    val cheapestDealID: String,
    val external: String,
    val internalName: String,
    val thumb: String,
) : GameMarker