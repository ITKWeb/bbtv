package com.itkweb.bbtv.channels;

import com.itkweb.bbtv.channels.Channel;
import com.itkweb.bbtv.channels.sample.SampleChannel;
import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Requires;

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
    Collection<Channel> channels;

    @Override
    public String getChannel() {
     //   return channels.get(r.nextInt(channels.size()));
        return "Yes";
        //return channels;
    }
}
