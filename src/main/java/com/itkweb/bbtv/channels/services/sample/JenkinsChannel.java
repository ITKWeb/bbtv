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
 * Created by rmaneschi on 06/03/15.
 */
@Component
@Provides(specifications = Channel.class)
@Instantiate
public class JenkinsChannel extends DefaultController implements Channel {

    private ChannelMeta meta = new ChannelMeta(JenkinsChannel.class.getName(), "/assets/images/jenkins.jpg", "Jenkins");

    @View("test1")
    Template test1;

    @Override
    public Result result() {
        return ok(render(test1));
    }

    @Override
    public ChannelMeta getMeta() {
        return meta;
    }

}
