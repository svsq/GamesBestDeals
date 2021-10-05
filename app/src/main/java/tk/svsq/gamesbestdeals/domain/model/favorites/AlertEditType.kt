package tk.svsq.gamesbestdeals.domain.model.favorites

import tk.svsq.gamesbestdeals.domain.model.base.TypeEnum

enum class AlertEditType(override val type: String) : TypeEnum {
    SET("set"),
    DELETE("delete")
}