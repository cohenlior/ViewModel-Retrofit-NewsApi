package assignment.home.com.newsarticle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import java.text.SimpleDateFormat
import java.util.*

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun toSimpleString(publishedAt: String): String {
    val inputFormatter = SimpleDateFormat ("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
    val outputFormatter = SimpleDateFormat ("EEE, d MMM yyyy HH:mm", Locale.getDefault())
    val date = inputFormatter.parse(publishedAt)
    return outputFormatter.format(date)
}