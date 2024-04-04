package school.redrover;

import org.apache.commons.lang3.StringUtils;

public class Lesson13 {
    public static int getSum(int a, int b) {
        return a + b;
    }
    public static String getPad(String substr, int count) {
//        String result = "";
//        for (int i = 0; i < count; i++) {
//            result += substr;
//        }
//        return result;
        return StringUtils.repeat(substr, count);
    }
}