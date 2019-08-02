package codekotlin.utils

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.os.Build
import android.util.DisplayMetrics
import android.view.Display
import android.view.WindowManager


/**
 * 屏幕尺寸操作
 *
 * @author Chenahaizhen
 * @date: 2017年7月17日 下午2:26:56
 */
class ScreenUtils {
    init {
        /* cannot be instantiated */
        throw UnsupportedOperationException("cannot be instantiated")
    }

    companion object {

        private val INTERGER_14 = 14
        private val INTERGER_17 = 17

        val displayMetrics: DisplayMetrics
            get() = DisplayMetrics()

        val screenHeight: Float
            get() = displayMetrics.heightPixels.toFloat()

        val screenWidth: Float
            get() = displayMetrics.widthPixels.toFloat()

        /**
         * 获取屏幕宽度
         *
         * @param context
         * @return
         */
        fun getScreenWidth(context: Context): Int {
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val outMetrics = DisplayMetrics()
            wm.defaultDisplay.getMetrics(outMetrics)
            return outMetrics.widthPixels
        }

        /**
         * 获取屏幕高度
         *
         * @param context
         * @return
         */
        fun getScreenHeight(context: Context): Int {
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val outMetrics = DisplayMetrics()
            wm.defaultDisplay.getMetrics(outMetrics)
            return outMetrics.heightPixels
        }


        /**
         * 获取屏幕的真实宽高
         *
         * @param activity
         * @return
         */
        fun getRealScreenSize(activity: Activity): IntArray {
            val size = IntArray(2)
            var screenWidth = 0
            var screenHeight = 0
            val w = activity.windowManager
            val d = w.defaultDisplay
            val metrics = DisplayMetrics()
            d.getMetrics(metrics)
            // since SDK_INT = 1;
            screenWidth = metrics.widthPixels
            screenHeight = metrics.heightPixels
            // includes window decorations (statusbar bar/menu bar)
            if (Build.VERSION.SDK_INT >= INTERGER_14 && Build.VERSION.SDK_INT < INTERGER_17) {
                try {
                    screenWidth = Display::class.java.getMethod("getRawWidth").invoke(d) as Int
                    screenHeight = Display::class.java.getMethod("getRawHeight").invoke(d) as Int
                } catch (ignored: Exception) {
                }

            }
            // includes window decorations (statusbar bar/menu bar)
            if (Build.VERSION.SDK_INT >= INTERGER_17) {
                try {
                    val realSize = Point()
                    Display::class.java.getMethod("getRealSize", Point::class.java).invoke(d, realSize)
                    screenWidth = realSize.x
                    screenHeight = realSize.y
                } catch (ignored: Exception) {
                }

            }
            size[0] = screenWidth
            size[1] = screenHeight
            return size
        }


        var screenW: Int = 0
            private set
        var screenH: Int = 0
            private set
        var screenDensity: Float = 0.toFloat()
            private set

        fun initScreen(mActivity: Activity) {
            val metric = DisplayMetrics()
            mActivity.windowManager.defaultDisplay.getMetrics(metric)
            screenW = metric.widthPixels
            screenH = metric.heightPixels
            screenDensity = metric.density
        }

        /**
         * 获取手机虚拟按键高度
         * @param poCotext 上下文对象
         * @return 高度
         */
        fun getVrtualBtnHeight(poCotext: Context): Int {
            val location = getRealScreenSize(poCotext as Activity)
            val realHeiht = getScreenHeight(poCotext)
            return location[1] - realHeiht
        }

        /**
         * 获取
         * @param context 上下文
         * @return Int
         */
        fun getStatusBarHeight(context: Context): Int {
            var height = 0
            val resourceId = context.applicationContext.resources.getIdentifier("status_bar_height", "dimen", "android")
            if (resourceId > 0) {
                height = context.applicationContext.resources.getDimensionPixelSize(resourceId)
            }
            return height
        }
    }

}
