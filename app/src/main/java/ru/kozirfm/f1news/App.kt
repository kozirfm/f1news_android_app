package ru.kozirfm.f1news

import android.app.Application
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.kozirfm.f1news.di.appModule
import ru.kozirfm.f1news.di.viewModelsModule

@ExperimentalSerializationApi
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(appModule, viewModelsModule)
        }
    }
}