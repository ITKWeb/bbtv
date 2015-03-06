package com.itkweb.bbtv.channels.services.consumer;

import com.itkweb.bbtv.channels.apis.BreakingNews;
import com.itkweb.bbtv.channels.apis.Channel;
import com.itkweb.bbtv.channels.apis.ChannelMeta;
import com.itkweb.bbtv.channels.services.ChannelConsumer;
import org.apache.felix.ipojo.annotations.Component;
import org.apache.felix.ipojo.annotations.Instantiate;
import org.apache.felix.ipojo.annotations.Provides;
import org.apache.felix.ipojo.annotations.Requires;
import org.wisdom.api.DefaultController;
import org.wisdom.api.annotations.View;
import org.wisdom.api.http.Result;
import org.wisdom.api.templates.Template;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by eric on 06/03/15.
 */
@Component
@Provides(specifications = BreakingNews.class)
@Instantiate
public class BreakingNewsConsumerImpl implements BreakingNews {

    private String news;

    @Override
    public String news() {
        return null;
    }

    @Override
    public void setNews(String news) {
        this.news = news;
    }
}
