package org.sherman.magic.portfoliocompass.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

/**
 * Created by Admin on 12/8/2017.
 */
class CompassView(context: Context?) : View(context) {
    val pi = Math.PI
    lateinit var paint: Paint
    private var position = 0.0

    init {
        init()
    }

    fun init() {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.strokeWidth = 2F
        paint.textSize = 25F
        paint.style = Paint.Style.STROKE
        paint.color = Color.BLACK
    }

    override fun onDraw(canvas: Canvas?) {
        val xPoint = measuredWidth/2.0F
        val yPoint = measuredHeight/2.0F
        val radius = Math.max(xPoint,yPoint)*0.6F

        canvas?.drawCircle(xPoint, yPoint, radius, paint)
        canvas?.drawRect(0.0F, 0.0F, measuredWidth.toFloat(),measuredHeight.toFloat(), paint )
        canvas?.drawLine(xPoint, yPoint,
                xPoint+radius*(Math.sin((-position/180*pi).toDouble())).toFloat(),
                yPoint-radius*(Math.cos((-position/180*pi).toDouble())).toFloat(), paint)
        canvas?.drawText(position.toString(), xPoint, yPoint, paint)
    }

    fun updateData(position: Float){
        this.position = position.toDouble()
        invalidate()
    }

}