package hollywood.kayushi07.com.hollywoodhollywoodgame.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hollywood.kayushi07.com.hollywoodhollywoodgame.Activity.Dummy;
import hollywood.kayushi07.com.hollywoodhollywoodgame.Activity.GameActivity;
import hollywood.kayushi07.com.hollywoodhollywoodgame.Model.Level;
import hollywood.kayushi07.com.hollywoodhollywoodgame.Model.Model;
import hollywood.kayushi07.com.hollywoodhollywoodgame.R;



public class MultiViewTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Model> dataSet;
    Context mContext;
    int total_types, listLayoutRes;
//    SQLiteDatabase mDatabase;

    public MultiViewTypeAdapter(ArrayList<Model> data, Context context) {
        this.dataSet = data;
        this.mContext = context;
        total_types = dataSet.size();
//        this.mDatabase = mDatabase;

    }

    public static class ClosedLevelViewHolder extends RecyclerView.ViewHolder {
        TextView txtType, txtMsg;
//        ImageView image;

        public ClosedLevelViewHolder(View itemView) {
            super(itemView);
            this.txtType = (TextView) itemView.findViewById(R.id.type);
            this.txtMsg = (TextView) itemView.findViewById(R.id.levelmsg);
//            this.image = (ImageView) itemView.findViewById(R.id.background);
        }
    }

    public static class OpenLevelViewHolder extends RecyclerView.ViewHolder {
        TextView txtLevel, txtPoints, txtMovies;
        Button start;

        public OpenLevelViewHolder(View itemView) {
            super(itemView);
            this.txtLevel = (TextView) itemView.findViewById(R.id.level_id);
            this.txtPoints = (TextView) itemView.findViewById(R.id.points_count);
            this.txtMovies = (TextView) itemView.findViewById(R.id.movie_count);
            this.start = (Button) itemView.findViewById(R.id.start);
        }
    }

   

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case Model.CLOSED_LEVEL:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.closed_level, parent, false);
                return new ClosedLevelViewHolder(view);
            case Model.OPEN_LEVEL:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.open_level, parent, false);
                return new OpenLevelViewHolder(view);
        }
        return null;


    }


    @Override
    public int getItemViewType(int position) {

        switch (dataSet.get(position).type) {
            case 0:
                return Model.CLOSED_LEVEL;
            case 1:
                return Model.OPEN_LEVEL;
            default:
                return -1;
        }


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

        Model object = dataSet.get(listPosition);

        if (object != null) {
            switch (object.type) {
                case Model.CLOSED_LEVEL:

                    Model object1 = dataSet.get(listPosition - 1);


                    int unlock_score, total_score, diff_score;
                    unlock_score = object1.unlock_score;
                    total_score = object1.score;
                    diff_score = unlock_score - total_score;
                    String msg = "Score " + diff_score + " points to unlock Movie set.";

                    ((ClosedLevelViewHolder) holder).txtType.setText(object.text);
                    ((ClosedLevelViewHolder) holder).txtMsg.setText(msg);
//                    ((ClosedLevelViewHolder) holder).image.setImageResource(object.data);
                    break;
                case Model.OPEN_LEVEL:
                    ((OpenLevelViewHolder) holder).txtLevel.setText(object.text);
                    ((OpenLevelViewHolder) holder).txtPoints.setText(" "+object.score);
                    ((OpenLevelViewHolder) holder).txtMovies.setText(" "+object.movies);

                    ((OpenLevelViewHolder) holder).start.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {

                            System.out.println("POSITION :  " + listPosition);

                            Intent i = new Intent(mContext, GameActivity.class);
                            int pos = listPosition;
                            Dummy.setCurrent_level(pos+1);
//                            pos++;
//                            i.putExtra("Level",pos);
                            mContext.startActivity(i);
//                            ((Activity)mContext).finish();
                            }
                    });


//                    ((OpenLevelViewHolder) holder).fab.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//
//                            if (fabStateVolume) {
//                                if (mPlayer.isPlaying()) {
//                                    mPlayer.stop();
//
//                                }
//                                ((OpenLevelViewHolder) holder).fab.setImageResource(R.drawable.volume);
//                                fabStateVolume = false;
//
//                            } else {
//                                mPlayer = MediaPlayer.create(mContext, R.raw.sound);
//                                mPlayer.setLooping(true);
//                                mPlayer.start();
//                                ((OpenLevelViewHolder) holder).fab.setImageResource(R.drawable.mute);
//                                fabStateVolume = true;
//
//                            }
//                        }
//                    });


                    break;
            }
        }

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

//    private void loadLevelsFromDatabase() {
//        Cursor cursorLevels = mDatabase.rawQuery("SELECT * FROM levels", null);
//        if (cursorLevels.moveToFirst()) {
//            levelList.clear();
//            do {
//                levelList.add(new Level(
//                        cursorLevels.getInt(0),
//                        cursorLevels.getInt(1),
//                        cursorLevels.getInt(2)
//                ));
//            } while (cursorLevels.moveToNext());
//        }
//        cursorLevels.close();
//        notifyDataSetChanged();
//    }


}
