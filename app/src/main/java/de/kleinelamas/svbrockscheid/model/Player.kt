package de.kleinelamas.svbrockscheid.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @author Matthias Kutscheid
 */
@Parcelize
data class Player(val bild: String,
                  val name: String,
                  val datum: String,
                  val spiele: String?,
                  val zeitOhneGegentor: String?,
                  val tore: String?,
                  val vorlagen: String?,
                  val gelbeKarten: String?,
                  val gehalteneElfmeter: String?,
                  val zuNull: String?,
                  val roteKarten: String?,
                  val gelbRoteKarten: String?
) : Parcelable