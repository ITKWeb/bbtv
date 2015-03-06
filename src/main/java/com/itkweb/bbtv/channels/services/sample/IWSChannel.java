package com.itkweb.bbtv.channels.services.sample;

import com.itkweb.bbtv.channels.apis.Channel;
import com.itkweb.bbtv.channels.apis.ChannelMeta;
import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.wisdom.api.DefaultController;
import org.wisdom.api.annotations.View;
import org.wisdom.api.http.Result;
import org.wisdom.api.templates.Template;

/**
 * Created by eric on 06/03/15.
 */
@Component
@Provides(specifications = Channel.class)
@Instantiate
public class IWSChannel extends DefaultController implements Channel {

    private ChannelMeta meta = new ChannelMeta("/assets/images/weather.png","Weather");

    @View("welcome")
    Template iwsTemplate;

    public IWSChannel() {
        System.out.println("YES----------------------");
    }

    @Override
    public Result result() {
        return ok(render(iwsTemplate, "iwschannel", "iws!!!!"));
    }

    @Override
    public Result mosaic() {
        return ok(render(iwsTemplate, "welcome", "Welcome to Wisdom Framework!"));
    }

    @Override
    public ChannelMeta getMeta() {
        return meta;
    }

    @Override
    public String id() {
        return IWSChannel.class.getName();
    }
}
