package net.projectbarks.thelaugher.jokes;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;

import net.projectbarks.thelaugher.R;
import net.projectbarks.thelaugher.joketype.JokeType;

/**
 * Created by brandon on 10/19/14.
 */
public class JokesAdapter extends ArrayAdapter<Joke> {

    private static class CacheView {
        View backBackground;
        View frontBackground;
        TextView text;
    }

    private int layoutResouceId;

    public JokesAdapter(Context context, int layoutResourceId, Joke[] data) {
        super(context, layoutResourceId, data);
        this.layoutResouceId = layoutResourceId;
    }

    @Override
    public View getView(int position, View createdView, ViewGroup parent) {
        SwipeLayout row = (SwipeLayout) createdView;
        Joke joke = getItem(position);
        CacheView cache;

        if (row == null) {
            LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater();
            row = (SwipeLayout) inflater.inflate(layoutResouceId, parent, false);
            row.setShowMode(SwipeLayout.ShowMode.LayDown);
            row.setDragEdge(SwipeLayout.DragEdge.Right);
            cache = new CacheView();
            cache.backBackground = row.findViewById(R.id.bottom_wrapper);
            cache.frontBackground= row.findViewById(R.id.surface);
            cache.text = (TextView) row.findViewById(R.id.jokeContent);
            row.setTag(cache);
        } else {
            cache = (CacheView) row.getTag();
        }
        cache.frontBackground.setBackgroundColor(joke.getType().toColor(getContext()));
        cache.backBackground.setBackgroundColor(joke.getType().toColorHighlight(getContext()));
        cache.text.setText(joke.getJoke());

        return row;
    }

}
