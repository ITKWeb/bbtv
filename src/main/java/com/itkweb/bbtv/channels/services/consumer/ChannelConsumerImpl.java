package com.itkweb.bbtv.channels.services.consumer;

import com.itkweb.bbtv.channels.apis.Channel;
import com.itkweb.bbtv.channels.services.ChannelConsumer;
import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Requires;
import org.wisdom.api.DefaultController;
import org.wisdom.api.annotations.View;
import org.wisdom.api.http.Result;
import org.wisdom.api.templates.Template;

import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * Created by eric on 06/03/15.
 */
@Component
@Provides(specifications = ChannelConsumer.class)
@Instantiate
public class ChannelConsumerImpl extends DefaultController implements ChannelConsumer {

    private Random r = new Random();

    @View("mosaic")
    Template mosaic;

    @Requires(specification = Channel.class)
    List<Channel> channels;

    @Override
    public Channel getChannel() {
        return channels.get(r.nextInt(channels.size()));
     //   return "Yes";
        //return channels;
    }

    @Override
    public Result result() {
        return ok(render(mosaic, "mosaic", "Bonjour to Wisdom Framework!"));
    }


}
