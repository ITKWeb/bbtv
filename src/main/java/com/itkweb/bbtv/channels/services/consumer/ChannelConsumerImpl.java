package com.itkweb.bbtv.channels.services.consumer;

import com.itkweb.bbtv.channels.apis.Channel;
import com.itkweb.bbtv.channels.apis.ChannelMeta;
import com.itkweb.bbtv.channels.services.ChannelConsumer;
import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Requires;
import org.wisdom.api.DefaultController;
import org.wisdom.api.annotations.View;
import org.wisdom.api.http.Result;
import org.wisdom.api.templates.Template;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by eric on 06/03/15.
 */
@Component
@Provides(specifications = ChannelConsumer.class)
@Instantiate
public class ChannelConsumerImpl extends DefaultController implements ChannelConsumer {

    private int channelIndex = 0;

    @View("mosaic")
    Template mosaic;

    @Requires(specification = Channel.class)
    List<Channel> channels;

    @Override
    public Channel getChannel() {
        Channel channel = channels.get(channelIndex);
        Logger.getLogger("ChannelConsumerImpl").log(Level.INFO, "Channel : " + channel.getMeta().id);
        channelIndex++;
        if (channelIndex > channels.size()-1) channelIndex = 0;
        return channel;
    }

    @Override
    public Result result() {
        List<ChannelMeta> metas = new ArrayList<ChannelMeta>();
        for (Channel channel : channels) {
            metas.add(channel.getMeta());
        }
        return ok(render(mosaic, "metadatas", metas));
    }


}
