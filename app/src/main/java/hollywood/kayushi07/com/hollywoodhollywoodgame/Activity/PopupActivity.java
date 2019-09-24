package hollywood.kayushi07.com.hollywoodhollywoodgame.Activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import hollywood.kayushi07.com.hollywoodhollywoodgame.Model.DatabaseHandler;
import hollywood.kayushi07.com.hollywoodhollywoodgame.R;
import hollywood.kayushi07.com.hollywoodhollywoodgame.Receiver.NetworkStateReceiver;

/**
 * Created by Ayushi on 27-01-2018.
 */

public class PopupActivity extends AppCompatActivity implements NetworkStateReceiver.NetworkStateReceiverListener {

    private NetworkStateReceiver networkStateReceiver;
    AdView mAdView;
    int level;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);

        networkStateReceiver = new NetworkStateReceiver();
        networkStateReceiver.addListener(this);
        this.registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));

        mAdView = (AdView) findViewById(R.id.adViewpop);


        Intent intent = getIntent();
        int score = intent.getIntExtra("Score",1);
        level = intent.getIntExtra("Level",1);
        final RatingBar rate = (RatingBar) findViewById(R.id.rate);
        rate.setRating(score/2);

        DatabaseHandler mDB = new DatabaseHandler(getApplicationContext());
//        SQLiteDatabase db = mDB.getReadableDatabase();

        int[] sc;
        sc = mDB.getScore(level);
        int total_score, unlock_score;
        total_score = sc[0];
        unlock_score = sc[1];

        int f_score, diff_score;
        f_score= score + total_score;
        mDB.updateScore(level, f_score);

        diff_score = unlock_score - f_score;

        TextView txtScore = (TextView) findViewById(R.id.txt_status);
        TextView txtTotalScore = (TextView) findViewById(R.id.txt_total_score);
        TextView txtHighestScore = (TextView) findViewById(R.id.txt_high_score);
        TextView txtLevel = (TextView) findViewById(R.id.level);
        txtLevel.setText("LEVEL " + level);

        Button next = (Button) findViewById(R.id.next_game);

        switch(score){
            case 1:
                txtScore.setText("Try Another Movie!");
                break;
            case 2:
                txtScore.setText("Try Another Movie!");
                break;
            case 3:
                txtScore.setText("Try Another Movie!");
                break;
            case 4:
                txtScore.setText("Try Another Movie!");
                break;
            case 5:
                txtScore.setText("Fair!");
                break;
            case 6:
                txtScore.setText("Great!");
                break;
            case 7:
                txtScore.setText("Awesom!");
                break;
            case 8:
                txtScore.setText("Fabulous");
                break;
            case 9:
                txtScore.setText("Splendid!");
                break;
            case 10:
                txtScore.setText("Excellent!");
                break;


        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PopupActivity.this, GameActivity.class);
                startActivity(i);
                finish();
            }
        });


//        int total_Score;
//        SharedPreferences prefs = getSharedPreferences("Bollywood Score", MODE_PRIVATE);
//        SharedPreferences.Editor editor =  prefs.edit();
//        int scoreText = prefs.getInt("gameScore", 0);
//        int high_score = prefs.getInt("highScore",0);
//       total_Score = score + scoreText;
//        editor.putInt("gameScore", total_Score);
//        if(total_Score>high_score){
//            high_score=total_Score;
//            editor.putInt("highScore", high_score);}//
//        editor.apply();


        txtTotalScore.setText("" + f_score);//total_Score);

        String nextLevelStr;
        if(diff_score <= 0)
        {
            nextLevelStr = " ";
        }
        else
        nextLevelStr = "You need " + diff_score + " more points to unlock next level.";

        txtHighestScore.setText(nextLevelStr);


    }
    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {

        networkStateReceiver.removeListener(this);
        this.unregisterReceiver(networkStateReceiver);

        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

    @Override
    public void networkAvailable() {
        mAdView.setVisibility(View.VISIBLE);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public void networkUnavailable() {
        mAdView.setVisibility(View.GONE);
    }
}

