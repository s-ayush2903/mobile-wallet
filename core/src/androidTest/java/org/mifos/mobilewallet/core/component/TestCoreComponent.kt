package org.mifos.mobilewallet.core.component

import android.app.Application
import dagger.Component
import dagger.Module
import org.mifos.mobilewallet.core.base.UseCaseHandler
import org.mifos.mobilewallet.core.data.fineract.api.FineractApiManager
import org.mifos.mobilewallet.core.data.fineract.repository.FineractRepository
import org.mifos.mobilewallet.core.module.TestCoreModule
import javax.inject.Singleton


@Singleton
@Component(modules = [TestCoreModule::class])
interface TestCoreComponent {

    fun application(): Application

    fun usecasehandler(): UseCaseHandler

    fun fineractApiManager(): FineractApiManager

    fun fineractRepository(): FineractRepository


}