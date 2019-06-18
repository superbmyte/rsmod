package gg.rsmod.game.message.encoder

import gg.rsmod.game.message.MessageEncoder
import gg.rsmod.game.message.impl.HintArrowMessage

/**
 * @author bmyte <bmytescape@gmail.com>
 */
class HintArrowEncoder : MessageEncoder<HintArrowMessage>() {

    override fun extract(message: HintArrowMessage, key: String): Number = when (key) {
        "arrow_type" -> message.arrowType
        "index_or_x" -> message.IdxOrX
        "arrow_y" -> message.arrowY
        "offset_z" -> message.offsetZ
        else -> throw Exception("Unhandled value key.")
    }

    override fun extractBytes(message: HintArrowMessage, key: String): ByteArray = throw Exception("Unhandled value key.")
}