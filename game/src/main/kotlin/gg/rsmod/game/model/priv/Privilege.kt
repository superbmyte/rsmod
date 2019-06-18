package gg.rsmod.game.model.priv

/**
 * Represents privilege levels.
 *
 * @param id
 * The unique id of the privilege
 *
 * @param icon
 * The icon id that is used on the client to show the proper crown for the
 * privilege.
 *
 * @param name
 * The name of the privilege.
 *
 * @param powers
 * The name of the "powers" that this privilege has access to.
 *
 * @author Tom <rspsmods@gmail.com>
 */
data class Privilege(val id: Int, val icon: Int, val name: String, val powers: Set<String>) {

    companion object {

        /**
         * The global identifier used for players. This identifier should be
         * used globally to identify a player with player privileges.
         */
        const val PLAYER_POWER = "player" // 0

        /**
         * The global identifier used for player moderators. This identifier should be
         * used globally to identify a player with moderator privileges.
         */
        const val MOD_POWER = "mod" // 1

        /**
         * The global identifier used for developers. This identifier should be
         * used globally to identify a player with developer privileges.
         */
        const val DEV_POWER = "dev" // 2

        /**
         * The global identifier used for administrators. This identifier should
         * be used globally to identify a player with administrator privileges.
         */
        const val ADMIN_POWER = "admin" // 3

        /**
         * The global identifier used for owners. This identifier should be
         * used globally to identify a player with owner privileges.
         */
        const val OWNER_POWER = "owner" // 4

        /**
         * The default privilege level.
         */
        val DEFAULT = Privilege(id = 0, icon = 0, name = "player", powers = setOf("player")) // default is a player
    }
}