package de.kleinelamas.svbrockscheid.injection

import dagger.Module
import dagger.Provides
import de.kleinelamas.svbrockscheid.model.GameLiveData

@Module
class DataModule {

    @Provides
    fun provideGameLiveData(): GameLiveData {
        return GameLiveData()
    }
}