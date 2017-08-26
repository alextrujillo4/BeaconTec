package com.example.alextrujillo.beacontec;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alextrujillo on 13/06/17.
 */

public class PlaceSpinnerAdapter extends BaseAdapter {
    private Context context;
    private List<Drawable> flags = new ArrayList<Drawable>();
    private List<String> barberNames = new ArrayList<String>();;
    private List<String> barberTimes = new ArrayList<String>();;
    private LayoutInflater inflter;
    private TextView names;
    private ImageView icon;
    private TextView time;

    public PlaceSpinnerAdapter(Context applicationContext, List<Drawable> flags, List<String> barberNames, List<String> barberTimes) {
        this.context = applicationContext;
        this.flags = flags;
        this.barberNames = barberNames;
        this.barberTimes = barberTimes;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return flags.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


  public String getBarberName(int i) {
        return barberNames.get(i);
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.spinner_layout_places, null);
        icon = view.findViewById(R.id.img);
        names = view.findViewById(R.id.name);
        time = view.findViewById(R.id.time);

            icon.setImageDrawable(flags.get(i));
            names.setText(barberNames.get(i));
            time.setText(barberTimes.get(i));

        return view;
    }

    public String getBarberName() {
        return names.getText().toString();

    }

}