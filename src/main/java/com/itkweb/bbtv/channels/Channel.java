package com.itkweb.bbtv.channels;

import org.wisdom.api.DefaultController;
import org.wisdom.api.annotations.Service;
import org.wisdom.api.http.Result;

/**
 * Created by eric on 06/03/15.
 */
public interface Channel {

    public Result get();

    public Result mosaic();

}
