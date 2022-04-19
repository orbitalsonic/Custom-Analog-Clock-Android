package com.orbitalsonic.analogclock

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.view.View


class MyCanvasView(
    context: Context,
    mStartDegHr: Float,
    mStartDegMint: Float,
    mStartDegSec: Float,
    mClockImage: Int,
    mHourImage: Int,
    mMintImage: Int,
    mSecImage: Int,
    mHourColor: Int,
    mMintColor: Int,
    mSecColor: Int
) : View(context) {

    private lateinit var mBitmapSec: Bitmap
    private lateinit var mBitmapMint: Bitmap
    private lateinit var mBitmapHr: Bitmap
    private lateinit var mBitmapMain: Bitmap

    private val startDegHr = mStartDegHr
    private var startDegMint = mStartDegMint
    private var startDegSec = mStartDegSec

    private val clockImage: Int = mClockImage
    private val hourImage:Int = mHourImage
    private val mintImage:Int = mMintImage
    private val secImage:Int = mSecImage

    private val hourColor: Int = mHourColor
    private val mintColor: Int = mMintColor
    private val secColor: Int = mSecColor

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawCanvas(canvas!!)
    }

    private fun drawCanvas(canvas: Canvas) {

        mBitmapMain = BitmapFactory.decodeResource(resources, clockImage)
        val bitmap = Bitmap.createScaledBitmap(
            mBitmapMain,
            getPercentageWidth(70, canvas.width),
            getPercentageWidth(70, canvas.width),
            true
        )

        canvas.drawBitmap(
            bitmap,
            ((canvas.width - bitmap.width) / 2).toFloat(),
            ((canvas.height - bitmap.height) / 2).toFloat(),
            null
        )

        mBitmapHr = BitmapFactory.decodeResource(resources,hourImage)
        hrNeedleControl(canvas, startDegHr)

        mBitmapMint = BitmapFactory.decodeResource(resources, mintImage)
        mintNeedleControl(canvas, startDegMint)

        mBitmapSec = BitmapFactory.decodeResource(resources, secImage)
        secNeedleControl(canvas, startDegSec)

    }


    private fun getPercentageWidth(percentage: Int, mWidth: Int): Int {
        return ((percentage * mWidth) / 100)

    }

    private fun secNeedleControl(canvas: Canvas, mStartDegSec: Float) {
        val paint = Paint()
        val filter: ColorFilter = PorterDuffColorFilter(
            secColor,
            PorterDuff.Mode.SRC_IN
        )
        paint.colorFilter = filter

        val width: Int = mBitmapSec.width
        val height: Int = mBitmapSec.height
        val newWidth = getPercentageWidth(70, canvas.width)
        val newHeight = getPercentageWidth(70, canvas.width)

        val scaleWidth = newWidth.toFloat() / width
        val scaleHeight = newHeight.toFloat() / height

        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)
        matrix.postRotate(mStartDegSec)
        mBitmapSec = Bitmap.createBitmap(
            mBitmapSec, 0, 0,
            width, height, matrix, true
        )

        if (secColor==Color.parseColor("#00FFFFFF")){
            canvas.drawBitmap(
                mBitmapSec,
                ((canvas.width - mBitmapSec.width) / 2).toFloat(),
                ((canvas.height - mBitmapSec.height) / 2).toFloat(),
                null
            )
        }else{
            canvas.drawBitmap(
                mBitmapSec,
                ((canvas.width - mBitmapSec.width) / 2).toFloat(),
                ((canvas.height - mBitmapSec.height) / 2).toFloat(),
                paint
            )
        }



    }

    private fun mintNeedleControl(canvas: Canvas, mStartDegMint: Float) {
        val paint = Paint()
        val filter: ColorFilter = PorterDuffColorFilter(
            mintColor,
            PorterDuff.Mode.SRC_IN
        )
        paint.colorFilter = filter

        val width: Int = mBitmapMint.width
        val height: Int = mBitmapMint.height
        val newWidth = getPercentageWidth(70, canvas.width)
        val newHeight = getPercentageWidth(70, canvas.width)

        val scaleWidth = newWidth.toFloat() / width
        val scaleHeight = newHeight.toFloat() / height

        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)
        matrix.postRotate(mStartDegMint)
        mBitmapMint = Bitmap.createBitmap(
            mBitmapMint, 0, 0,
            width, height, matrix, true
        )

        if (mintColor==Color.parseColor("#00FFFFFF")){
            canvas.drawBitmap(
                mBitmapMint,
                ((canvas.width - mBitmapMint.width) / 2).toFloat(),
                ((canvas.height - mBitmapMint.height) / 2).toFloat(),
                null
            )
        }else{
            canvas.drawBitmap(
                mBitmapMint,
                ((canvas.width - mBitmapMint.width) / 2).toFloat(),
                ((canvas.height - mBitmapMint.height) / 2).toFloat(),
                paint
            )
        }



    }

    private fun hrNeedleControl(canvas: Canvas, mStartDegHr: Float) {

        val paint = Paint()
        val filter: ColorFilter = PorterDuffColorFilter(
            hourColor,
            PorterDuff.Mode.SRC_IN
        )
        paint.colorFilter = filter

        val width: Int = mBitmapHr.width
        val height: Int = mBitmapHr.height
        val newWidth = getPercentageWidth(70, canvas.width)
        val newHeight = getPercentageWidth(70, canvas.width)

        val scaleWidth = newWidth.toFloat() / width
        val scaleHeight = newHeight.toFloat() / height

        val matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)
        matrix.postRotate(mStartDegHr)
        mBitmapHr = Bitmap.createBitmap(
            mBitmapHr, 0, 0,
            width, height, matrix, true
        )

        if (hourColor==Color.parseColor("#00FFFFFF")){
            canvas.drawBitmap(
                mBitmapHr,
                ((canvas.width - mBitmapHr.width) / 2).toFloat(),
                ((canvas.height - mBitmapHr.height) / 2).toFloat(),
                null
            )
        }else{
            canvas.drawBitmap(
                mBitmapHr,
                ((canvas.width - mBitmapHr.width) / 2).toFloat(),
                ((canvas.height - mBitmapHr.height) / 2).toFloat(),
                paint
            )
        }



    }

}