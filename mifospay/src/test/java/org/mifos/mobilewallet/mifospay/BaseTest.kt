package org.mifos.mobilewallet.mifospay

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.mifos.mobilewallet.core.data.fineract.api.services.AuthenticationService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

open class BaseTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var authApi: AuthenticationService

    @Before
    @Throws(IOException::class)
    fun assemble() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val gson = GsonBuilder().setLenient().create()
        authApi = Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .client(OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(AuthenticationService::class.java)

    }

    @Before
    @Throws(IOException::class)
    fun teardown(){
        mockWebServer.shutdown()
    }


}