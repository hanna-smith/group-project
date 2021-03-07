package com.group.bingSearch;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

/*
 * Gson: https://github.com/google/gson
 * Maven info:
 *     groupId: com.google.code.gson
 *     artifactId: gson
 *     version: 2.8.1
 *
 * Once you have compiled or downloaded gson-2.8.1.jar, assuming you have placed it in the
 * same folder as this file (localSearch.java), you can compile and run this program at
 * the command line as follows.
 *
 * javac localSearch.java -classpath .;gson-2.8.1.jar -encoding UTF-8
 * java -cp .;gson-2.8.1.jar localSearch
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class BingPlaceSearch {

    // ***********************************************
    // *** Update or verify the following values. ***
    // **********************************************

        // Replace the subscriptionKey string value with your valid subscription key.
        static String subscriptionKey = "336dc5991956458cb05bbba75c4fe7c1";

        static String host = "https://api.bing.microsoft.com";
        static String path = "/v7.0/entities";

        public static SearchResults SearchLocal (String searchQuery) throws Exception {
            // construct URL of search request (endpoint + query string)
            URL url = new URL(host + path + "?q=" +  URLEncoder.encode(searchQuery, "UTF-8") + "&mkt=en-us&responseFilter=Places");
            HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
            connection.setRequestProperty("Ocp-Apim-Subscription-Key", subscriptionKey);

            // receive JSON body
            InputStream stream = connection.getInputStream();
            String response = new Scanner(stream).useDelimiter("\\A").next();

            // construct result object for return
            SearchResults results = new SearchResults(new HashMap<String, String>(), response);

            // extract Bing-related HTTP headers
            Map<String, List<String>> headers = connection.getHeaderFields();
            for (String header : headers.keySet()) {
                if (header == null) continue;      // may have null key
                if (header.startsWith("BingAPIs-") || header.startsWith("X-MSEdge-")) {
                    results.relevantHeaders.put(header, headers.get(header).get(0));
                }
            }

            stream.close();
            return results;
        }

        // pretty-printer for JSON; uses GSON parser to parse and re-serialize
        public static String prettify(String json_text) {
            JsonParser parser = new JsonParser();
            JsonObject json = parser.parse(json_text).getAsJsonObject();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.toJson(json);
        }
        
        public BingPlaceSearch() {}

//        public static void main (String[] args) {
//            try {
//            	String searchTerm = "Restaurant";
//            	String searchLocation = "Bellevue";
//                System.out.println("Searching the Web for: " + searchTerm);
//
//                SearchResults result = search(searchTerm, searchLocation);
//                
//                JsonParser parser = new JsonParser();
//                JsonObject json = parser.parse(result.jsonResponse).getAsJsonObject();
//                JsonObject placeJson = json.getAsJsonObject("places");
//                JsonArray places = placeJson.getAsJsonArray("value");
//               
//                for (int i = 0; i < places.size(); i++) {
//                	JsonObject place = (JsonObject)places.get(i);
//                	String name = place.get("name").getAsString();
//                	System.out.println(name);
//                	System.out.println(prettify(place.toString()));
//                }
//
////                System.out.println("\nRelevant HTTP Headers:\n");
////                for (String header : result.relevantHeaders.keySet())
////                    System.out.println(header + ": " + result.relevantHeaders.get(header));
////
////                System.out.println("\nJSON Response:\n");
////                System.out.println(prettify(result.jsonResponse));
//            }
//            catch (Exception e) {
//                e.printStackTrace(System.out);
//                System.exit(1);
//            }
//        }
        
        public SearchResults search(String searchTerm, String searchLocation) {
        	String fullSearch = searchTerm + " in " + searchLocation;
        	try {
        		return SearchLocal(fullSearch);
        	} catch(Exception e) {
        		System.out.println(e);
        		return null;
        	}
        }
    }