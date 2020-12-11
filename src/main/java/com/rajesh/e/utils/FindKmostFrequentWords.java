package com.rajesh.e.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FindKmostFrequentWords {

    public List<String> topKFrequent(List<String> words , int k) {
        Map<String,Integer> map = new HashMap<>();
        for(String word : words){
           map.put(word, map.getOrDefault(word, 0)+1);
        }
        PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>(){
            @Override
            public int compare(String word1 , String word2){
                int frequency1= map.get(word1);
                int frequency2 = map.get(word2);
                if(frequency1 == frequency2) return word2.compareTo(word1);
                return frequency1- frequency2;
            }
        });
        for(Map.Entry<String,Integer> entry : map.entrySet()){
            pq.add(entry.getKey());
            if(pq.size()>k) pq.poll();

        }
        List<String> result = new ArrayList<>();
        while(!pq.isEmpty()) result.add(pq.poll());
        Collections.reverse(result);
        return result;
    }

    public List<String> topTwoWordsWithSameOrderKFrequent(List<String> words, int k) {
        Map<String,Integer> map = new HashMap<>();
        for(int i=0;i<words.size()-1;i++){
            map.put(words.get(i)+" "+words.get(i+1),map.getOrDefault(words.get(i)+" "+words.get(i+1), 0)+1);
        }

        PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>(){
            @Override
            public int compare(String word1 , String word2){
                int frequency1= map.get(word1);
                int frequency2 = map.get(word2);
                if(frequency1 == frequency2) return word2.compareTo(word1);
                return frequency1- frequency2;
            }
        });
        for(Map.Entry<String,Integer> entry : map.entrySet()){
            pq.add(entry.getKey());
            if(pq.size()>k) pq.poll();

        }
        List<String> result = new ArrayList<>();
        while(!pq.isEmpty()) result.add(pq.poll());
        Collections.reverse(result);
        return result;
    }
}
