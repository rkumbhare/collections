/**
 * @auther Rakesh
 * @time Aug 11, 2016
 */

package com.rkumbhare.app;

import com.rkumbhare.app.collections.HashMapImpl;

public class HashMapDemo { 

	public static void main(String[] args) {
		HashMapImpl<String, String> map = new HashMapImpl<String, String>();
		map.put("A", "A");
		map.put("AB", "AB");
		map.put("BA", "BA");
		map.put("P", "P");
		map.put("Q", "Q");
		map.put("PQ", "PQ");
		map.put("QP", "QP");
		map.put(null, "RAKESH");
		
		System.out.println(map.size());
		
		System.out.println(map.get("P"));
		System.out.println(map.get("Q"));
		System.out.println(map.get("PQ"));
		System.out.println(map.get("QP"));
		System.out.println(map.get("BA"));
		System.out.println(map.get("AB"));
		System.out.println(map.get("A"));
		System.out.println(map.get(null));
		System.out.println("RAKESH KUMBHARE".hashCode());
		System.out.println(map.put(null, "RAKESH KUMBHARE"));
		
		System.out.println(map.remove(null));
		System.out.println(map.remove("Q"));
		System.out.println(map.remove("BA"));
		System.out.println(map.remove("P"));
		System.out.println(map.remove("A"));
		System.out.println(map.remove("PQ"));
		
		System.out.println(map.size());
		
	}

}
