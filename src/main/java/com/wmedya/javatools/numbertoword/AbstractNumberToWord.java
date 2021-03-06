package com.wmedya.javatools.numbertoword;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Base implementation of numbers to words independent of language. Language
 * specific implementations should be extended from this class
 * 
 * @author yawo
 *
 */
public abstract class AbstractNumberToWord implements INumberToWord {

	protected String[] digits = new String[10];
	protected String[] tens = new String[10];
	protected String[] hundreds = new String[10];
	protected String[] scales = new String[30];

	/**
	 * Parses a string number as integer according to its size and calculates
	 * division by the power of thousand. Should be carefully implemented and
	 * used proper type like int, long or BigInteger considering the size of the
	 * integer.
	 * 
	 * @param text
	 * @return division by the power of thousand
	 */
	protected Number calculateDivison(String text) {
		Double power = Math.pow(1000, (text.length() - 1) / 3d);
		if (power.toString().contains("E")) {
			BigDecimal bigDecimal = BigDecimal.valueOf(Math.pow(1000, (text.length() - 1) / 3d));
			return new BigInteger(text).divide(bigDecimal.toBigInteger());
		} else {
			return Long.valueOf(text) / power.longValue();
		}
	}

	/**
	 * Adds digit names by their order. Maximum allowed is 10. Otherwise an
	 * exception thrown
	 * 
	 * @param names
	 */
	protected void addDigits(String... names) {
		for (int i = 0; i < names.length; i++) {
			digits[i] = names[i];
		}
	}

	/**
	 * Adds ten names by their order. Maximum allowed is 10. Otherwise an
	 * exception thrown
	 * 
	 * @param names
	 */
	protected void addTens(String... names) {
		for (int i = 0; i < names.length; i++) {
			tens[i] = names[i];
		}
	}

	/**
	 * Adds hundred names by their order. Maximum allowed is 10. Otherwise an
	 * exception thrown
	 * 
	 * @param names
	 */
	protected void addHundreds(String... names) {
		for (int i = 0; i < names.length; i++) {
			hundreds[i] = names[i];
		}
	}

	/**
	 * Adds scale names like "ten","hundred","thousand","million","billion"...
	 * by their order. Maximum allowed is 30. Otherwise an exception thrown
	 * 
	 * @param names
	 */
	protected void addScales(String... names) {
		for (int i = 0; i < names.length; i++) {
			scales[i] = names[i];
		}
	}

}
