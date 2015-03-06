package com.itkweb.bbtv.channels.services.consumer;

import com.itkweb.bbtv.channels.services.Channel;
import com.itkweb.bbtv.channels.services.ChannelConsumer;
import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Requires;
import org.wisdom.api.http.Result;

import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * Created by eric on 06/03/15.
 */
@Component
@Provides(specifications = ChannelConsumer.class)
@Instantiate
public class ChannelConsumerImpl implements ChannelConsumer {

    private Random r = new Random();

    @Requires(specification = Channel.class)
    List<Channel> channels;

    @Override
    public Channel getChannel() {
        return channels.get(r.nextInt(channels.size()));
     //   return "Yes";
        //return channels;
    }
}
