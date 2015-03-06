package com.itkweb.bbtv.channels.services.sample;

import com.itkweb.bbtv.channels.services.Channel;
import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.wisdom.api.DefaultController;
import org.wisdom.api.annotations.View;
import org.wisdom.api.http.Result;
import org.wisdom.api.templates.Template;

/**
 * Created by eric on 06/03/15.
 */
@Component
@Provides(specifications = Channel.class)
@Instantiate
public class SampleChannelService extends DefaultController implements Channel {

    @View("welcome")
    Template welcome;

    public SampleChannelService() {
        System.out.println("YES----------------------");
    }

    @Override
    public Result get() {
        return ok(render(welcome, "welcome", "Welcome to ITK!"));
    }

    @Override
    public Result mosaic() {
        return ok(render(welcome, "welcome", "Welcome to Wisdom Framework!"));
    }
}
