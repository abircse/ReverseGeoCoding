package coxtunes.com.reversegeocoding.addresstolatlong

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import coxtunes.com.reversegeocoding.GeoCodingLocation
import coxtunes.com.reversegeocoding.MainActivity
import coxtunes.com.reversegeocoding.R

class AddressToLatLongActivity : AppCompatActivity() {

    private lateinit var addressButton: Button
    private lateinit var textViewLatLong: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address_to_lat_long)

        title = "Reverse Geocoding (Address to Lat Long)"
        textViewLatLong = findViewById(R.id.latLongTV)
        addressButton = findViewById(R.id.addressButton)
        addressButton.setOnClickListener {
            val editText = findViewById<EditText>(R.id.editTextAddress)
            val address = editText.text.toString()
            val locationAddress = GeoCodingLocation()
            locationAddress.getAddressFromLocation(
                address,
                applicationContext,
                GeoCoderHandler(this)
            )
        }
    }

    companion object {
        private class GeoCoderHandler(private val activity: AddressToLatLongActivity) : Handler() {
            override fun handleMessage(message: Message) {
                val locationAddress: String? = when (message.what) {
                    1 -> {
                        val bundle = message.data
                        bundle.getString("address")
                    }

                    else -> null
                }
                activity.textViewLatLong.text = locationAddress
            }
        }
    }
}