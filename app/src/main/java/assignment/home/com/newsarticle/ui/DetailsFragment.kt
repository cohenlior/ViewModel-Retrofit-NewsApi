package assignment.home.com.newsarticle.ui

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import assignment.home.com.newsarticle.model.Article
import assignment.home.com.newsarticle.viewmodel.SelectedArticleViewModel
import kotlinx.android.synthetic.main.screen_article_details.*
import android.webkit.WebView
import android.webkit.WebViewClient

class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(assignment.home.com.newsarticle.R.layout.screen_article_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val selectedArticleViewModel: SelectedArticleViewModel =
            ViewModelProviders.of(activity!!).get(SelectedArticleViewModel::class.java)

        selectedArticleViewModel.getSelectedArticle().observe(this, Observer<Article> { article ->
            webViewFragment.loadUrl(article.url)
        })

        webViewFragment.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                loadingArticle.visibility = View.VISIBLE
            }
            override fun onPageFinished(view: WebView, url: String) {
                loadingArticle.visibility = View.INVISIBLE
            }
        }
    }
}