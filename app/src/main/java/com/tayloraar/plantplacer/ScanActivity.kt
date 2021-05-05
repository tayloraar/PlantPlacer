
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

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
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
            in 1..10 -> "Dark"
            in 11..50 -> "Grey"
            in 51..5000 -> "Normal"
            in 5001..25000 -> "Incredibly bright"
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
