
package com.tayloraar.plantplacer

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar


class ScanActivity : AppCompatActivity(), SensorEventListener {

    private val viewModel: ItemViewModel by viewModels()
    private lateinit var sensorManager: SensorManager
    private var brightness: Sensor? = null
    lateinit var text: TextView
     var resume = true;
     lateinit var dataText : String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)
        setSupportActionBar(findViewById(R.id.toolbar))

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        viewModel.selectedItem.observe(this, Observer { data ->
            // Perform an action with the latest item data
        })

        setUpSensor()


    }
    private fun setUpSensor() {
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        brightness = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_LIGHT && resume) {
            val light1 = event.values[0]

            dataText = "Sensor: $light1\n${brightness(light1)}"

           viewModel.updateData(dataText)

        }
    }

    private fun brightness(brightness: Float): String {

        return when (brightness.toInt()) {
            0 -> "Pitch black"
            in 1..499 -> "Too Dark For Plants!"
            in 500..2500 -> "Very low-light plants or heavy shade plants"
            in 2501..5000 -> "Low-light or partial shade plants"
            in 5001..10000 -> "Medium-light or partial sun plants"
            in 10001..20000 -> "Bright-light no direct sun"
            in 20001..50000 -> "Full sun all day"
            else -> "This light will blind you"
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }

    override fun onResume() {
        super.onResume()
        // Register a listener for the sensor.
        sensorManager.registerListener(this, brightness, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    fun resumeScan(view:View){
        this.resume = true
    }
    fun pauseScan(view: View){
        this.resume = false
    }


}
