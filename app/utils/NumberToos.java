package utils;

import java.util.Random;

/**
 * Created by adinlead on 17-6-22.
 */
public class NumberToos {
    static Random random = new Random();

    public static int randomInt(int sta, int end) {
        return random.nextInt(end - sta) + sta;
    }
}
