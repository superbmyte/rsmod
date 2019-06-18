package gg.rsmod.game.message.handler

import gg.rsmod.game.event.FirstCameraMoveEvent
import gg.rsmod.game.message.MessageHandler
import gg.rsmod.game.message.impl.EventCameraPositionMessage
import gg.rsmod.game.model.World
import gg.rsmod.game.model.entity.Client
import gg.rsmod.game.model.entity.Player

/**
 * @author Tom <rspsmods@gmail.com>
 */
class EventCameraPositionHandler : MessageHandler<EventCameraPositionMessage> {

    override fun handle(client: Client, world: World, message: EventCameraPositionMessage) {
        val player = client as Player
        if(player.varps[281].state < 2)
            player.triggerEvent(FirstCameraMoveEvent)
        client.cameraPitch = message.pitch
        client.cameraYaw = message.yaw
    }
}