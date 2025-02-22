package org.example.pokeverse.arena.data

sealed interface Action {
    val timestamp: String
    val image: String
    val title: String
    val metaData: MetaData

    open class Attack(
        override val timestamp: String,
        override val image: String,
        override val title: String,
        override val metaData: MetaData,
        open val damage: Float
    ) : Action {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Attack) return false

            return timestamp == other.timestamp &&
                    image == other.image &&
                    metaData == other.metaData &&
                    damage == other.damage
        }

        override fun hashCode(): Int {
            return timestamp.hashCode() * 31 +
                    image.hashCode() * 31 +
                    metaData.hashCode() * 31 +
                    damage.hashCode()
        }
    }

    data class SpecialAttack(
        override val timestamp: String,
        override val image: String,
        override val title: String,
        override val metaData: MetaData,
        override val damage: Float,
    ) : Attack(timestamp, image, title, metaData, damage)

    data class Defence(
        override val timestamp: String,
        override val image: String,
        override val title: String,
        override val metaData: MetaData,
        val duration: Long,
    ) : Action

    data class Heal(
        override val timestamp: String,
        override val image: String,
        override val title: String,
        override val metaData: MetaData,
        val healthPoints: Float,
    ) : Action

    data class MetaData(
        val primaryColor: Long,
        val secondaryColor: Long,
    )
}

/**
 * Why Override hashCode() in Kotlin?
 * When you override equals(), you must override hashCode() to maintain the contract between these two methods.
 *
 * 💡 The Contract:
 * If two objects are equal (==), they must have the same hash code.
 * If two objects have the same hash code, they are not necessarily equal (because different objects can have the same hash code due to collisions).
 * 🚨 What happens if you don’t override hashCode()?
 * If you only override equals() but not hashCode(), collections like HashSet, HashMap, etc. won’t work correctly.
 *
 * Why * 31 in hashCode()?
 * 1️⃣ Multiplying by 31 is a common hash function technique
 * It helps reduce collisions (two different objects getting the same hash).
 * It spreads out hash values to avoid clustering.
 * 2️⃣ Why 31 specifically?
 * 31 is a prime number → Primes help reduce hash collisions.
 * It is efficient → JVM can optimize multiplication by 31 as (x << 5) - x (bit-shifting).
 */