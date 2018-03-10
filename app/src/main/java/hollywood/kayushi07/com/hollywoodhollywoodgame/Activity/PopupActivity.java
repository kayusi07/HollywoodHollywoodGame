package hollywood.kayushi07.com.hollywoodhollywoodgame.Activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import hollywood.kayushi07.com.hollywoodhollywoodgame.R;
import hollywood.kayushi07.com.hollywoodhollywoodgame.Receiver.NetworkStateReceiver;

/**
 * Created by Ayushi on 27-01-2018.
 */

public class PopupActivity extends AppCompatActivity implements NetworkStateReceiver.NetworkStateReceiverListener {

    private NetworkStateReceiver networkStateReceiver;
    AdView mAdView;

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

        final RatingBar rate = (RatingBar) findViewById(R.id.rate);
        rate.setRating(score/2);

        TextView txtScore = (TextView) findViewById(R.id.txt_status);
        TextView txtTotalScore = (TextView) findViewById(R.id.txt_total_score);
        TextView txtHighestScore = (TextView) findViewById(R.id.txt_high_score);


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


        int total_Score;

        SharedPreferences prefs = getSharedPreferences("Bollywood Score", MODE_PRIVATE);

        SharedPreferences.Editor editor =  prefs.edit();
        int scoreText = prefs.getInt("gameScore", 0);
        int high_score = prefs.getInt("highScore",0);
       total_Score = score + scoreText;
        editor.putInt("gameScore", total_Score);

        txtTotalScore.setText("" + total_Score);//total_Score);

        if(total_Score>high_score){
            high_score=total_Score;
            editor.putInt("highScore", high_score);}

        editor.apply();
        txtHighestScore.setText("" + high_score);


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

