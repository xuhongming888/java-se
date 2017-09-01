package map;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * HashMap实现原理学习
 *
 */
public class MyHashMap {
	
/*	public static void main(String[] args) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("语文", 1);
		map.put("数学", 2);
		map.put("英语", 3);
		map.put("历史", 4);
		map.put("政治", 5);
		map.put("地理", 6);
		map.put("生物", 7);
		map.put("化学", 8);
		for(Entry<String, Integer> entry : map.entrySet()) {
		    System.out.println(entry.getKey() + ": " + entry.getValue());
		}
		
	}*/
	

	public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<String, Integer>(3, 0.75f);
        map.put(null, 0);
        map.put("java", 1);
        map.put("c++", 2);
        map.put("python", 3);
        map.put("php", 4);
        map.put("nodejs", 5);
        map.put(null, 6);
        for(Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("php".hashCode() == "c++".hashCode());
    }

	
	
}
