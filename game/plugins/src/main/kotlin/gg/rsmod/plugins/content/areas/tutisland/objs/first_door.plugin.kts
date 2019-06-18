package gg.rsmod.plugins.content.areas.tutisland.objs

on_obj_option(9398, "open") {
    val out = player.tile.x == 3097
    val endTile = if(out) player.tile.step(Direction.EAST, 1) else player.tile.step(Direction.WEST, 1)

    player.walkTo(endTile)
    player.playSound(62)
}