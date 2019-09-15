package org.eclipse.acceleo.module.palladio.common;

import java.util.HashMap;
import java.util.Map;

public class LoopDetection {
	private static Map<String, Integer> startActionIds = new HashMap<String, Integer>();
	
	public static boolean notContained(String id) {
		Integer count = startActionIds.get(id);
		if(count == null) {
			startActionIds.put(id, 1);
			return true;
		}
		if(count < 10) {
			startActionIds.put(id, count +1);
			return true;
		} else {
			return false;
		}
	}
	
	public static void reset() {
		startActionIds.clear();
	}
}
