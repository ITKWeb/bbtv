package com.itkweb.bbtv.channels.apis;

import org.wisdom.api.http.Result;

/**
 * Created by eric on 06/03/15.
 */
public interface Channel {

    public Result result();

    public Result mosaic();

    public ChannelMeta getMeta();

    public String id();

}
