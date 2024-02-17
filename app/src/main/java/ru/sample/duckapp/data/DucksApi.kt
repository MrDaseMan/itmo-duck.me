package ru.sample.duckapp.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import okhttp3.ResponseBody
import ru.sample.duckapp.domain.Duck

interface DucksApi {
    // async request to https://random-d.uk/api/v2/random
    @GET("random")
    fun getRandomDuck(): Call<Duck>

    // async request to https://random-d.uk/api/v2/http/:code returns content-type: image/jpeg
    @GET("http/{code}")
    fun getDuckByCode(@Path("code") code: String): Call<ResponseBody>
}