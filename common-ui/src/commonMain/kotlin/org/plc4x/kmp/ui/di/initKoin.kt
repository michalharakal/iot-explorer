package org.plc4x.kmp.ui.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module
import org.plc4x.kmp.domain.repository.DeviceRepository
import org.plc4x.kmp.platform.CoroutineContextProvider
import org.plc4x.kmp.plc.PlcDeviceRepository

fun initKoin(
    ioDispatcher: CoroutineDispatcher,
    appDeclaration: KoinAppDeclaration = {},
) = startKoin {
    appDeclaration()
    modules(commonModule(ioDispatcher))
}

fun commonModule(ioDispatcher: CoroutineDispatcher) = module {

    single {
        object : CoroutineContextProvider {
            override fun mainScope(): CoroutineScope = MainScope()
            override fun ioScope(): CoroutineScope = CoroutineScope(ioDispatcher)
        }
    } bind CoroutineContextProvider::class

    single {
        PlcDeviceRepository()
    } bind DeviceRepository::class

}