package com.example.foursquarekotlin

import android.app.Application
import com.parse.Parse

class StarterApplication : Application()
{
    override fun onCreate() {
        super.onCreate()

        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG)

        Parse.initialize(Parse.Configuration.Builder(this)
            .applicationId("LkPrM8D8SbEoFC0QvsRUJ9pbXyRvj6shr0YroCHm")
            .clientKey("WHonR9ZmLRZc86KHQ5PDgww74zhePPAfYB2DqjrL")
            .server("https://parseapi.back4app.com/")
            .build()



        )

    }
}