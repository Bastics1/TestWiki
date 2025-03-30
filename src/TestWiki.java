import io.appium.java_client.TouchAction;
import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;

public class TestWiki extends CoreTestCase {
    public MainPageObject MainPageObject;
    public void setUp() throws  Exception {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text,'Пропустить')]"), "Невозможно нажать на пропуск", 60);
    }

    @Test
    public void testWiki() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Хоббит, или Туда и обратно");
        SearchPageObject.waitForSearchResult("повесть Джона Р. Р. Толкина (1937)");
        SearchPageObject.initSave();
        SearchPageObject.typeListName("Хоббит");
        SearchPageObject.clickOkButton();
        driver.navigate().back();
        driver.navigate().back();
        SearchPageObject.clickBackButton();
        SearchPageObject.initSavedList();
        SearchPageObject.clickNotNowButton();
        SearchPageObject.longPressOnListByTitle("Хоббит", 3);
        SearchPageObject.clickDeleteListButton();
        SearchPageObject.clickOkButton();
    }
}


