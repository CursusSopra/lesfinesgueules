/**
 * File modified by : Julien Caillon
 */
package fr.cursusSopra.tech;

/**
 * @author Julien Caillon
 */
public class FormTools {

	/*
	 * Return true if the string is not empty
	 */
	public static boolean isStrNotEmpty(String str) {
		return str != null && !str.isEmpty();
	}

	/*
	 * Return true if zip code is valid
	 */
	public static boolean isZipValid(String str) {
		return str != null && str.matches("^\\d{5}$");
	}
}
