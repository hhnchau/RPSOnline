package info.kingpes.rockpaperscissorsonline.Font;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by Chau Huynh on 17/02/02017.
 */

public class Font {
    private Typeface font_nosifer, font_atarian, font_dimbo_regular, font_super_mario;

    public Font(Context context) {
        font_nosifer = Typeface.createFromAsset(context.getAssets(), "nosifer.ttf");
        font_atarian = Typeface.createFromAsset(context.getAssets(), "atarian.ttf");
        font_dimbo_regular = Typeface.createFromAsset(context.getAssets(), "dimbo_regular.ttf");
        font_super_mario = Typeface.createFromAsset(context.getAssets(), "super_mario.ttf");
    }

    public Typeface getFontNosifer() {
        return font_nosifer;
    }

    public Typeface getFontAtarian() {
        return font_atarian;
    }

    public Typeface getFontDimbo() {
        return font_dimbo_regular;
    }

    public Typeface getFontSuperMario() {
        return font_super_mario;
    }
}
