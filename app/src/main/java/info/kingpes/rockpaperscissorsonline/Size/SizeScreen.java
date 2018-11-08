package info.kingpes.rockpaperscissorsonline.Size;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by Chau Huynh on 16/02/02017.
 */

public class SizeScreen {
    private Point point;

    public SizeScreen(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        point = new Point();
        display.getSize(point);
    }

    public Point getSize() { //=>int width=point.x, heigth=point.y
        return point;
    }

    public int getY_Animation_RPS() {
        int y;
        if (point.y < 1000) {
            y = (point.y / 2) - 120;
        } else {
            y = (point.y / 2) - 120 - 40;
        }
        return y;
    }

    public int[] get_Animation_Icon() {
        int[] x = new int[2];
        if (point.y <= 1000) {
            x[0] = (point.x / 2) - 100;
            x[1] = (point.y / 2) - 180;
        } else {
            x[0] = (point.x / 2) - 100 - 40;
            x[1] = (point.y / 2) - 180 - 40;
        }
        return x;
    }
}
