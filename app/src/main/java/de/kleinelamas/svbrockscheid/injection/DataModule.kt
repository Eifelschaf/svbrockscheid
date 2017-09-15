package de.kleinelamas.svbrockscheid.injection

import dagger.Module
import dagger.Provides
import de.kleinelamas.svbrockscheid.model.GameLiveData
import de.kleinelamas.svbrockscheid.model.TeamLiveData

@Module
class DataModule {

    @Provides
    fun provideGameLiveData(): GameLiveData {
        return GameLiveData()
    }

    @Provides
    fun provideTeamLiveData(): TeamLiveData {
        return TeamLiveData()
    }
}