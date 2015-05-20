package net.projectbarks.thelaugher;


import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import com.tuenti.buttonmenu.ButtonMenu;
import com.tuenti.buttonmenu.animator.ObjectAnimatorFactory;
import com.tuenti.buttonmenu.animator.ScrollAnimator;
import com.tuenti.buttonmenu.viewmodel.button.SimpleButtonVM;
import com.tuenti.buttonmenu.viewmodel.buttonmenu.SimpleButtonMenuVM;

import net.projectbarks.thelaugher.joketype.JokeType;
import net.projectbarks.thelaugher.joketype.JokeTypeAdapter;


public class  MainActivity extends ActionBarActivity {

    private ButtonMenu mButtonMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setup adapter for joke types
        JokeTypeAdapter adapter = new JokeTypeAdapter(this, R.layout.main_joketype_row, JokeType.values());
        ((ListView)findViewById(R.id.jokes)).setAdapter(adapter);

        //init button menu
        ScrollAnimator animator = initButtonMenu();
        initScrollListener(animator);
        initListClickListener(adapter);
    }

    private ScrollAnimator initButtonMenu() {
        //setup buttons in menu
        SimpleButtonMenuVM mSimpleButtonMenuVM = new SimpleButtonMenuVM();
        mSimpleButtonMenuVM.addItem(new SimpleButtonVM(R.layout.button_post, R.id.post, null));
        mSimpleButtonMenuVM.addItem(new SimpleButtonVM(R.layout.button_profile, R.id.profile, null));
        mSimpleButtonMenuVM.addItem(new SimpleButtonVM(R.layout.button_info, R.id.info, null));

        //Configure buttom menu view
        mButtonMenu = (ButtonMenu) findViewById(R.id.button_menu);
        mButtonMenu.setButtonMenuVM(mSimpleButtonMenuVM);
        mButtonMenu.initialize();

        //Configure list
        ScrollAnimator scrollAnimator = new ScrollAnimator(mButtonMenu, new ObjectAnimatorFactory());
        scrollAnimator.configureListView((ListView) findViewById(R.id.jokes));
        scrollAnimator.setDurationInMillis(300);
        return scrollAnimator;
    }

    private void initScrollListener(ScrollAnimator animator) {
        animator.setAdditionalScrollListener(new AbsListView.OnScrollListener() {
            JokeType previous = JokeType.RANDOM;

            @Override
            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (mButtonMenu.getVisibility() != View.VISIBLE) {
                    return;
                }
                int lastVisible = absListView.getLastVisiblePosition();
                JokeType item = (JokeType) absListView.getItemAtPosition(lastVisible);
                if (previous == item) {
                    return;
                }
                int colorFrom = previous.toColor(getBaseContext());
                int colorTo = item.toColor(getBaseContext());
                previous = item;
                ObjectAnimator.ofObject(mButtonMenu, "backgroundColor", new ArgbEvaluator(), colorFrom, colorTo)
                        .setDuration(200)
                        .start();
            }

            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {}
        });
    }

    private void initListClickListener(final JokeTypeAdapter adapter) {
        adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                JokeType type = adapter.getItem(i);
                final Intent intent = new Intent(MainActivity.this, JokeActivity.class);
                Bundle b = new Bundle();
                b.putString("type", type.name());
                intent.putExtras(b);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(intent);
                        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
                    }
                }, 300);
            }
        });
    }
}
