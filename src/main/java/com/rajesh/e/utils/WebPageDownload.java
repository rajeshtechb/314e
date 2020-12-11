package com.rajesh.e.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class WebPageDownload {

    @Autowired
    FindKmostFrequentWords findKmostFrequentWords;

    public List<String> findTopTenFrequentWords(String url, int hierarchy, int numOfFrequentWords) {
        List<String> allLinks = new ArrayList<>();
        List<String> words = new ArrayList<>();
        allLinks.add(url);
        extractDataWithJsoup(url, allLinks, 0, words, hierarchy);
        List<String> list = findKmostFrequentWords.topKFrequent(words, numOfFrequentWords);
        return list;
    }

    public List<String> findTopTenFrequentWordPairs(String url, int hierarchy, int numOfFrequentWords) {
        List<String> allLinks = new ArrayList<>();
        List<String> words = new ArrayList<>();
        allLinks.add(url);
        extractDataWithJsoup(url, allLinks, 0, words, hierarchy);
        List<String> list = findKmostFrequentWords.topTwoWordsWithSameOrderKFrequent(words, numOfFrequentWords);
        return list;
    }

    public void extractDataWithJsoup(String href, List<String> allLinks, int level, List<String> words, int hierarchy) {
        Document doc = extractDocument(href);
        if (doc != null) {
            String text = doc.body().text();
            for (String word : text.split(" ")) {
                String nWord = word.trim();
                if (!isStringCharacters(nWord))
                    words.add(nWord);
            }
            Elements links = doc.select("a[href]");
            for (Element link : links) {
                String linkHref = link.attr("href");
                if (linkHref.startsWith("https")) {
                    if (!allLinks.contains(linkHref)) {
                        allLinks.add(linkHref);
                        if (level <= hierarchy) {
                            extractDataWithJsoup(linkHref, allLinks, level + 1, words, hierarchy);
                        }
                    }
                }
            }
        }
    }

    private Document extractDocument(String href) {
        Document doc = null;
        try {
            doc = Jsoup.connect(href).timeout(10 * 1000).userAgent
                    ("Mozilla").ignoreHttpErrors(true).get();
        } catch (IOException e) {
            //Your exception handling here
        }
        return doc;
    }

    private boolean isStringCharacters(String str) {
        Pattern pattern = Pattern.compile("[^A-Za-z]");
        Matcher match = pattern.matcher(str);
        return match.find();
    }

}
