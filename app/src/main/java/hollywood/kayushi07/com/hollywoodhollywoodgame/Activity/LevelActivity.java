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


//    List<Level> levelList;
    ArrayList<Model> levelListFinal;
    SQLiteDatabase mDatabase;
    MultiViewTypeAdapter multiViewTypeAdapter;
//    ListView listViewLevels;
//    LevelAdapter adapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels);

//        levelList = new ArrayList<>();

//        ArrayList<Model> list= new ArrayList<>();
////        list.add(new Model(Model.TEXT_TYPE,"LEVELS",0));
//        list.add(new Model(Model.OPEN_LEVEL,"LEVEL 1",0));
//        list.add(new Model(Model.OPEN_LEVEL,"LEVEL 2",0));
//        list.add(new Model(Model.OPEN_LEVEL,"LEVEL 3",0));
//        list.add(new Model(Model.OPEN_LEVEL,"LEVEL 4",0));
//        list.add(new Model(Model.CLOSED_LEVEL,"LEVEL 5",0));
//        list.add(new Model(Model.CLOSED_LEVEL,"LEVEL 6",0));
//        list.add(new Model(Model.CLOSED_LEVEL,"LEVEL 7",0));
//        list.add(new Model(Model.CLOSED_LEVEL,"LEVEL 8",0));
//        list.add(new Model(Model.CLOSED_LEVEL,"LEVEL 9",0));
//        list.add(new Model(Model.CLOSED_LEVEL,"LEVEL 10",0));


        //opening the database
        mDatabase = openOrCreateDatabase("hollywoodgamedb", MODE_PRIVATE, null);

        //this method will display the levels in the list
        levelListFinal = new ArrayList<>();

        //we used rawQuery(sql, selectionargs) for fetching all the levels
        Cursor cursorLevels = mDatabase.rawQuery("SELECT * FROM levels", null);
        int id, movies, score;
        //if the cursor has some data
        if (cursorLevels.moveToFirst()) {
            //looping through all the records
            do {

                id = cursorLevels.getInt(0);
                movies = cursorLevels.getInt(1);
                score = cursorLevels.getInt(2);
                //pushing each record in the level list
//                levelList.add(new Level(id, movies, score));


                levelListFinal.add(new Model(Model.OPEN_LEVEL,"LEVEL " + id, id, movies, score));


            } while (cursorLevels.moveToNext());
        }
        //closing the cursor
        cursorLevels.close();

        MultiViewTypeAdapter adapter = new MultiViewTypeAdapter(levelListFinal,this, mDatabase);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);



//        mRecyclerView.setLayoutManager(linearLayoutManager);
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        mRecyclerView.setAdapter(adapter);

    }





}