package com.umg.trains;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TrainAdapter extends ArrayAdapter<Train> {


    private Context mContext;
    int mResource;

    public TrainAdapter(@NonNull Context context,int resource, ArrayList<Train> objects) {
        super(context, 0, objects);
        mContext = context;
        mResource = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Train train = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(mResource, parent, false);
        }

        TextView hp = (TextView) convertView.findViewById(R.id.trainHP);
        hp.setText("HP " + train.getHp());

        TextView damage = (TextView) convertView.findViewById(R.id.trainDamage);
        damage.setText("Damage " + train.getDamage());

        TextView atackSpeed = (TextView) convertView.findViewById(R.id.trainAtackSpeed);
        atackSpeed.setText("Atack Speed " + train.getAtackSpeed());

        TextView defense = (TextView) convertView.findViewById(R.id.trainDefense);
        defense.setText("Defense " + train.getDefense());

        ImageView img = (ImageView) convertView.findViewById(R.id.trainImage);
        img.setImageResource(train.getImage());


        return convertView;

    }
}
