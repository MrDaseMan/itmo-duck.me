package ru.sample.duckapp

import android.app.Application
import ru.sample.duckapp.infra.Api

class DucksApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Api.ducksApi
        Api.helpers
    }
}