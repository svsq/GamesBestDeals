package tk.svsq.gamesbestdeals.data.repository

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import tk.svsq.gamesbestdeals.domain.repository.PrefRepository
import javax.inject.Inject

class PrefDataRepository @Inject constructor(
    val context: Context
) : PrefRepository {

    companion object {
        private const val FAV_GAME_IDS = "favGameIds"
    }

    private val pref =
        context.getSharedPreferences(context.packageName + "_fav", Context.MODE_PRIVATE)

    override suspend fun saveFavoritesGameIds(id: String) {
        val items = mutableSetOf<String>()
        items.addAll(getFavoritesGameIds())
        items.add(id)
        val gson = Gson()
        val value: String = gson.toJson(items)
        pref.edit(commit = true) { putString(FAV_GAME_IDS, value) }
    }

    override suspend fun getFavoritesGameIds(): Set<String> {
        val gson = Gson()
        val json = pref.getString(FAV_GAME_IDS, "").orEmpty()
        val type = object : TypeToken<Set<String>?>() {}.type
        val predict: Set<String> = gson.fromJson(json, type) ?: setOf()
        return predict
    }

    override suspend fun clearAll() {
        pref.edit().clear().apply()
    }

}