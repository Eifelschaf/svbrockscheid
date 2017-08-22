package de.kleinelamas.svbrockscheid

import android.content.Context
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.answers.Answers
import io.fabric.sdk.android.Fabric
import javax.inject.Inject

/**
 * @author Matthias Kutscheid
 */
class FabricProxy {
    @Inject lateinit var context: Context
    init {
        SVBApp.component.inject(this)
        Fabric.with(context, Crashlytics(), Answers())
    }
}