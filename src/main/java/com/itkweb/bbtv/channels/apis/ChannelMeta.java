package com.itkweb.bbtv.channels.apis;

/**
 * Created by eric on 06/03/15.
 */
public class ChannelMeta {

    public String id;
    public String icon;
    public String name;
    public String url;

    public ChannelMeta() {
    }

    public ChannelMeta(String id, String icon, String name) {
        this.id = id;
        this.icon = icon;
        this.name = name;
        this.url = "channel/" + id;
    }
}
