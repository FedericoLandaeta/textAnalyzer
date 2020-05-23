package textAnalyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main{
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("C:\\Users\\Vanessa\\Documents\\eclipse\\TheRaven.txt");
        Scanner scanner = new Scanner(file);
        Map<String,Integer> map = new HashMap<String, Integer>(); 
        while (scanner.hasNext())
        {
            String word = scanner.next(); 
            if(map.containsKey(word) == false) 
                map.put(word,1);
            else 
            {
                int count = (int)(map.get(word)); 
                map.remove(word); 
                map.put(word,count+1);
            }
        }
        Set<Map.Entry<String, Integer>> set = map.entrySet();
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<Map.Entry<String, Integer>>(set);  
        Collections.sort( sortedList, new Comparator<Map.Entry<String, Integer>>() 
        {
            public int compare( Map.Entry<String, Integer> a, Map.Entry<String, Integer> b ) 
            {
                return (b.getValue()).compareTo( a.getValue() );            
            }
        } );
        for(Map.Entry<String, Integer> i:sortedList){
            System.out.println(i.getKey()+" --> "+i.getValue());
        }
    }
}