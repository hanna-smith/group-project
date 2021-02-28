package com.group.kudos.bingSearch;

import java.util.HashMap;

// Container class for search results encapsulates relevant headers and JSON data
public class SearchResults{
    HashMap<String, String> relevantHeaders;
    public String jsonResponse;
    SearchResults(HashMap<String, String> headers, String json) {
        relevantHeaders = headers;
        jsonResponse = json;
    }
}