package com.xtrac.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.RandomStringUtils;

public class Helper {

	private static Pattern pattern;
	private static Matcher matcher;
	private static final String EMAIL_PATTERN =  "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/**
	 * Validate hex with regular expression
	 * 
	 * @param hex for validation
	 * @return true valid hex, false invalid hex
	 */
	public static boolean validate(final String email) {

		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}

	/**
	 * random number generation
	 * 
	 * @return
	 */
	public static int randomNumberGeneration() {

		int randomInt = 0;
		Random random = new Random();
		randomInt = random.nextInt(10000);
		return randomInt;
	}

	/**
	 * random alpha generation
	 * 
	 * @return
	 */
	public static String randomTextGeneration() {

		String generatedString = RandomStringUtils.randomAlphabetic(10);
		return generatedString.toString();
	}
}