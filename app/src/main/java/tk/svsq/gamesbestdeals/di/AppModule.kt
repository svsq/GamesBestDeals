package tk.svsq.gamesbestdeals.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import tk.svsq.gamesbestdeals.data.repository.PrefDataRepository
import tk.svsq.gamesbestdeals.domain.repository.PrefRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun providePrefRepository(@ApplicationContext context: Context): PrefRepository =
        PrefDataRepository(context)

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(context.packageName + "_prefs", Context.MODE_PRIVATE)

}