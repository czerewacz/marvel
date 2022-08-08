package ai.akun.marvel.commons

import ai.akun.characters.di.charactersModule
import ai.akun.core.di.coreModule
import ai.akun.core.network.di.networkModule
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MarvelApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MarvelApp)
            modules(
                listOf(
                    coreModule,
                    networkModule,
                    charactersModule
                )
            )
        }
    }

}