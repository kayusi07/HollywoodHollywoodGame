package hollywood.kayushi07.com.hollywoodhollywoodgame.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import hollywood.kayushi07.com.hollywoodhollywoodgame.Activity.Dummy;
import hollywood.kayushi07.com.hollywoodhollywoodgame.Activity.GameActivity;
import hollywood.kayushi07.com.hollywoodhollywoodgame.Model.Model;
import hollywood.kayushi07.com.hollywoodhollywoodgame.R;



public class MultiViewTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Model> dataSet;
    Context mContext;
    int total_types;

    public static class TextTypeViewHolder extends RecyclerView.ViewHolder {


        TextView txtType;
        CardView cardView;

        public TextTypeViewHolder(View itemView) {
            super(itemView);

            this.txtType = (TextView) itemView.findViewById(R.id.type);
            this.cardView = (CardView) itemView.findViewById(R.id.card_view);

        }

    }

    public static class ClosedLevelViewHolder extends RecyclerView.ViewHolder {


        TextView txtType;
//        ImageView image;

        public ClosedLevelViewHolder(View itemView) {
            super(itemView);

            this.txtType = (TextView) itemView.findViewById(R.id.type);
//            this.image = (ImageView) itemView.findViewById(R.id.background);

        }

    }

    public static class OpenLevelViewHolder extends RecyclerView.ViewHolder {


        TextView txtType;
        Button fab;

        public OpenLevelViewHolder(View itemView) {
            super(itemView);

            this.txtType = (TextView) itemView.findViewById(R.id.type);
            this.fab = (Button) itemView.findViewById(R.id.start);

        }

    }

    public MultiViewTypeAdapter(ArrayList<Model> data, Context context) {
        this.dataSet = data;
        this.mContext = context;
        total_types = dataSet.size();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case Model.TEXT_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.name_level, parent, false);
                return new TextTypeViewHolder(view);
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
                return Model.TEXT_TYPE;
            case 1:
                return Model.CLOSED_LEVEL;
            case 2:
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
                case Model.TEXT_TYPE:
                    ((TextTypeViewHolder) holder).txtType.setText(object.text);

                    break;
                case Model.CLOSED_LEVEL:
                    ((ClosedLevelViewHolder) holder).txtType.setText(object.text);
//                    ((ClosedLevelViewHolder) holder).image.setImageResource(object.data);
                    break;
                case Model.OPEN_LEVEL:


                    ((OpenLevelViewHolder) holder).txtType.setText(object.text);
                    ((OpenLevelViewHolder) holder).fab.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {

                            System.out.println("POSITION :  " + listPosition);

                            Intent i = new Intent(mContext, GameActivity.class);
                            int pos = listPosition;
                            Dummy.setCurrent_level(pos+1);
//                            pos++;
//                            i.putExtra("Level",pos);
                               mContext.startActivity(i);
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


}
