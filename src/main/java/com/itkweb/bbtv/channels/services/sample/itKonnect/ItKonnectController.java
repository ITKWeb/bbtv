package com.itkweb.bbtv.channels.services.sample.itKonnect;

import com.fasterxml.jackson.databind.InjectableValues;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.*;
import org.apache.felix.ipojo.annotations.Requires;
import org.wisdom.api.DefaultController;
import org.wisdom.api.annotations.Controller;
import org.wisdom.api.annotations.Route;
import org.wisdom.api.http.HttpMethod;
import org.wisdom.api.http.Result;

import java.io.*;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by rmaneschi on 03/04/15.
 */
@Controller
public class ItKonnectController extends DefaultController {

    Ip2Geoloc ip2Geoloc = new Ip2Geoloc();

    private Pattern p = Pattern.compile("^((\\d{3}.){4})\\s*-\\s*-\\s*\\[([^\\]]*)\\]\\s*\\\"GET\\s*(\\/)\\s+");

    private class Point {
        public Double latitude;
        public Double longitude;
        public Integer amount;

        public Point(Double latitude, Double longitude, Integer amount) {
            this.latitude = latitude;
            this.longitude = longitude;
            this.amount = amount;
        }
    }

    @Route(method = HttpMethod.GET, uri = "/channel/itKonnect/data")
    public Result channelResult() throws IOException, GeoIp2Exception {
        List<Point> res = new ArrayList<Point>();

        InputStream stream = getClass().getResourceAsStream("/files/itKonnect/access_winfield");
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line;
        while ((line = reader.readLine()) != null) {
            Matcher m = p.matcher(line);
            if(m.lookingAt()) {
                Logger.getLogger("ItKonnectController").log(Level.INFO, m.group(1));
                //Location location = get(m.group(1));//ip
                res.add(new Point(41.845917, -87.628998, 100));
                m.group(3);//date
            }
        }
        reader.close();

        res.add(new Point(43.610769000000000000, 3.876715999999987600, 100));
        return ok(res);
    }

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
