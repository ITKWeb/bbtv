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

import com.itkweb.bbtv.channels.services.Channel;
import com.itkweb.bbtv.channels.services.ChannelConsumer;
import org.apache.felix.ipojo.annotations.Requires;
import org.wisdom.api.DefaultController;
import org.wisdom.api.annotations.Controller;
import org.wisdom.api.annotations.Route;
import org.wisdom.api.annotations.View;
import org.wisdom.api.http.HttpMethod;
import org.wisdom.api.http.Result;
import org.wisdom.api.templates.Template;

/**
 * Your first Wisdom Controller.
 */
@Controller
public class WelcomeController extends DefaultController {

    /**
     * Injects a template named 'welcome'.
     */
    @View("welcome")
    Template welcome;

    @Requires
    ChannelConsumer channelConsumer;

    @Requires
    Channel channel;

    /**
     * The action method returning the welcome page. It handles
     * HTTP GET request on the "/" URL.
     *
     * @return the welcome page
     */
    @Route(method = HttpMethod.GET, uri = "/")
    public Result welcome() {
        return channelConsumer.getChannel().get();
        //return channel.get();
    }

    @Route(method = HttpMethod.GET, uri = "/mosaic")
    public Result mosaic() {
        return channel.mosaic();
        //return channelConsumer.getChannel().mosaic();
    }
}
