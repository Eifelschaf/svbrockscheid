package de.kleinelamas.svbrockscheid.model

/**
 * @author Matthias Kutscheid
 */
data class TeamHolder(private val team: HashMap<String, Array<Player>>) {
    companion object {
        val TORHUETER = "TORHÜTER"
        val ABWEHR = "ABWEHR"
        val MITTELFELD = "MITTELFELD"
        val ANGRIFF = "ANGRIFF"
        val TRAINER = "TRAINERTEAM"
    }

    fun getTorhueter() = team[TORHUETER]
    fun getAbwehr() = team[ABWEHR]
    fun getMittelfeld() = team[MITTELFELD]
    fun getAngriff() = team[ANGRIFF]
    fun getTrainer() = team[TRAINER]
}