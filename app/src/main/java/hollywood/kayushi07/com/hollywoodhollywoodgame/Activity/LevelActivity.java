package hollywood.kayushi07.com.hollywoodhollywoodgame.Activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import hollywood.kayushi07.com.hollywoodhollywoodgame.Adapter.*;
import hollywood.kayushi07.com.hollywoodhollywoodgame.Model.*;
import hollywood.kayushi07.com.hollywoodhollywoodgame.R;

public class LevelActivity extends AppCompatActivity {


    ArrayList<Model> levelListFinal;
    DatabaseHandler mDB;
    RecyclerView mRecyclerView;
    MultiViewTypeAdapter adapter;
    LinearLayoutManager linearLayoutManager;

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