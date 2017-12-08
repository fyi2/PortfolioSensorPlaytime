package org.sherman.magic.portfoliocompass

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.sherman.magic.portfoliocompass.view.CompassView

class MainActivity : AppCompatActivity() {
    lateinit var sensorManager:SensorManager
    lateinit var compassView:CompassView
    lateinit var sensor : Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        compassView = CompassView(this)
        setContentView(compassView)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        if (sensor!=null){
            sensorManager.registerListener(mySensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        } else {
            Log.d("DEBUG ===>>>", "Orientation not found")
        }
    }

    private val mySensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(sensorEvent: SensorEvent) {
            val azimuth = sensorEvent.values[0]
            compassView.updateData(azimuth)

        }

        override fun onAccuracyChanged(sensor: Sensor, i: Int) {

        }
    }
}
