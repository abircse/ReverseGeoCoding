package coxtunes.com.reversegeocoding

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import coxtunes.com.reversegeocoding.addresstolatlong.AddressToLatLongActivity
import coxtunes.com.reversegeocoding.latlongtoaddressbymarkerpicker.MainActivity2

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Reverse Geocoding"

        val button1 = findViewById<Button>(R.id.button_one)
        val button2 = findViewById<Button>(R.id.button_two)

        button1.setOnClickListener {
            val intent = Intent(this, AddressToLatLongActivity::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}