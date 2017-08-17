package de.kleinelamas.svbrockscheid.injection

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import de.kleinelamas.svbrockscheid.MainActivity
import de.kleinelamas.svbrockscheid.SVBApp
import de.kleinelamas.svbrockscheid.connection.ApiClient
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class, ApiModule::class, AndroidSupportInjectionModule::class, BindingModule::class))
@Singleton
interface AppComponent {
    val context: Context
    val apiClient: ApiClient

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(target: SVBApp)
    fun inject(target: MainActivity)
}