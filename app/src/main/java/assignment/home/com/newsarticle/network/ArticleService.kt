package assignment.home.com.newsarticle.network

import assignment.home.com.newsarticle.model.ArticleResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ArticleService {
    @Headers("X-Api-Key: 30db667d79ad45ab8b380ffa7f11d51c")
    @GET("v2/top-headlines?country=us")
    fun getArticles(): Call<ArticleResult>
}