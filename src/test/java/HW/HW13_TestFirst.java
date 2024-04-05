package HW;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.FirstBaseTest;


public class HW13_TestFirst extends FirstBaseTest {
//    Задача
//    Необходимо написать тесты для любого метода из подключенной библиотеки (можно использовать StringUtils) или можно
//    написать свой метод и протестировать его. Тесты должны запускаться через TestNG.

    @Test
    public void kmFirstTest() {
        Assert.assertTrue(StringUtils.isEmpty(null));
        Assert.assertTrue(StringUtils.isEmpty(""));
        Assert.assertFalse(StringUtils.isEmpty("null"));
    }
}