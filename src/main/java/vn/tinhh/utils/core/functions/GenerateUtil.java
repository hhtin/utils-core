package vn.tinhh.utils.core.functions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateUtil {

    private static char[] charsLow = "zxcvbnmasdfghjklqwertyuiop".toCharArray();
    private static char[] charsUp = "ZXCVBNMASDFGHJKLQWERTYUIOP".toCharArray();
    private static char[] charsNum = "0123456789".toCharArray();
    private static char[] charsSpec = "!@#$%*-_+=".toCharArray();

    public static final int RANDOM_MAIL_COUNT = 12;

    public static int randomInt(int min, int max) {
        Random rn = new Random();
        return rn.nextInt(max - min + 1) + min;
    }

    public static String randomNumber(int countChar) {
        Random rand = new Random();
        String strOTP = "";
        for (int i = 1; i <= countChar; i++) {
            int value = rand.nextInt(9);
            strOTP += String.valueOf(value);
        }
        return strOTP;
    }


    public static String randomString(int countChar) {
        Random randomGenerator = new Random();
        List<Integer> list = new ArrayList<>();
        do {
            int randomInt = randomGenerator.nextInt(5);
            boolean flag = true;
            if (list.size() < 5) {
                for (Integer i : list) {
                    if (i == randomInt) {
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) {
                list.add(randomInt);
            }
        } while (list.size() < countChar);

        char c;
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (Integer i : list) {
            switch (i) {
                case 0:
                    c = charsLow[random.nextInt(charsLow.length)];
                    sb.append(c);
                    break;
                case 1:
                    c = charsUp[random.nextInt(charsUp.length)];
                    sb.append(c);
                    break;
                case 2:
                    c = charsNum[random.nextInt(charsNum.length)];
                    sb.append(c);
                    break;
//                case 3:
//                    c = charsSpec[random.nextInt(charsSpec.length)];
//                    sb.append(c);
//                    break;
                default:
                    c = charsNum[random.nextInt(charsNum.length)];
                    sb.append(c);
                    break;
            }
        }

        return sb.toString();
    }
}
