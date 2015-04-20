package com.itkweb.bbtv.channels.services.sample.itKonnect;

/**
 * Created by rmaneschi on 03/04/15.
 */

import com.itkweb.bbtv.channels.apis.Channel;
import com.itkweb.bbtv.channels.apis.ChannelMeta;
import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.wisdom.api.DefaultController;
import org.wisdom.api.annotations.View;
import org.wisdom.api.http.Result;
import org.wisdom.api.templates.Template;

@Component
@Provides(specifications = Channel.class)
@Instantiate
public class ItKonnect extends DefaultController implements Channel {

    private ChannelMeta meta = new ChannelMeta(ItKonnect.class.getName(), "/assets/images/logo-jug.png", "itKonnect");

    @View("channels/itKonnect-channel")
    Template channel;

    @Override
    public Result result() {
        return ok(render(channel));
    }

    @Override
    public ChannelMeta getMeta() {
        return meta;
    }

}
