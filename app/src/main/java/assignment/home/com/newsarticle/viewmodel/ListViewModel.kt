package assignment.home.com.newsarticle.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import assignment.home.com.newsarticle.model.Article
import assignment.home.com.newsarticle.model.ArticleResult
import assignment.home.com.newsarticle.network.ArticleApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListViewModel : ViewModel() {

    var results: MutableLiveData<ArticleResult> = MutableLiveData()
    var articleLoadError: MutableLiveData<Boolean> = MutableLiveData()
    var loading: MutableLiveData<Boolean> = MutableLiveData()

    private val articleApi: ArticleApi = ArticleApi()

    fun getResults(): LiveData<ArticleResult> = results

    fun getError(): LiveData<Boolean> = articleLoadError

    fun getLoading(): LiveData<Boolean> = loading

    // This isn't an optimal implementation. Using repository module is better.
    fun loadResults() {
        loading.value = true
        val call = articleApi.getResults()
        call.enqueue(object : Callback<ArticleResult>{
            override fun onFailure(call: Call<ArticleResult>, t: Throwable) {
                articleLoadError.value = true
                loading.value = false
                Log.e("ListViewModel", "Problem calling News API", t)            }

            override fun onResponse(call: Call<ArticleResult>, response: Response<ArticleResult>) {
                response?.isSuccessful.let {
                    loading.value = false
                    val resultList = ArticleResult(response?.body()?.articles ?: emptyList())
                    results.value = resultList
                }
            }
        })
    }
}