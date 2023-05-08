package coxtunes.com.reversegeocoding.latlongtoaddressbymarkerpicker

import android.annotation.SuppressLint
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import coxtunes.com.reversegeocoding.R
import java.io.IOException
import java.util.Locale

class MainActivity2 : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // Fetching API_KEY which we wrapped
        val apiKey = "ADD_YOUR_GOOGLE_API_KEY"

        // Initializing the Places API
        // with the help of our API_KEY
        if (!Places.isInitialized()) {
            Places.initialize(this, apiKey)
        }

        // Initializing map
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    @SuppressLint("SetTextI18n")
    override fun onMapReady(p0: GoogleMap) {

        mMap = p0

        // These are Geeks for Geeks Noida Office Coordinates.
        val coxsbazar = LatLng(21.45388, 91.96765)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coxsbazar, 17F))

        mMap.setOnCameraIdleListener {
            val lat = mMap.cameraPosition.target.latitude
            val lng = mMap.cameraPosition.target.longitude
            val addressTV = findViewById<TextView>(R.id.tv)

            // Initializing Geocoder
            val mGeocoder = Geocoder(applicationContext, Locale.getDefault())
            var addressString = ""

            // Reverse-Geocoding starts
            try {
                val addressList: List<Address> =
                    mGeocoder.getFromLocation(lat, lng, 1) as List<Address>

                // use your lat, long value here
                if (addressList.isNotEmpty()) {

                    val address = addressList[0]
                    val sb = StringBuilder()
                    for (i in 0 until address.maxAddressLineIndex) {
                        sb.append(address.getAddressLine(i)).append("\n")
                    }

                    // Various Parameters of an Address are appended
                    // to generate a complete Address
                    if (address.premises != null)
                        sb.append(address.premises).append(", ")

                    sb.append(address.subAdminArea).append("\n")
                    sb.append(address.locality).append(", ")
                    sb.append(address.adminArea).append(", ")
                    sb.append(address.countryName).append(", ")
                    sb.append(address.postalCode)

                    // StringBuilder sb is converted into a string
                    // and this value is assigned to the
                    // initially declared addressString string.
                    addressString = sb.toString()
                }
            } catch (e: IOException) {
                Toast.makeText(applicationContext, "Unable connect to Geocoder", Toast.LENGTH_LONG)
                    .show()
            }

            // Finally, the address string is posted in the textView with LatLng.
            addressTV.text = "Lat: $lat \nLng: $lng \nAddress: $addressString"
        }
    }
}