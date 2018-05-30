package hollywood.kayushi07.com.hollywoodhollywoodgame.Activity;

import android.content.Context
import android.content.Intent
import com.google.android.gms.ads.AdRequest
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.google.android.gms.ads.AdView
import hollywood.kayushi07.com.hollywoodhollywoodgame.R

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Load an ad into the AdMob banner view.
        val adView = findViewById(R.id.adView) as AdView
        val adRequest = AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build()
        adView.loadAd(adRequest)

        // Toasts the test ad message on the screen. Remove this after defining your own ad unit ID.
//        Toast.makeText(this, TOAST_TEXT, Toast.LENGTH_LONG).show()



        val b_easy = findViewById(R.id.b_easy) as Button
//        val b_medium = findViewById(R.id.b_medium) as Button
//        val b_diffi = findViewById(R.id.b_diffi) as Button
//        val b_very_diffi = findViewById(R.id.b_veryDiffi) as Button

        b_easy.setOnClickListener{
            //            Toast.makeText(this, TOAST_TEXT, Toast.LENGTH_LONG).show()

            val i = Intent(this, LevelActivity::class.java);
            startActivity(i);
        }
    }

    override fun onDestroy() {

        val editor = getSharedPreferences("Bollywood Score", Context.MODE_PRIVATE).edit()
        editor.putInt("gameScore", 0)

        editor.apply();

        super.onDestroy()

    }
}
