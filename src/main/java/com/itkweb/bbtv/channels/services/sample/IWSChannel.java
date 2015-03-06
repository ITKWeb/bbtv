package com.itkweb.bbtv.channels.services.sample;

import com.fasterxml.jackson.databind.JsonNode;
import com.itkweb.bbtv.channels.apis.Channel;
import com.itkweb.bbtv.channels.apis.ChannelMeta;
import com.itkweb.bbtv.channels.services.sample.jug.JugEvent;
import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Requires;
import org.wisdom.api.DefaultController;
import org.wisdom.api.annotations.View;
import org.wisdom.api.content.Json;
import org.wisdom.api.http.Result;
import org.wisdom.api.templates.Template;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eric on 06/03/15.
 */
@Component
@Provides(specifications = Channel.class)
@Instantiate
public class IWSChannel extends DefaultController implements Channel {

    private ChannelMeta meta = new ChannelMeta(IWSChannel.class.getName(), "/assets/images/iws.jpg","IWS");

    @Requires
    Json json;

    @View("iwschannel")
    Template iwsTemplate;

    public IWSChannel() {
    }

    @Override
    public Result result() {

        URL davisITK = null;
        try {
            davisITK = new URL("http://api.weatherlink.com/v1/NoaaExt.json?user=itkstation&pass=DavisITK&apiToken=8ff089b49dc442f7b28b45505fbfb452");
            URLConnection davisITKConnection = davisITK.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(davisITKConnection.getInputStream()));
            String inputLine;
            StringBuilder sb = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                sb.append(inputLine);
            }
            in.close();
            JsonNode jsonData = json.parse(sb.toString());

            IWSData iwsData = new IWSData();
            iwsData.observationTime = jsonData.get("observation_time").textValue();
            iwsData.latitude = jsonData.get("latitude").textValue();
            iwsData.longitude= jsonData.get("longitude").textValue();
            iwsData.stationId= jsonData.get("station_id").textValue();
            iwsData.temperatureCelsius = jsonData.get("temp_c").textValue();
            iwsData.temperatureFahrenheit = jsonData.get("temp_f").textValue();


            return ok(render(iwsTemplate, "iwsData", iwsData));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return badRequest("Error");
    }

    @Override
    public ChannelMeta getMeta() {
        return meta;
    }
}
