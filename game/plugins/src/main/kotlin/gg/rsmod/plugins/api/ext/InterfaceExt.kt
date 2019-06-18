package gg.rsmod.plugins.api.ext

import gg.rsmod.game.model.entity.Player
import java.util.*

/**
 * Gets the 'interface hash' of a given interface id and
 * child component. This value is commonly used in ClientScripts when referring
 * to a child component in the game. An 'interface hash' is in the format of (parent >> 16) | child
 *
 * Example: 335.getInterfaceHash(25) would return 21954585, which is the 'absolute' id of the component
 *
 * @param child     The child component
 */
fun Int.getInterfaceHash(child: Int = -1) : Int {
    val value = (this shl 16)
    if (child != -1) return value or child
    return value
}

/**
 * Create an [EnumSet] made up of [values].
 *
 * @param values the default values stored in our set.
 */
fun Player.flashComponent(parent: Int, child: Int, times: Int){
    queue {
        for(i in 1..times){
            setComponentHidden(parent, child, true)
            wait(1)
            setComponentHidden(parent, child, false)
            wait(1)
        }
    }
}