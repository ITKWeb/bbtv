package com.itkweb.bbtv.channels.services;

import com.itkweb.bbtv.channels.apis.Channel;
import org.wisdom.api.http.Result;

/**
 * Created by eric on 06/03/15.
 */
public interface ChannelConsumer {

    Channel getChannel();

    public Result result();
}
