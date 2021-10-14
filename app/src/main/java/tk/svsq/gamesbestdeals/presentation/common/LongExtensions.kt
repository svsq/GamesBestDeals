package tk.svsq.gamesbestdeals.presentation.common

import java.text.SimpleDateFormat
import java.util.*


fun Long.toDateString(): String {
    return try {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val netDate = Date(this * 1000)
        sdf.format(netDate)
    } catch (e: Exception) {
        e.toString()
    }
}