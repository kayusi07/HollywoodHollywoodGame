package hollywood.kayushi07.com.hollywoodhollywoodgame.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hollywood.kayushi07.com.hollywoodhollywoodgame.Adapter.*;
import hollywood.kayushi07.com.hollywoodhollywoodgame.Model.*;
import hollywood.kayushi07.com.hollywoodhollywoodgame.R;

public class LevelActivity extends AppCompatActivity {


    ArrayList<Model> levelListFinal;
    DatabaseHandler mDB;
    RecyclerView mRecyclerView;
    MultiViewTypeAdapter adapter;
    LinearLayoutManager linearLayoutManager;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels);

        mDB = new DatabaseHandler(getApplicationContext());
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);


        levelListFinal = mDB.getAllLevels();
        adapter = new MultiViewTypeAdapter(levelListFinal, this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();

        levelListFinal = mDB.getAllLevels();
        adapter = new MultiViewTypeAdapter(levelListFinal, this);
        mRecyclerView.setAdapter(adapter);
    }
}