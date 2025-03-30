package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.internal.TouchAction;

import java.time.Duration;
import java.util.Collections;

public class SearchPageObject extends MainPageObject{
    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static final String
    SEARCH_INIT_ELEMENT = "//*[contains(@text,'Поиск по Википедии')]",
    SEARCH_INPUT = "//*[contains(@text,'Поиск')]",
    SEARCH_RESULT = "//*[@class='android.view.ViewGroup']//*[@text='{SUBSTRING}']",
    SEARCH_SAVE_BUTTON = "//*[contains(@text,'Сохранить')]",
    SEARCH_ADD_BUTTON = "//*[contains(@text,'Добавить в список')]",
    LIST_NAME_INPUT = "//*[contains(@text,'Название этого списка')]",
    OK_BUTTON = "//*[@text='ОК']",
    BACK_BUTTON = "//*[@content-desc='Перейти вверх']",
    SAVED_LIST = "//*[@text='Сохранённые']",
    LIST_BY_TITLE = "//*[@text='{TITLE}']",
    NOT_NOW_BUTTON = "//*[@text='Не сейчас']",
    DELETE_LIST_BUTTON = "//*[@text='Удалить список']";

    public void initSearchInput() {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT),
                "Невозможно нажать на поле ввода", 60);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT),
                "Невозможно нажать на поле ввода", 60);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line,
                "Невозможно нажать на поле ввода", 60);

    }

    private static String getResultSearchElement(String  substring) {
        return SEARCH_RESULT.replace("{SUBSTRING}", substring);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath),
                "Невозможно найти" + substring, 60);
    }

    public void initSave() {
        this.waitForElementAndClick(By.xpath(SEARCH_SAVE_BUTTON), "Невозможно нажать на поле сохранить", 60);
        this.waitForElementAndClick(By.xpath(SEARCH_ADD_BUTTON), "Невозможно нажать на поле добавить", 60);
    }

    public void typeListName(String listName) {
        this.waitForElementAndSendKeys(By.xpath(LIST_NAME_INPUT), listName, "Невозможно ввести название списка", 60);
    }
    public void clickOkButton() {
        this.waitForElementAndClick(By.xpath(OK_BUTTON), "Невозможно нажать кнопку ОК", 60);
    }

    public void initSavedList() {
        this.waitForElementAndClick(By.xpath(SAVED_LIST), "Невозможно перейти на вкладку Сохранённые", 60);
    }

    public  void clickBackButton() {
        this.waitForElementAndClick(By.xpath(BACK_BUTTON), "Невозможно нажать кнопку назад", 60);
    }

    public void longPressOnListByTitle(String itemTitle, int durationInSeconds) {
        String itemXpath = LIST_BY_TITLE.replace("{TITLE}", itemTitle);

        WebElement element = waitForElementPresent(
                By.xpath(itemXpath),
                "Не удалось найти элемент '" + itemTitle + "' для долгого нажатия",
                10
        );

        // Координаты центра элемента
        int centerX = element.getLocation().getX() + element.getSize().getWidth() / 2;
        int centerY = element.getLocation().getY() + element.getSize().getHeight() / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence sequence = new Sequence(finger, 0)
                .addAction(finger.createPointerMove(Duration.ZERO,
                        PointerInput.Origin.viewport(), centerX, centerY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger, Duration.ofSeconds(durationInSeconds)))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));
    }

    public  void clickNotNowButton() {
        this.waitForElementAndClick(By.xpath(NOT_NOW_BUTTON), "Невозможно нажать кнопку Не сейчас", 60);
    }
    public  void clickDeleteListButton() {
        this.waitForElementAndClick(By.xpath(DELETE_LIST_BUTTON), "Невозможно нажать кнопку Не сейчас", 60);
    }
}

