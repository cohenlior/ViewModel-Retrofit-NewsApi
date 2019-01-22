package assignment.home.com.newsarticle.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import assignment.home.com.newsarticle.inflate
import assignment.home.com.newsarticle.model.Article
import assignment.home.com.newsarticle.model.ArticleResult
import assignment.home.com.newsarticle.toSimpleString
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_article_list_item.view.*

class ListAdapter(private var dataSet: List<Article> = ArrayList()) :
    RecyclerView.Adapter<ListAdapter.ArticleHolder>() {
    var mClickListener: ClickListener? = null

    fun setOnClickListener(clickListener: ClickListener) {
        this.mClickListener = clickListener
    }
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ListAdapter.ArticleHolder {
        return ArticleHolder(parent.inflate(assignment.home.com.newsarticle.R.layout.view_article_list_item, false))
    }


    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        holder.bindArticle(dataSet.get(position))
    }

    override fun getItemCount() = dataSet.size

    fun updateList(articles: List<Article>) {
        this.dataSet = articles
        notifyDataSetChanged()
    }

    inner class ArticleHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener  {
        override fun onClick(v: View) {
            mClickListener?.onClick(article)
        }

        private var view: View = v
        private lateinit var article: Article

        init {
            v.setOnClickListener(this)
        }

        fun bindArticle(article: Article) {
            this.article = article
            Picasso.get()
                .load(article.urlToImage)
                .placeholder(assignment.home.com.newsarticle.R.drawable.ic_panorama_grey_24dp)
                .into(view.articleImage)

            view.articleTitle.text = article.title
            view.articleDate.text = toSimpleString(article.publishedAt)
            view.articleDescription.text = article.description
        }
    }

    interface ClickListener {
        fun onClick(article: Article)
    }
}