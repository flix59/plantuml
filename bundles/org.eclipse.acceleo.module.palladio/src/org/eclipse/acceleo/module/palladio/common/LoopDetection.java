package org.eclipse.acceleo.module.palladio.common;

import java.util.ArrayList;
import java.util.List;

public class LoopDetection {
	private static List<String> startActionIds = new ArrayList<String>();
	
	public static boolean notContained(String id) {
		return true;
		/*if(!startActionIds.contains(id)) {
			addAction(id);
			return true;
		} else {
			return false;
		}*/
	}
	public static void addAction(String id) {
		startActionIds.add(id);
	}
	
	public static void reset() {
		startActionIds.clear();
	}
}
