package info.kingpes.rockpaperscissorsonline.Util;

/**
 * Created by Chau Huynh on 06/02/02017.
 */

public class Area {
    public static int getArea(int stars) {
        int area = 0;
        if (stars <= 5) {
            area = 1;
        }
        if (stars > 5 && stars <= 10) {
            area = 2;
        }
        if (stars > 10 && stars <= 20) {
            area = 3;
        }
        if (stars > 20 && stars <= 40) {
            area = 4;
        }
        if (stars > 40 && stars <= 70) {
            area = 5;
        }
        if (stars > 70 && stars <= 100) {
            area = 6;
        }
        if (stars > 100 && stars <= 150) {
            area = 7;
        }
        if (stars > 150 && stars <= 200) {
            area = 8;
        }
        if (stars > 200 && stars <= 300) {
            area = 9;
        }
        if (stars > 300 && stars <= 500) {
            area = 10;
        }
        if (stars > 500) {
            area = 11;
        }
        return area;
    }

    public static int[] getSpaceArea(int are) {
        int[] a = new int[2];
        if (are == 1) {
            a[0] = 1;
            a[1] = 5;
        }
        if (are == 2) {
            a[0] = 6;
            a[1] = 10;
        }
        if (are == 3) {
            a[0] = 11;
            a[1] = 20;
        }
        if (are == 4) {
            a[0] = 21;
            a[1] = 40;
        }
        if (are == 5) {
            a[0] = 41;
            a[1] = 70;
        }
        if (are == 6) {
            a[0] = 71;
            a[1] = 100;
        }
        if (are == 7) {
            a[0] = 101;
            a[1] = 150;
        }
        if (are == 8) {
            a[0] = 151;
            a[1] = 200;
        }
        if (are == 9) {
            a[0] = 201;
            a[1] = 300;
        }
        if (are == 10) {
            a[0] = 301;
            a[1] = 500;
        }
        if (are == 11) {
            a[0] = 501;
            a[1] = 2000;
        }
        return a;
    }
}
