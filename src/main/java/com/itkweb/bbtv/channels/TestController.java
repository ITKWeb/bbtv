package com.itkweb.bbtv.channels;

import org.wisdom.api.DefaultController;
import org.wisdom.api.annotations.Controller;
import org.wisdom.api.annotations.Route;
import org.wisdom.api.annotations.View;
import org.wisdom.api.http.HttpMethod;
import org.wisdom.api.http.Result;
import org.wisdom.api.templates.Template;

/**
 * Created by rmaneschi on 06/03/15.
 */
@Controller
public class TestController extends DefaultController {

    @View("bbtv")
    Template bbtv;

    @View("test1")
    Template test1;

    @Route(method = HttpMethod.GET, uri = "/test")
    public Result welcome() {
        return ok(render(bbtv, "channelUrl", "/test/1"));
    }

    @Route(method = HttpMethod.GET, uri = "/test/{channelName}")
    public Result channel() {
        return ok(render(test1));
    }

}
