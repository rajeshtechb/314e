package com.rajesh.e.controller;

import com.rajesh.e.utils.FindKmostFrequentWords;
import com.rajesh.e.utils.WebPageDownload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
public class WorkSearchController {

    @Autowired
    private WebPageDownload webPageDownload;

    @GetMapping(path = "/findTopTenFrequentWords/url/{url}/hierarchy/{hierarchy}/numOfFrequentWords/{numOfFrequentWords}")
    public List<String> getTopTenFrequentWords(@PathVariable String url, @PathVariable int hierarchy, @PathVariable int numOfFrequentWords){
        return webPageDownload.findTopTenFrequentWords(URLDecoder.decode(url, StandardCharsets.UTF_8), hierarchy, numOfFrequentWords);
    }

    @GetMapping(path = "/findTopTenFrequentWordPairs/url/{url}/hierarchy/{hierarchy}/numOfFrequentWords/{numOfFrequentWords}")
    public List<String> getTopTenFrequentWordPairs(@PathVariable String url, @PathVariable int hierarchy, @PathVariable int numOfFrequentWords ){
        return webPageDownload.findTopTenFrequentWordPairs(URLDecoder.decode(url, StandardCharsets.UTF_8), hierarchy, numOfFrequentWords);
    }
}
