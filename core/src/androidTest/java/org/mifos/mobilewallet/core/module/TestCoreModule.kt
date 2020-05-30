package org.mifos.mobilewallet.core.module

import android.app.Application
import dagger.Module
import dagger.Provides
import org.mifos.mobilewallet.core.base.UseCaseHandler
import org.mifos.mobilewallet.core.base.UseCaseThreadPoolScheduler
import org.mifos.mobilewallet.core.data.fineract.api.FineractApiManager
import javax.inject.Singleton

@Module
interface TestCoreModule {

     var coreApp: Application?

    fun ApplicationModule(application: Application?) {
        this.coreApp = application
    }

    @Provides
    fun provideApplication(): Application? {
        return coreApp
    }

    @Provides
    fun provideUsecaseHandler(): UseCaseHandler? {
        return UseCaseHandler(UseCaseThreadPoolScheduler())
    }

    @Provides
    @Singleton
    fun provideFineractApiManager(): FineractApiManager? {
        return FineractApiManager()
    }

}