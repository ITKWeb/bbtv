package com.itkweb.bbtv.channels.apis;

/**
 * Created by eric on 06/03/15.
 */
public class ChannelMeta {

    public String icon;
    public String name;

    public ChannelMeta() {
    }

    public ChannelMeta(String icon, String name) {
        this.icon = icon;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }

}
