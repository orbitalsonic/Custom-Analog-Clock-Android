package com.orbitalsonic.analogclock

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.RelativeLayout
import java.util.*

class MainActivity : AppCompatActivity() {

    var startDegSec = 0.0F
    var startDegMint = 0.0F
    var startDegHr = 0.0F
    lateinit var clockLayout: RelativeLayout

    private val handler = Handler(Looper.getMainLooper())
    private val drawRunner = Runnable { draw() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clockLayout = findViewById(R.id.clockLayout)
        getInitialAllAngle()

        clockLayout.removeAllViews()
        clockLayout.addView(drawCanvas())
    }

    private fun repeatMethod() {
        needleControlAngle()
        handler.removeCallbacks(drawRunner)
        handler.postDelayed(
            drawRunner,
            (1000)
        )

//            Handler(Looper.getMainLooper()).postDelayed({
//                    draw()
//
//            }, 1000)

    }

    private fun draw() {
        clockLayout.removeAllViews()
        clockLayout.addView(drawCanvas())
        repeatMethod()
    }

    private fun drawCanvas(): MyCanvasView {

        return MyCanvasView(
            this,
            startDegHr,
            startDegMint,
            startDegSec,
            R.drawable.antiqueclock_bg,
            R.drawable.antiqueclock_hour,
            R.drawable.antiqueclock_min,
            R.drawable.antiqueclock_sec,
            Color.parseColor("#00FFFFFF"),
            Color.parseColor("#00FFFFFF"),
            Color.parseColor("#00FFFFFF")
        )

    }

    private fun getInitialAllAngle() {
        val calender: Calendar = Calendar.getInstance()
        val mSecond: Int = calender.get(Calendar.SECOND)
        val mMinute: Int = calender.get(Calendar.MINUTE)
        val mHour: Int = calender.get(Calendar.HOUR)

        startDegSec = initialSecondAngle(mSecond)
        startDegMint = initialMinuteAngle(mMinute)
        startDegHr = initialHourAngle(mHour, mMinute)
    }

    private fun initialSecondAngle(sec: Int): Float {
        return sec * 6F
    }

    private fun initialMinuteAngle(mint: Int): Float {
        return mint * 6F
    }

    private fun initialHourAngle(hour: Int, mint: Int): Float {
        return ((hour * 30) + (mint * 0.5)).toFloat()
    }

    private fun needleControlAngle() {

        if (startDegSec >= 360) {
            startDegSec = 6.0F
            if (startDegMint >= 360) {
                startDegMint = 6.0F
                if (startDegHr >= 360) {
                    startDegHr = 0.5F
                } else {
                    startDegHr += 0.5F
                }
            } else {
                startDegMint += 6.0F
                if (startDegMint % 6 == 0.0F) {
                    if (startDegHr >= 360) {
                        startDegHr = 0.5F
                    } else {
                        startDegHr += 0.5F
                    }
                }
            }
        } else {
            startDegSec += 6.0F
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(drawRunner)
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(drawRunner)
    }

    override fun onResume() {
        super.onResume()
        //Start Drawing
        handler.post(drawRunner)
    }
}