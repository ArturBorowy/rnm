package pl.arturborowy.rnm.base.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("srcUrl")
fun setSrcUrl(imageView: ImageView, url: String?) {
    Glide.with(imageView.context.applicationContext)
        .load(url)
        .into(imageView)
}