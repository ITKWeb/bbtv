package com.itkweb.bbtv.channels.services.sample;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.itkweb.bbtv.channels.apis.Channel;
import com.itkweb.bbtv.channels.apis.ChannelMeta;
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

/**
 * Created by rmaneschi on 06/03/15.
 */
@Component
@Provides(specifications = Channel.class)
@Instantiate
public class JugChannel extends DefaultController implements Channel {

    private ChannelMeta meta = new ChannelMeta(JugChannel.class.getName(), "/assets/images/logo-jug.png", "JUG");

    @Requires
    Json json;

    @View("jug-channel")
    Template jugChannel;

    @Override
    public Result result() {
        URL yahoo = null;
        try {
            yahoo = new URL("http://jug-montpellier.org/api/events.json");
            URLConnection yc = yahoo.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;
            StringBuilder sb = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                sb.append(inputLine);
            }
            in.close();
            JsonNode node = json.parse(sb.toString());

            return ok(node);//render(jugChannel));
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
