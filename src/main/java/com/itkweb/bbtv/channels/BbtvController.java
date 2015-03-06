/*
 * #%L
 * Wisdom-Framework
 * %%
 * Copyright (C) 2013 - 2014 Wisdom Framework
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.itkweb.bbtv.channels;

import com.itkweb.bbtv.channels.apis.BreakingNews;
import com.itkweb.bbtv.channels.apis.Channel;
import com.itkweb.bbtv.channels.services.ChannelConsumer;
import org.apache.felix.ipojo.annotations.Requires;
import org.wisdom.api.DefaultController;
import org.wisdom.api.annotations.Controller;
import org.wisdom.api.annotations.Parameter;
import org.wisdom.api.annotations.Route;
import org.wisdom.api.annotations.View;
import org.wisdom.api.http.HttpMethod;
import org.wisdom.api.http.Result;
import org.wisdom.api.templates.Template;

import java.util.List;

/**
 * Your first Wisdom Controller.
 */
@Controller
public class BbtvController extends DefaultController {

    @View("bbtv")
    Template bbtv;

    @View("only-one-channel")
    Template onlyOneChannel;

    @Requires
    ChannelConsumer channelConsumer;

    @Requires(specification = Channel.class)
    List<Channel> channels;

    @Requires
    BreakingNews breakingNews;

    /**
     * The action method returning the welcome page. It handles
     * HTTP GET request on the "/" URL.
     *
     * @return the welcome page
     */
    @Route(method = HttpMethod.GET, uri = "/")
    public Result welcome() {
        return ok(render(bbtv));
    }

    @Route(method = HttpMethod.GET, uri = "/randomChannel")
    public Result randomChannel() {
        return channelConsumer.getChannel().result();
    }

    @Route(method = HttpMethod.GET, uri = "/channel/{id}")
    public Result channel(@Parameter("id") String id) {
        for(Channel channel : channels) {
            if(channel.getMeta().id.equals(id)) {
                return ok(render(onlyOneChannel, "urlChannel", "/channelResult/" + channel.getMeta().id));
            }
        }
        return badRequest("Channel " + id + " not found!");
    }

    @Route(method = HttpMethod.GET, uri = "/channelResult/{id}")
    public Result channelResult(@Parameter("id") String id) {
        for(Channel channel : channels) {
            if(channel.getMeta().id.equals(id)) {
                return channel.result();
            }
        }
        return badRequest("Channel " + id + " not found!");
    }

    @Route(method = HttpMethod.GET, uri = "/mosaic")
    public Result mosaic() {
        return channelConsumer.result();
    }

    @Route(method = HttpMethod.GET, uri = "/bn")
    public Result breakingnews() { return ok("Hello this is a very long which should be longer than the width of the marquee"); }
}
