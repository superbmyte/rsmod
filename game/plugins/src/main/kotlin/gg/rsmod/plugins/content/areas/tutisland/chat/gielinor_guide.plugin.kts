package gg.rsmod.plugins.content.areas.tutisland.chat

import gg.rsmod.plugins.content.areas.tutisland.*

on_npc_option( npc = Npcs.GIELINOR_GUIDE, option = "talk-to") {
    player.queue {
        when(player.getVarp(TUT_COMPLETION_VARP)){
            0,1,2,3,4,5,6 -> {
                player.hintArrow(0,0,0,0) // clear hint arrow
                player.setVarp(TUT_COMPLETION_VARP, 5) // tutorial started
                chatNpc("Greetings! I see you are a new arrival to the world of<br>" +
                        "Gielinor. My job is to welcome all new visitors. So<br>" +
                        "welcome!", animation = 589)
                chatNpc("You have already learned the first thing needed to<br>" +
                        "<br>" +
                        "succeed in the world: talking to other people!", animation = 590)
                chatNpc("You will find many inhabitants of this world have useful<br>" +
                        "things to say to you. By clicking on them you can talk<br>" +
                        "to them.")
                chatNpc("Now then, let's start by looking at your options menu.", animation = 608)
                player.setVarp(TUT_COMPLETION_VARP, 7) // options
                message("<col=0000ff>Options menu</col><br>" +
                        "Please click on the flashing spanner icon found at the bottom right of your screen. This will display your options menu.")
                player.openInterface(InterfaceDestination.SETTINGS)
                player.setVarbit(FLASH_ICON_VARBIT, FLASH_ICON.OPTIONS_ICON+1)
            }
            7 -> {
                chatNpc("Greetings! I see you are a new arrival to the world of" +
                        "Gielinor. My job is to welcome all new visitors. So" +
                        "welcome!")
                player.setVarp(TUT_COMPLETION_VARP, 10)
            }
            else -> chatNpc("You can leave now.")
        }
    }
}