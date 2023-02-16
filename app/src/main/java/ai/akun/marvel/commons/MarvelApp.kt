package ai.akun.marvel.commons

import ai.akun.character_data.di.characterDataModule
import ai.akun.character_domain.di.characterDomainModule
import ai.akun.character_presentation.di.characterPresentationModule
import ai.akun.core.di.coreModule
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
                    characterDomainModule,
                    characterDataModule,
                    characterPresentationModule
                )
            )
        }
    }

}