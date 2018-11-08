package info.kingpes.rockpaperscissorsonline.utils;

import info.kingpes.rockpaperscissorsonline.BuildConfig;

/**
 * Created by Chau Huynh on 6/11/2017.
 */

public class Version {
    private static Version instance;

    public static Version getInstance() {
        if (instance == null) {
            instance = new Version();
        }
        return instance;
    }

    public static final int UPDATE = 1;
    public static final int FORCE = 2;
    public static final int CONTINUE = 3;


    public int check(String serverVersion) {
        int[] app = changeVersionToInt(BuildConfig.VERSION_NAME);
        int[] server = changeVersionToInt(serverVersion);
        if (app[0] < server[0]) {
            // Force user to update
            return FORCE;
        } else {
            if (app[1] < server[1]) {
                //Update but user can cancel and continue using app
                return UPDATE;
            } else {
                if (app[2] < server[2]) {
                    //Update but user can cancel and continue using app
                    return UPDATE;
                } else {
                    //Continues
                    return CONTINUE;
                }

            }
        }
    }

    private int[] changeVersionToInt(String version) {
        String[] temp = version.split("\\.");
        int[] intVer = new int[3];
        for (int i = 0; i < intVer.length; i++) {
            if (i == 2 & temp.length != 3) {
                intVer[2] = 0;
            } else {
                intVer[i] = Integer.parseInt(temp[i]);
            }
        }
        return intVer;
    }
}
