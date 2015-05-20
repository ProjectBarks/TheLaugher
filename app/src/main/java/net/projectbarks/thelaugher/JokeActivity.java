package net.projectbarks.thelaugher;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.ListView;

import net.projectbarks.thelaugher.jokes.Joke;
import net.projectbarks.thelaugher.jokes.JokesAdapter;
import net.projectbarks.thelaugher.joketype.JokeType;


public class JokeActivity extends ActionBarActivity {

    private JokeType mJokeType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        Bundle b = getIntent().getExtras();
        mJokeType = JokeType.valueOf(b.getString("type"));

        JokesAdapter adapter = new JokesAdapter(this, R.layout.swipe_joke, new Joke[]{
            new Joke("Nock nock. Whos there? Me", "", mJokeType),
            new Joke("Nock nock. Whos there? Me", "", mJokeType),
            new Joke("Nock nock. Whos there? Me", "", mJokeType),
            new Joke("Nock nock. Whos there? Me", "", mJokeType),
        });

        ((ListView)findViewById(R.id.jokeList)).setAdapter(adapter);
        findViewById(R.id.jokeListBackground).setBackgroundColor(mJokeType.toColor(this));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(mJokeType.toColor(this)));
        getSupportActionBar().setTitle(mJokeType.getName());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
