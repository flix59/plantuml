package org.eclipse.acceleo.module.palladio.common;

public class ParsingUtil {
	public static String parseExternalCallActionName(String aName) {
		return aName.split("_")[1].split("I")[1];
	}
	public static String parseExternalCallActionInterface(String aName) {
		return aName.split("_")[1];
	}
}
