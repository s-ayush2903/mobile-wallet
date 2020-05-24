package org.mifos.mobilewallet.mifospay

import okhttp3.mockwebserver.MockResponse
import okio.IOException
import org.junit.Assert
import org.junit.Test
import org.mifos.mobilewallet.mifospay.utils.MockFileParser
import retrofit2.Response
import rx.Observable

class LoginTest :BaseTest(){

    @Test
    @Throws(IOException::class)
    fun loginTest(){

    val loginResponse=MockFileParser("login_response.json").content

        mockWebServer.enqueue(MockResponse().setBody(loginResponse))

//        val response:Response<*> = authApi.authenticate("S","D").execute()
        val response = authApi.authenticate("mifos","password")


        //read some docs/blogposts regarding observable testing
        Assert.assertEquals(response, loginResponse)
    }




}