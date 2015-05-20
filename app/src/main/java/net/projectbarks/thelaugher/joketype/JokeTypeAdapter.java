package net.projectbarks.thelaugher.joketype;

import android.app.Activity;
import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import net.projectbarks.thelaugher.R;

import lombok.Setter;

/**
 * Created by brandon on 10/19/14.
 */
public class JokeTypeAdapter extends ArrayAdapter<JokeType> {

    private static class CacheView {
        View background;
        TextView text;
    }

    @Setter
    private AdapterView.OnItemClickListener onItemClickListener;
    private int mLayoutResouceId;
    private GestureDetector mGestureDetector;

    public JokeTypeAdapter(Context context, int layoutResourceId, JokeType[] data) {
        super(context, layoutResourceId, data);
        this.mLayoutResouceId = layoutResourceId;
        this.mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    @Override
    public View getView(int position, View createdView, ViewGroup parent) {
        View row = createdView;
        JokeType type = getItem(position);
        CacheView cache;

        if (row == null) {
            LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater();
            row = inflater.inflate(mLayoutResouceId, parent, false);
            cache = new CacheView();
            cache.background = row.findViewById(R.id.jokeType);
            cache.text = (TextView) row.findViewById(R.id.jokeTypeName);
            row.setTag(cache);
            row.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (onItemClickListener == null || !mGestureDetector.onTouchEvent(motionEvent)) {
                        return false;
                    }
                    JokeType type = null;
                    String text = (String) ((TextView)view.findViewById(R.id.jokeTypeName)).getText();
                    for (JokeType possibility : JokeType.values()) {
                        if (!possibility.getName().equals(text)) {
                            continue;
                        }
                        type = possibility;
                        break;
                    }
                    int position = getPosition(type);
                    onItemClickListener.onItemClick((AdapterView<?>) view.getParent(), view, position, view.getId());
                    return false;
                }
            });
        } else {
            cache = (CacheView) row.getTag();
        }

        cache.background.setBackgroundColor(type.toColor(getContext()));
        cache.text.setText(type.getName());

        return row;
    }
}
