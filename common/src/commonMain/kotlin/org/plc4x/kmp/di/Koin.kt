package org.plc4x.kmp.di

import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module



fun initKoin(
    rootLocalDir: String,
    ioDispatcher: CoroutineDispatcher,
    appDeclaration: KoinAppDeclaration = {},
) = startKoin {
    appDeclaration()
    modules(commonModule(rootLocalDir, ioDispatcher))
}

// called by iOS etc
// fun initKoin() = initKoin(enableNetworkLogs = false) {}

fun commonModule(rootLocalDir: String, ioDispatcher: CoroutineDispatcher) = module {


}
