package assignment.home.com.newsarticle.ui

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_fragment.*
import assignment.home.com.newsarticle.R
import assignment.home.com.newsarticle.model.Article
import assignment.home.com.newsarticle.model.ArticleResult
import assignment.home.com.newsarticle.viewmodel.ListViewModel
import assignment.home.com.newsarticle.viewmodel.SelectedArticleViewModel

class ListArticleFragment : Fragment(), ListAdapter.ClickListener {

    private lateinit var listViewModel: ListViewModel
    private lateinit var listAdapter: ListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listViewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewManager = LinearLayoutManager(activity)
        listAdapter = ListAdapter()
        recyclerView.adapter = listAdapter
        recyclerView.layoutManager = viewManager
        listAdapter.setOnClickListener(this)
        observeViewModel()
    }

    private fun observeViewModel() {
        listViewModel.getResults().observe(this, Observer<ArticleResult>{ result ->
            Log.d("ListArticleFragment", result.toString())
            recyclerView.visibility = View.VISIBLE
            listAdapter.updateList(result.articles)

        })

        listViewModel.getError().observe(this, Observer<Boolean> { isError ->
            if (isError) {
                tvError.visibility = View.VISIBLE
                recyclerView.visibility =View.GONE
            } else {
                tvError.visibility = View.GONE
                tvError.text = null
            }
        })

        listViewModel.getLoading().observe(this, Observer<Boolean> { isLoading ->
            loadingView.visibility = (if (isLoading) View.VISIBLE else View.GONE)
            if (isLoading) {
                tvError.visibility = View.GONE
                recyclerView.visibility = View.GONE
            }
        })
    }

    override fun onResume() {
        super.onResume()
        listViewModel.loadResults()
    }

    override fun onClick(article: Article) {
        if (!TextUtils.isEmpty(article.url)) {
            val selectedArticleViewModel: SelectedArticleViewModel =
                ViewModelProviders.of(activity!!).get(SelectedArticleViewModel::class.java)
            selectedArticleViewModel.selectedArticle(article)
            activity!!.supportFragmentManager
                .beginTransaction()
                .replace(R.id.screen_container, DetailsFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}