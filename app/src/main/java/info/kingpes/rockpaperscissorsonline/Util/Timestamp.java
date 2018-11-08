package info.kingpes.rockpaperscissorsonline.Util;

/**
 * Created by Chau Huynh on 09/02/02017.
 */

public class Timestamp {
    public static String convert(long seconds) {
        String times = "";
        seconds = seconds / 1000;
        if (seconds < 60) {
            times = "seconds ago";
        } else {
            if (seconds < (60 * 60)) {
                int minutes = (int) (seconds / 60);
                times = minutes + " min ago";
            } else {
                if (seconds < (24 * 60 * 60)) {
                    int hours = (int) (seconds / (60 * 60));
                    int mins = (int) (seconds % (60 * 60));
                    mins = mins / 60;
                    if (mins <= 0) {
                        times = hours + " h ago";
                    } else {
                        times = hours + " h " + mins + " min ago";
                    }

                } else {
                    if (seconds < (30 * 24 * 60 * 60)) {
                        int days = (int) (seconds / (24 * 60 * 60));
                        int hours = (int) (seconds % (24 * 60 * 60));
                        hours = hours / (60 * 60);
                        if (hours <= 0) {
                            times = days + " d ago";
                        } else {
                            times = days + " d " + hours + " h ago";
                        }
                    } else {
                        if (seconds < (12 * 30 * 24 * 60 * 60)) {
                            int months = (int) (seconds / (30 * 24 * 60 * 60));
                            int days = (int) (seconds % (30 * 24 * 60 * 60));
                            days = days / (24 * 60 * 60);
                            if (days <= 0) {
                                times = months + " m ago";
                            } else {
                                times = months + " m " + days + " d ago";
                            }
                        } else {
                            int years = (int) (seconds / (12 * 31 * 24 * 60 * 60));
                            int months = (int) (seconds % (12 * 31 * 24 * 60 * 60));
                            months = months / (30 * 24 * 60 * 60);
                            if (months <= 0) {
                                times = years + " y ago";
                            } else {
                                times = years + " y " + months + " m ago";
                            }
                        }
                    }
                }
            }
        }
        return times;
    }
}