package de.kleinelamas.svbrockscheid.injection

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import de.kleinelamas.svbrockscheid.MainActivity
import de.kleinelamas.svbrockscheid.SVBApp
import de.kleinelamas.svbrockscheid.connection.ApiClient
import de.kleinelamas.svbrockscheid.fragments.GamesFragment
import de.kleinelamas.svbrockscheid.fragments.TeamFragment
import de.kleinelamas.svbrockscheid.model.GameLiveData
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class, ApiModule::class, AndroidSupportInjectionModule::class, BindingModule::class, DataModule::class))
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
    fun inject(target: TeamFragment)
    fun inject(target: GamesFragment)
    fun inject(taget: GameLiveData)
}