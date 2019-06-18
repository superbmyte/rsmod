package gg.rsmod.plugins.content.areas.tutisland

import gg.rsmod.game.event.FirstCameraMoveEvent
import gg.rsmod.game.event.FirstMoveEvent
import gg.rsmod.game.model.timer.HINT_POPUP_DURATION_TIMER

val hintTimer = HINT_POPUP_DURATION_TIMER

on_world_init {
    spawnNpcs(world)
}

on_login {
    val tutCompletion = player.getVarp(TUT_COMPLETION_VARP)
    if(tutCompletion != TUT_COMPLETED){
        player.openInterface(TUT_INTERFACE, InterfaceDestination.WALKABLE)
        openUnlockedInterfaces(player)
        player.queue {
            waitInterfaceClose(APPEARANCE_INTERFACE_ID)
            player.timers[hintTimer] = 10
            tutStep(player)
        }
    }
}

on_enter_region(12336) {
    player.playSong(62) // newbie melody
}

on_timer(hintTimer){
    val tutCompletion = player.getVarp(TUT_COMPLETION_VARP)
    if(tutCompletion < 6){
        var HINT_SELECTION = -1
        when(tutCompletion){
            0,1 -> {
                HINT_SELECTION = HINT_CASE.CAMERA
                player.timers[hintTimer] = 15
            }
            2 -> {
                HINT_SELECTION = HINT_CASE.MOVE
                player.timers[hintTimer] = 25
            }
            3 -> {
                HINT_SELECTION = HINT_CASE.INTERACT
                player.timers[hintTimer] = 20
            }
            4 -> {
                HINT_SELECTION = HINT_CASE.CHATBOX
                player.timers[hintTimer] = 20
            }
            else -> {
                HINT_SELECTION = HINT_CASE.DEFAULT
                player.timers[hintTimer] = 10
            }
        }
        player.runClientScript(2584, HINT_SELECTION)
        //player.setVarp(TUT_COMPLETION_VARP, tutCompletion+1)
    }
    else
        player.setComponentHidden(614, 6, true)
}

on_event(FirstCameraMoveEvent::class.java){
    player.setComponentHidden(614, 6, true)
    player.setVarp(TUT_COMPLETION_VARP, 2)
}

on_event(FirstMoveEvent::class.java){
    player.setComponentHidden(614, 6, true)
    player.setVarp(TUT_COMPLETION_VARP, 3)
}
