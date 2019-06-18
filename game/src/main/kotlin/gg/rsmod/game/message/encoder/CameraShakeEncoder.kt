package gg.rsmod.game.message.encoder

import gg.rsmod.game.message.MessageEncoder
import gg.rsmod.game.message.impl.CameraShakeMessage

/**
 * @author bmyte <bmytescape@gmail.com>
 */
class CameraShakeEncoder : MessageEncoder<CameraShakeMessage>() {

    override fun extract(message: CameraShakeMessage, key: String): Number = when (key) {
        "index" -> message.index
        "left" -> message.left
        "center" -> message.center
        "right" -> message.right
        else -> throw Exception("Unhandled value key.")
    }

    override fun extractBytes(message: CameraShakeMessage, key: String): ByteArray = throw Exception("Unhandled value key.")
}