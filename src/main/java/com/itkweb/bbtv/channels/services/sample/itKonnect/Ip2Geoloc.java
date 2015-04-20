package com.itkweb.bbtv.channels.services.sample.itKonnect;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.*;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by rmaneschi on 03/04/15.
 */
public class Ip2Geoloc {

    public Location get(String ip) throws IOException, GeoIp2Exception {
        // A File object pointing to your GeoIP2 or GeoLite2 database
        File database = new File("/files/itKonnect/GeoLite2-City.mmdb");

        // This creates the DatabaseReader object, which should be reused across
        // lookups.
        DatabaseReader reader = new DatabaseReader.Builder(database).build();

        InetAddress ipAddress = InetAddress.getByName(ip);

        // Replace "city" with the appropriate method for your database, e.g.,
        // "country".
        CityResponse response = reader.city(ipAddress);

        Country country = response.getCountry();
        System.out.println(country.getIsoCode());            // 'US'
        System.out.println(country.getName());               // 'United States'
        System.out.println(country.getNames().get("zh-CN")); // '美国'

        Subdivision subdivision = response.getMostSpecificSubdivision();
        System.out.println(subdivision.getName());    // 'Minnesota'
        System.out.println(subdivision.getIsoCode()); // 'MN'

        City city = response.getCity();
        System.out.println(city.getName()); // 'Minneapolis'

        Postal postal = response.getPostal();
        System.out.println(postal.getCode()); // '55455'

        Location location = response.getLocation();
        System.out.println(location.getLatitude());  // 44.9733
        System.out.println(location.getLongitude()); // -93.2323

        return location;
    }

}
