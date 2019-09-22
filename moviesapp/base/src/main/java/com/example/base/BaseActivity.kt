package com.example.base

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.fragment.app.FragmentActivity
import io.reactivex.disposables.CompositeDisposable
import com.fxn.cue.Cue
import com.fxn.cue.enums.Duration
import com.fxn.cue.enums.Type

abstract class BaseActivity: FragmentActivity() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    fun onShowError(error: String) {
        //TODO Change all this to a ToastHelper or somethinig like that
        Cue.init()
                .with(this)
                .setMessage(error)
                .setGravity(Gravity.TOP or Gravity.BOTTOM)
                .setType(Type.CUSTOM)
                .setDuration(Duration.LONG)
                .setBorderWidth(0)
                .setCornerRadius(100)
                .setCustomFontColor(Color.parseColor("#e86873"),
                        Color.parseColor("#ffffff"),
                        Color.parseColor("#e86873"))
                .setPadding(25)
                .setTextSize(15)
                .show()
    }

    fun onShowMessage(message: String) {
        Cue.init()
                .with(this)
                .setMessage(message)
                .setGravity(Gravity.TOP or Gravity.BOTTOM)
                .setType(Type.CUSTOM)
                .setDuration(Duration.LONG)
                .setBorderWidth(0)
                .setCornerRadius(100)
                .setCustomFontColor(Color.parseColor("#005296"),
                        Color.parseColor("#ffffff"),
                        Color.parseColor("#005296"))
                .setPadding(25)
                .setTextSize(15)
                .show()
    }
}