package assignment.home.com.newsarticle.network

import assignment.home.com.newsarticle.model.ArticleResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ArticleApi {
    private val service: ArticleService

    companion object {
        const val BASE_URL = "https://newsapi.org/"
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(ArticleService::class.java)
    }

    fun getResults(): Call<ArticleResult> {
        return service.getArticles()
    }
}