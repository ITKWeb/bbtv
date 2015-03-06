package com.itkweb.bbtv.channels.services.sample.jug;

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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rmaneschi on 06/03/15.
 */
@Component
@Provides(specifications = Channel.class)
@Instantiate
public class JugChannel extends DefaultController implements Channel {

    private ChannelMeta meta = new ChannelMeta(JugChannel.class.getName(), "/assets/images/logo-jug.png", "JUG");
    private List<JugEvent> events = new ArrayList<JugEvent>();

    @Requires
    Json json;

    @View("channels/jug-channel")
    Template jugChannel;

    public JugChannel() {
        try {
            URLConnection connection = new URL("http://jug-montpellier.org/api/events.json").openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder sb = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                sb.append(inputLine);
            }
            in.close();
            JsonNode jsonEvents = json.parse(sb.toString());
            events = new ArrayList<JugEvent>();
            JugEvent event;
            for(JsonNode jsonEvent : jsonEvents) {
                event = new JugEvent();
                event.title = jsonEvent.get("title").textValue();
                event.date = jsonEvent.get("date").textValue();
                event.description = jsonEvent.get("description").textValue();
                events.add(event);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Result result() {
        return ok(render(jugChannel, "events", events));
    }

    @Override
    public ChannelMeta getMeta() {
        return meta;
    }

}
