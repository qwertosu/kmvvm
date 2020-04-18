package com.brodgate.kmvvm

import org.koin.dsl.module

val appModule = module {

    single {
        AppModule()
    }
}