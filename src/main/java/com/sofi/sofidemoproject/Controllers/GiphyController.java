package com.sofi.sofidemoproject.Controllers;

import com.sofi.sofidemoproject.Models.Data;
import com.sofi.sofidemoproject.Services.GiphyService;
import com.sofi.sofidemoproject.SofiDemoConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

// RESTFul Controller
@RestController
// Mapping to search controller
@RequestMapping(path="/search")
public class GiphyController {

    //Create a services
    private GiphyService giphyService;

    // Load the config
    private final SofiDemoConfigProperties sofiConfig;

    @Autowired
    public GiphyController(GiphyService giphyService, SofiDemoConfigProperties sofiConfig) {
        this.giphyService = giphyService;
        this.sofiConfig = sofiConfig;
    }

    @RequestMapping("/")
    public String index(){
        return "Welcome to GiphyController";
    }

    // Mapping the search key word
    @RequestMapping(value="/{keyWord}",method= RequestMethod.GET)
    public Data searchGiphyByKeyword(@PathVariable String keyWord) throws IOException {
        return giphyService.getSearchResponse(keyWord, sofiConfig.maxReturn());
    }
}
