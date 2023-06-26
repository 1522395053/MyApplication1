package libx.android.app2

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatTextView

class TestWidthView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    AppCompatTextView(context, attrs, defStyleAttr) {

    private val TAG = "TestWidthView"

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.d(TAG,"onMeasure，width:$width")
    }

    override fun layout(l: Int, t: Int, r: Int, b: Int) {
        Log.d(TAG,"layout，super.layout之前-width:$width")
        super.layout(l, t, r, b)
        Log.d(TAG,"layout，super.layout之后-width:$width")
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.d(TAG,"onLayout，width:$width")
    }
}