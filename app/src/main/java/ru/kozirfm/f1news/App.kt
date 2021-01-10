package ru.kozirfm.f1news

import android.app.Application
import org.koin.core.context.startKoin
import ru.kozirfm.f1news.di.appModule

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}