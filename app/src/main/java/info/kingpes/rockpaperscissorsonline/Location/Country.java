package info.kingpes.rockpaperscissorsonline.Location;

import android.content.Context;
import android.content.res.Configuration;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Chau Huynh on 28/01/02017.
 */

public class Country {
    public static String getCountry(Context context) {
        //Set Default Language
        setDefaultLanguage(context);

        String country = null;
        LocationService locationService = new LocationService(context);
        Location location = locationService.getLocation(LocationManager.NETWORK_PROVIDER);
        if (location != null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            country = convertCountry(context, latitude, longitude);
        }
        return country;
    }

    private static String convertCountry(Context context, double lat, double lng) {
        String country = null;
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(lat, lng, 1);
        } catch (IOException e) {
        }
        if (addresses != null) {
            if (addresses.size() > 0) {
                country = addresses.get(0).getCountryName();
//                String address = addresses.get(0).getAddressLine(0);
//                String city = addresses.get(0).getLocality();
//                String state = addresses.get(0).getAdminArea();
//                String postalCode = addresses.get(0).getPostalCode();
//                String knownName = addresses.get(0).getFeatureName();

            }
        }
        return country;
    }

    public static void setDefaultLanguage(Context context) {
        Locale locale = new Locale("en_US");
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        context.getApplicationContext().getResources().updateConfiguration(configuration, null);
    }
}
