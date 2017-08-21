package de.kleinelamas.svbrockscheid.injection

import dagger.Module
import dagger.android.ContributesAndroidInjector
import de.kleinelamas.svbrockscheid.MainActivity
import de.kleinelamas.svbrockscheid.fragments.GamesFragment

/**
 * @author Matthias Kutscheid

 * Module for dagger bindings
 */
@Module
abstract class BindingModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun gamesFragment(): GamesFragment
}