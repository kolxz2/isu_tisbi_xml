package ru.nikolas_snek.isu_tisbi_xml.presenter.home


import android.util.Log
import android.view.View


fun View.gone() {
    visibility = View.GONE
}
fun Any.log(tag: String = "") {
    if (tag.equals("")) {
        Log.d("TAG_QMR", this.toString())
    } else {
        Log.d("TAG_QMR $tag", this.toString())

    }
}