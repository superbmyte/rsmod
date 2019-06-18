package gg.rsmod.plugins.content.mechanics.appearance

import gg.rsmod.game.model.attr.NEW_ACCOUNT_ATTR
import gg.rsmod.game.sync.block.UpdateBlockType
import gg.rsmod.plugins.content.areas.tutisland.TUT_COMPLETED
import gg.rsmod.plugins.content.areas.tutisland.TUT_COMPLETION_VARP
import gg.rsmod.plugins.content.areas.tutisland.TUT_INTERFACE

on_login {
    if (player.attr[NEW_ACCOUNT_ATTR] == true) {
        player.queue(TaskPriority.WEAK) {
            message("<col=0000ff>Setting your appearance</col><br>" +
                    "Before you get started you'll need to set the appearance of your " +
                    "character. Please use the open interface to set your appearance.")
            val appearance = selectAppearance() ?: return@queue
            player.appearance = appearance
            player.addBlock(UpdateBlockType.APPEARANCE)
            player.attr[NEW_ACCOUNT_ATTR] = false
            if(player.getVarp(TUT_COMPLETION_VARP) != TUT_COMPLETED) // tutorial not completed
                player.openInterface(TUT_INTERFACE, InterfaceDestination.WALKABLE)
        }
    }
}