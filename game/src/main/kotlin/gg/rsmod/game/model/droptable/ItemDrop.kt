package gg.rsmod.game.model.droptable

import org.json.JSONObject

class ItemDrop(json: String): JSONObject(json) {
    var itemId: Int = this.optInt("itemId")
    var quanityMin: Int = this.optInt("quanityMin")
    var quanityMax: Int = this.optInt("quanityMax")

}