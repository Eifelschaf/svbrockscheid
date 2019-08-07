package de.kleinelamas.svbrockscheid

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import de.kleinelamas.svbrockscheid.injection.AppComponent
import de.kleinelamas.svbrockscheid.injection.DaggerAppComponent
import javax.inject.Inject

/**
 * @author Matthias Kutscheid
 */
class SVBApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Any>
    lateinit var fabricProxy: FabricProxy

    override fun androidInjector(): AndroidInjector<Any> = activityInjector

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

