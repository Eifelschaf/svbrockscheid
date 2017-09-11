package de.kleinelamas.svbrockscheid

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import de.kleinelamas.svbrockscheid.injection.AppComponent
import de.kleinelamas.svbrockscheid.injection.DaggerAppComponent
import javax.inject.Inject

/**
 * @author Matthias Kutscheid
 */
class SVBApp : Application(), HasActivityInjector {

    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    lateinit var fabricProxy: FabricProxy

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
                .application(this)
                .build()
        component.inject(this)
        fabricProxy = FabricProxy()
    }

    companion object {
        lateinit var component: AppComponent
            private set
    }
}

