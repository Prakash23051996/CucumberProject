package CommoMethods;

import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;

import Basemethods.BaseClass;

public class CommonMethods extends BaseClass {
	
	public String randomNumber(int number) {
		return RandomStringUtils.randomNumeric(number);
	}

	public String randomNumberic(int number) {
		return RandomStringUtils.randomAlphanumeric(10);
	}
	

	public String randomString(int number) {
		return RandomStringUtils.randomAlphabetic(number);
	}

//	public static int getRandomNumber() throws Exception {
//		try {
//			String number = getDataFromPropertyFile("RandomNumber");
//			Random random = new Random();
//			return random.nextInt(Integer.valueOf(number));
//		} catch (Exception e) {
//			throw new Exception("Error Message:" + e.getMessage());
//		}
//	}

	public static int getRandomNumRange(int minValue, int maxValue) throws Exception {
		try {
			Random random = new Random();
			int minimum = minValue;
			int maximum = maxValue;
			return random.nextInt(maximum - minimum + 1) + minimum;
		} catch (Exception e) {
			throw new Exception("Error Message:" + e.getMessage());
		}
	}

//	public static String getRandomString() throws Exception {
//		try {
//			String characters = getDataFromPropertyFile("char");
//			Random random = new Random();
//			StringBuilder stringBuilder = new StringBuilder();
//			for (int i = 0; i < 3; i++) {
//				stringBuilder.append(characters.charAt(random.nextInt(characters.length())));
//			}
//			return stringBuilder.toString();
//		} catch (Exception e) {
//			throw new Exception("Error Message: " + e.getMessage());
//		}
//	}

}
