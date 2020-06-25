package ravenReader;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Reader {
	public static String raven() {
		String words = null;
		try {
	    	@SuppressWarnings("resource")
			String word = new Scanner(new URL("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm").openStream(), "UTF-8").useDelimiter("\\A").next();
	        Map<String,Integer> map = new HashMap<String, Integer>(); 
	    	String delStr;
	    	String delStr2;
	 
	        delStr = word.substring(0, 1924);
	        delStr2 = word.substring(10899,30163);
	        word = word.replace(delStr, "");
	        word = word.replace(delStr2, "");
	        word = word.replaceAll("<[^>]*>", "");
	        word = word.replaceAll("&mdash", "");
	        word = word.replaceAll("\"", "");
	        word = word.replaceAll("'", "");
	        word = word.replaceAll(",", "");
	        word = word.replaceAll("\\.", "");
	        word = word.replaceAll("!", "");
	        word = word.replaceAll(";", " ");
	        word = word.replaceAll("\\s+", " ").trim();
	        word = word.toLowerCase();
	        
	        
	        String [] arr = word.split(" ");
	        	for (int i= 0; i<arr.length; i++) {
	        		if(map.containsKey(arr[i]) == false) 
		                map.put(arr[i],1);
		            else 
		            {
		                int count = (int)(map.get(arr[i])); 
		                map.remove(arr[i]); 
		                map.put(arr[i],count+1);
		            }	
	        } 
	        	  Set<Map.Entry<String, Integer>> set = map.entrySet();
	              List<Map.Entry<String, Integer>> sortedList = new ArrayList<Map.Entry<String, Integer>>(set);  
	              Collections.sort( sortedList, new Comparator<Map.Entry<String, Integer>>() 
	              {
	                  public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b ) 
	                  {
	                      return (b.getValue()).compareTo( a.getValue() );            
	                  }
	              } );
	              for(Map.Entry<String, Integer> i:sortedList){
	                  words = words + ("\n"+i.getKey()+" --> "+i.getValue());  
	        }
		}
	catch(Exception FileNotFound){
		System.out.println("URL not found");	
	}
		return words;
	}
	public char getFirstChar() {
		return Reader.raven().charAt(0);
	}
}
