import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Objects;

public class TestBrowser extends BaseTest {

    private final By findInputSelector = By.xpath("//input[@aria-label='Запрос']");
    private final By findButtonSelector = By.xpath("//button[normalize-space()='Найти']");

    @Test
    public void testSearchAndVerifyResults() {
        //сайт yandex.ru редиректится на dzen, поэтому используем ya.ru
        driver.get("https://ya.ru");

        WebElement we = driver.findElement(findInputSelector);
        we.sendKeys("руддщ цкщдв");
        driver.findElement(findButtonSelector).click();
        //после поиска элемент изменился и его нужно искать заново
        we = driver.findElement(findInputSelector);

        Assert.assertEquals(we.getDomAttribute("value"), "hello world",
                "Поле ввода отличается от 'hello world'");
        Assert.assertTrue(Objects.requireNonNull(driver.getTitle()).contains("hello world"),
                "Заголовок страницы не содержит 'hello world'");
    }
}
