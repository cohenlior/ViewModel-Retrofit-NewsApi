package assignment.home.com.newsarticle.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import assignment.home.com.newsarticle.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val listFragment = ListArticleFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.screen_container, listFragment)
                .commit()
        }
    }
}
