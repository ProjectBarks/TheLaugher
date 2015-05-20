package net.projectbarks.thelaugher.joketype;

import android.content.Context;
import android.content.res.Resources;

import net.projectbarks.thelaugher.R;

import lombok.Getter;

/**
 * Created by brandon on 10/19/14.
 */
public enum JokeType {

    RANDOM("Random", 0, R.color.turquoise, R.color.green_sea),
    ONE_LINERS("One Liners", 1, R.color.sun_flower, R.color.orange),
    JOKES_FOR_NERDS("For Nerds", 2, R.color.emerald, R.color.nephritis),
    LIGHT_BULB_JOKES("Light Bulb", 3, R.color.carrot, R.color.pumpkin),
    RELIGIOUS_JOKES("Religious", 4, R.color.peter_river,  R.color.belize_hole),
    BLONDE("Blonde", 5, R.color.alizarin, R.color.pomegranate),
    POLITICAL("Political", 6, R.color.amethyst, R.color.wisteria),
    DIRTY_JOKES("Dirty", 7, R.color.wet_asphalt, R.color.midnight_blue),
    ETHNIC_JOKES("Ethnic", 8, R.color.green_sea,  R.color.turquoise),
    YO_MAMA("Yo Mama", 9,  R.color.orange,  R.color.sun_flower),
    CHUCK_NORRIS("Chuck Norris", 10, R.color.pumpkin,  R.color.carrot);

    @Getter
    private int mainColor;
    @Getter
    private int highlightColor;
    @Getter
    private String name;
    @Getter
    private int id;

    private JokeType(String name, int id, int mainColor, int highlightColor) {
        this.name = name;
        this.id = id;
        this.mainColor = mainColor;
        this.highlightColor = highlightColor;
    }

    public int toColor(Context context) {
        return context.getResources().getColor(getMainColor());
    }

    public int toColorHighlight(Context context) {
        return context.getResources().getColor(getHighlightColor());
    }
}
