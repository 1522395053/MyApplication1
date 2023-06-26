package com.example.myapplication1.ui.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.LayoutInflaterCompat
import com.example.myapplication1.R
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

@OptIn(ExperimentalTime::class)
class Demo2Activity : AppCompatActivity() {
    private var sum: Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        LayoutInflaterCompat.setFactory2(LayoutInflater.from(this@Demo2Activity), object : LayoutInflater.Factory2 {



            override fun onCreateView(
                parent: View?,
                name: String,
                context: Context,
                attrs: AttributeSet
            ): View? {
                //'测量构建单个View耗时'
                val (view, duration) = measureTimedValue { delegate.createView(parent, name, context!!, attrs!!) }
                //'累加构建视图耗时'
                sum += duration.inMilliseconds
                Log.v("test", "view=${view?.let { it::class.simpleName }} duration=${duration}  sum=${sum}")
                return view
            }

            override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
                return null
            }
        })
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo2)
    }
}