/**
 * @auther Rakesh
 * @time Aug 10, 2016
 */

package com.rkumbhare.app;

import com.rkumbhare.app.collections.HashMapImpl;

public class HashMapImplDemo {

	public static void main(String[] args) {
		HashMapImpl<String,String> mapImpl = new HashMapImpl<String, String>();
		mapImpl.put("A", "A");
		mapImpl.put("B", "B");
		mapImpl.put("C", "C");
		mapImpl.put("M", "M");
		mapImpl.put("N", "N");
		mapImpl.put("O", "O");
		mapImpl.put("P", "P");
		mapImpl.put("Q", "Q");
		mapImpl.put("R", "R");
		mapImpl.put("Z", "Z");
		System.out.println(mapImpl.size());
		
		// {P->A}, {R->C}
		
		System.out.println(mapImpl.get("A"));
		System.out.println(mapImpl.get("P"));
		System.out.println(mapImpl.get("R"));
		System.out.println(mapImpl.get("C"));
	}

}
