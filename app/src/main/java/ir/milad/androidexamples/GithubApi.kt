package ir.milad.androidexamples

import retrofit2.Call
import retrofit2.http.GET

interface GithubApi {

    @GET("users")
    fun getUsers() : Call<List<GithubUser>>
}