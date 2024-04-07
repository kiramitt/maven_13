package Lessons;

import org.testng.Assert;
import org.testng.annotations.Test;

import static school.redrover.Lesson13.getSum;

public class Lesson13Test {

    @Test
    public void testGetSum() {
        Assert.assertEquals(getSum(0,0),0);
        Assert.assertEquals(getSum(5,5), 10);
        Assert.assertEquals(getSum(1000_000_000,1000_000_000), 2000_000_000);
        Assert.assertEquals(getSum(-5,-5), -10);
    }

//    public void testGetSum() {
//        System.out.println(getSum(0,0) == 0);
//        System.out.println(getSum(5,5) == 10);
//        System.out.println(getSum(1000_000_000,1000_000_000) == 2000_000_000);
//        System.out.println(getSum(-5,-5) == -10);
//    }
//    public void testGetPad() {
//        System.out.println("AAA".equals(getPad("A",3)));
//        System.out.println("".equals(getPad("",3)));
//        System.out.println("".equals(getPad("C",0)));
//    }
}