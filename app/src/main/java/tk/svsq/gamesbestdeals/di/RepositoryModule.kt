package tk.svsq.gamesbestdeals.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tk.svsq.gamesbestdeals.data.repository.GameRepositoryData
import tk.svsq.gamesbestdeals.domain.repository.GameRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun gameRepository(gameRepositoryData: GameRepositoryData): GameRepository

}