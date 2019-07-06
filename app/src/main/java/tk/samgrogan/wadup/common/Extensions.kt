package tk.samgrogan.wadup.common

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import tk.samgrogan.wadup.detail.DetailItem

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(if (currentFocus == null) View(this) else currentFocus)
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

/**
 * @param isGone boolean for whether the view should be gone or invisible
 */
fun View.hide(isGone: Boolean) {
    if (isGone) {
        this.visibility = View.GONE
    } else {
        this.visibility = View.INVISIBLE
    }
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun MutableList<DetailItem>.addNotEmpty(element: DetailItem) {
    if (!element.body.isNullOrEmpty() && element.body != "null") {
        this.add(element)
    }
}