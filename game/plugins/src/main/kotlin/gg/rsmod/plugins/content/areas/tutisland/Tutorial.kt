package gg.rsmod.plugins.content.areas.tutisland

import gg.rsmod.game.model.Direction
import gg.rsmod.game.model.Tile
import gg.rsmod.game.model.World
import gg.rsmod.game.model.entity.Npc
import gg.rsmod.game.model.entity.Player
import gg.rsmod.plugins.api.InterfaceDestination
import gg.rsmod.plugins.api.cfg.Npcs
import gg.rsmod.plugins.api.ext.*

val TUT_INTERFACE = 614
val TUT_SPAWN = Tile(3094, 3104)

val TUT_COMPLETION_VARP = 281
val TUT_COMPLETED = 1000
val TUT_PROGRESS_VARP = 406

val TUT_REGIONS = arrayOf(12080, 12079, 12335, 12336, 12592)
val TUT_INTERF_BLOCK = arrayOf(InterfaceDestination.ATTACK, InterfaceDestination.SKILLS, InterfaceDestination.QUEST, InterfaceDestination.INVENTORY,
        InterfaceDestination.EQUIPMENT, InterfaceDestination.PRAYER, InterfaceDestination.MAGIC, InterfaceDestination.SOCIAL, InterfaceDestination.ACCOUNT_MANAGEMENT,
        InterfaceDestination.CLAN_CHAT, InterfaceDestination.SETTINGS, InterfaceDestination.EMOTES, InterfaceDestination.MUSIC)

val TUT_HINTS_SCRIPT = 2584

class HINT_CASE{
    companion object {
        const val CHATBOX = 1
        const val CAMERA = 2
        const val MOVE = 3
        const val INTERACT = 4
        const val DEFAULT = CHATBOX
    }
}

val FLASH_ICON_VARBIT = 3756
/**
 * enum values of tab icons [off by one per cs2 3756]
 */
class FLASH_ICON{
    companion object {
        const val COMBAT_ICON = 0 //161:58
        const val STATS_ICON = 1 //161:59
        const val QUESTS_ICON = 2 //161:60
        const val INVENTORY_ICON = 3 //161:61
        const val EQUIPMENT_ICON = 4 //161:62
        const val PRAYER_ICON = 5 //161:63
        const val MAGIC_ICON = 6 //161:64
        const val CLAN_CHAT_ICON = 7 //161:42
        const val IGNORES_ICON = 8 //161:43
        const val FRIENDS_ICON = 9 //161:44
        const val LOGOUT_ICON =  10 //161:45
        const val OPTIONS_ICON =  11 //161:46
        const val EMOTES_ICON = 12 //161:47
        const val MUSIC_ICON = 13 //161:48
    }
}

// important NPC spawns whose index are needed for hint arrows
var GIELINOR_GUIDE_IDX = 0
var SURVIVAL_EXPERT_IDX = 0
var FISHING_SPOT_IDX = 0
var MASTER_CHEF_IDX = 0
var QUEST_GUIDE_IDX = 0

fun spawnNpcs(world: World){
    val GIELINOR_GUIDE = Npc(Npcs.GIELINOR_GUIDE, Tile(3094, 3107), world)
    GIELINOR_GUIDE.respawns = true
    GIELINOR_GUIDE.walkRadius = 3
    GIELINOR_GUIDE.faceDirection(Direction.EAST)
    GIELINOR_GUIDE_IDX = GIELINOR_GUIDE.index
    world.spawn(GIELINOR_GUIDE)

    val SURVIVAL_EXPERT = Npc(Npcs.SURVIVAL_EXPERT, Tile(3103, 3095), world)
    SURVIVAL_EXPERT.respawns = true
    SURVIVAL_EXPERT.walkRadius = 3
    SURVIVAL_EXPERT.faceDirection(Direction.WEST)
    SURVIVAL_EXPERT_IDX = SURVIVAL_EXPERT.index
    world.spawn(SURVIVAL_EXPERT)
    val FISHING_SPOT = Npc(Npcs.FISHING_SPOT_3317, Tile(3101, 3092), world)
    FISHING_SPOT.respawns = true
    FISHING_SPOT.walkRadius = 0
    FISHING_SPOT.faceDirection(Direction.NORTH)
    FISHING_SPOT_IDX = FISHING_SPOT.index
    world.spawn(FISHING_SPOT)

    val MASTER_CHEF = Npc(Npcs.MASTER_CHEF, Tile(3074, 3085), world)
    MASTER_CHEF.respawns = true
    MASTER_CHEF.walkRadius = 2
    MASTER_CHEF.faceDirection(Direction.EAST)
    MASTER_CHEF_IDX = MASTER_CHEF.index
    world.spawn(MASTER_CHEF)

    val QUEST_GUIDE = Npc(Npcs.QUEST_GUIDE, Tile(3074, 3085), world)
    QUEST_GUIDE.respawns = true
    QUEST_GUIDE.walkRadius = 3
    QUEST_GUIDE.faceDirection(Direction.NORTH)
    QUEST_GUIDE_IDX = QUEST_GUIDE.index
    world.spawn(QUEST_GUIDE)
}

/**
 * used for opening the already unlocked interfaces in case of relog.
 */
fun openUnlockedInterfaces(player: Player){
    val tutCompletion = player.getVarp(TUT_COMPLETION_VARP)
    if(tutCompletion >= 7)
        player.openInterface(InterfaceDestination.SETTINGS)
}

fun tutStep(player: Player){
    val tutCompletion = player.getVarp(TUT_COMPLETION_VARP)
    if(tutCompletion != TUT_COMPLETED){
        player.queue {
            if(tutCompletion < 7){
                message("<col=0000ff>Getting started</col><br>" +
                        "Before you begin, have a read through the controls guide in the " +
                        "top left of the screen. When you're ready to get started, click on " +
                        "the Gielinor Guide. He is indicated by a flashing yellow arrow.")
                player.hintNpc(GIELINOR_GUIDE_IDX)
            }
            else if(tutCompletion < 10){
                message("<col=0000ff>Options menu</col><br>" +
                        "Please click on the flashing spanner icon found at the bottom right of your screen. This will display your options menu.")
                player.openInterface(InterfaceDestination.SETTINGS)
                player.setVarbit(FLASH_ICON_VARBIT, FLASH_ICON.OPTIONS_ICON+1) //enum is off by one
            }

        }
    }
}
