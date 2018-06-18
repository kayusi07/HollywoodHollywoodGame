package hollywood.kayushi07.com.hollywoodhollywoodgame.Activity;

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import com.google.android.gms.ads.AdRequest
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.ads.AdView
import hollywood.kayushi07.com.hollywoodhollywoodgame.R
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {


    val DATABASE_NAME = "hollywoodgamedb"
    internal lateinit var mDatabase: SQLiteDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Load an ad into the AdMob banner view.
        val adView = findViewById(R.id.adView) as AdView
        val adRequest = AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build()
        adView.loadAd(adRequest)


        //creating a database
        mDatabase = openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null)

        createEmployeeTable()

        val b_easy = findViewById(R.id.b_easy) as Button

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

    private fun createEmployeeTable() {
        mDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS levels (\n" +
                        "    id INTEGER PRIMARY KEY,\n" +
                        "    movies INTEGER,\n" +
                        "    score INTEGER\n" +
                        ");"
        )
        addLevel()
    }

    //In this method we will do the create operation
    private fun addLevel() {

        try {
            val insertSQL = "INSERT INTO levels \n" +
                    "(id, movies, score)\n" +
                    "VALUES \n" +
                    "(?, ?, ?);"

            //using the same method execsql for inserting values
            //this time it has two parameters
            //first is the sql string and second is the parameters that is to be binded with the query

            mDatabase.execSQL(insertSQL, arrayOf(1, 0, 0))
            mDatabase.execSQL(insertSQL, arrayOf(2, 0, 0))
            mDatabase.execSQL(insertSQL, arrayOf(3, 0, 0))
            mDatabase.execSQL(insertSQL, arrayOf(4, 0, 0))
            mDatabase.execSQL(insertSQL, arrayOf(5, 0, 0))
            mDatabase.execSQL(insertSQL, arrayOf(6, 0, 0))
            mDatabase.execSQL(insertSQL, arrayOf(7, 0, 0))
            mDatabase.execSQL(insertSQL, arrayOf(8, 0, 0))
            mDatabase.execSQL(insertSQL, arrayOf(9, 0, 0))
            mDatabase.execSQL(insertSQL, arrayOf(10, 0, 0))
        }
        catch (e: Exception)
        {
        }

    }
}
