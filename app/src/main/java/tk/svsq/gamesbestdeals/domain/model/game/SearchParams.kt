package tk.svsq.gamesbestdeals.domain.model.game

data class SearchParams(
    var query: String,
    var steamAppID: String? = null,
    var limit: Int = 60,
    var exact: Boolean = false
)
