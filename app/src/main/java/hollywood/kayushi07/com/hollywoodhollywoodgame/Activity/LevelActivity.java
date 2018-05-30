package hollywood.kayushi07.com.hollywoodhollywoodgame.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import hollywood.kayushi07.com.hollywoodhollywoodgame.Adapter.MultiViewTypeAdapter;
import hollywood.kayushi07.com.hollywoodhollywoodgame.Model.Model;
import hollywood.kayushi07.com.hollywoodhollywoodgame.R;

public class LevelActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels);


        ArrayList<Model> list= new ArrayList<>();
//        list.add(new Model(Model.TEXT_TYPE,"LEVELS",0));
        list.add(new Model(Model.OPEN_LEVEL,"Level 1",0));
        list.add(new Model(Model.OPEN_LEVEL,"LEVEL 2",0));
        list.add(new Model(Model.OPEN_LEVEL,"LEVEL 3",0));
        list.add(new Model(Model.OPEN_LEVEL,"LEVEL 4",0));
        list.add(new Model(Model.CLOSED_LEVEL,"Level 5",0));
        list.add(new Model(Model.CLOSED_LEVEL,"LEVEL 6",0));
        list.add(new Model(Model.CLOSED_LEVEL,"LEVEL 7",0));
        list.add(new Model(Model.CLOSED_LEVEL,"LEVEL 8",0));
        list.add(new Model(Model.CLOSED_LEVEL,"Level 9",0));
        list.add(new Model(Model.CLOSED_LEVEL,"Level 10",0));

        MultiViewTypeAdapter adapter = new MultiViewTypeAdapter(list,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(adapter);

    }
}