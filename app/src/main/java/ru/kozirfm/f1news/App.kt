package ru.kozirfm.f1news

import android.app.Application
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.core.context.startKoin
import ru.kozirfm.f1news.di.appModule

@ExperimentalSerializationApi
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}