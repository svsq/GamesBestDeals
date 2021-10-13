package tk.svsq.gamesbestdeals.presentation.common

import java.text.SimpleDateFormat
import java.util.*


fun Long.toDateString(): String {
    try {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val netDate = Date(this * 1000)
        return sdf.format(netDate)
    } catch (e: Exception) {
        return e.toString()
    }
}