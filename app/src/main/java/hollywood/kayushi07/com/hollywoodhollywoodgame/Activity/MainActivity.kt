package hollywood.kayushi07.com.hollywoodhollywoodgame.Activity;

import android.content.Context
import android.content.Intent
import com.google.android.gms.ads.AdRequest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
        //creating a database
//        mDatabase = openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null)

        val b_easy = findViewById(R.id.b_easy) as Button

        b_easy.setOnClickListener{
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
