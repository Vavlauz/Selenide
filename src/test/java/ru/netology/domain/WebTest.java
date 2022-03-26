package ru.netology.domain;

import com.codeborne.selenide.ClickMethod;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class WebTest {

    @BeforeEach
    public void setUp() {
        LocalDate.of(2022, 03, 26);
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");

    }

    @Test
    void shouldSendForm() {
        $$x("//input[@type='text']").filter(visible).first().val("Новосибирск").pressTab();
        $$x("//input[@type='tel']").exclude(hidden).first().sendKeys(Keys.DELETE, "31.03.2022");
        $$x("//input[@type='text']").filter(visible).last().val("Ужакин Вадим");
        $$x("//input[@type='tel']").exclude(hidden).last().val("+79993336666");
        $("[data-test-id=agreement]").click();
        $$(withText("Забронировать")).get(0).click();
        $x("//*[contains(text(),'Встреча успешно')]").should(appear, Duration.ofSeconds(15));

    }

    @Test
    void fieldTownEmpty() {
        $$x("//input[@type='text']").filter(visible).first().val("").pressTab();
        $$x("//input[@type='tel']").exclude(hidden).first().sendKeys(Keys.DELETE, "30.03.2022");
        $$x("//input[@type='text']").filter(visible).last().val("Ужакин Вадим");
        $$x("//input[@type='tel']").exclude(hidden).last().val("+79993336666");
        $("[data-test-id=agreement]").click();
        $$(withText("Забронировать")).get(0).click();
        $x("//*[contains(text(),'Поле обязательно')]").should(appear, Duration.ofSeconds(15));

    }

    @Test
    void fieldsAllEmpty() {
        $$x("//input[@type='text']").filter(visible).first().val("").pressTab();
        $$x("//input[@type='tel']").exclude(hidden).first().sendKeys(Keys.DELETE);
        $$x("//input[@type='text']").filter(visible).last().val("");
        $$x("//input[@type='tel']").exclude(hidden).last().val("");
        $$(withText("Забронировать")).get(0).click();
        $x("//*[contains(text(),'Поле обязательно')]").should(appear, Duration.ofSeconds(15));

    }

    @Test
    void fieldTownWrong() {
        $$x("//input[@type='text']").filter(visible).first().val("Вадим").pressTab();
        $$x("//input[@type='tel']").exclude(hidden).first().sendKeys(Keys.DELETE, "30.03.2022");
        $$x("//input[@type='text']").filter(visible).last().val("Ужакин Вадим");
        $$x("//input[@type='tel']").exclude(hidden).last().val("+79993336666");
        $("[data-test-id=agreement]").click();
        $$(withText("Забронировать")).get(0).click();
        $x("//*[contains(text(),'Доставка в выбранный город недоступна')]").should(appear, Duration.ofSeconds(15));

    }

    @Test
    void fieldTownWrong2() {
        $$x("//input[@type='text']").filter(visible).first().val("Novosibirsk").pressTab();
        $$x("//input[@type='tel']").exclude(hidden).first().sendKeys(Keys.DELETE, "30.03.2022");
        $$x("//input[@type='text']").filter(visible).last().val("Ужакин Вадим");
        $$x("//input[@type='tel']").exclude(hidden).last().val("+79993336666");
        $("[data-test-id=agreement]").click();
        $$(withText("Забронировать")).get(0).click();
        $x("//*[contains(text(),'Доставка в выбранный город недоступна')]").should(appear, Duration.ofSeconds(15));

    }

    @Test
    void fieldDataEmpty() {
        $$x("//input[@type='text']").filter(visible).first().val("Кемерово").pressTab();
        $$x("//input[@type='tel']").exclude(hidden).first().sendKeys(Keys.DELETE);
        $$x("//input[@type='text']").filter(visible).last().val("Ужакин Вадим");
        $$x("//input[@type='tel']").exclude(hidden).last().val("+79993336666");
        $("[data-test-id=agreement]").click();
        $$(withText("Забронировать")).get(0).click();
        $x("//*[contains(text(),'Неверно введена дата')]").should(appear, Duration.ofSeconds(15));

    }

    @Test
    void fieldDataCurrent() {
        $$x("//input[@type='text']").filter(visible).first().val("Кемерово").pressTab();
        $$x("//input[@type='tel']").exclude(hidden).first().sendKeys(Keys.DELETE, "26.03.2022");
        $$x("//input[@type='text']").filter(visible).last().val("Ужакин Вадим");
        $$x("//input[@type='tel']").exclude(hidden).last().val("+79993336666");
        $("[data-test-id=agreement]").click();
        $$(withText("Забронировать")).get(0).click();
        $x("//*[contains(text(),'Заказ на выбранную дату невозможен')]").should(appear, Duration.ofSeconds(15));

    }

    @Test
    void fieldDataWrong() {
        $$x("//input[@type='text']").filter(visible).first().val("Кемерово").pressTab();
        $$x("//input[@type='tel']").exclude(hidden).first().sendKeys(Keys.DELETE, "25.03.2022");
        $$x("//input[@type='text']").filter(visible).last().val("Ужакин Вадим");
        $$x("//input[@type='tel']").exclude(hidden).last().val("+79993336666");
        $("[data-test-id=agreement]").click();
        $$(withText("Забронировать")).get(0).click();
        $x("//*[contains(text(),'Заказ на выбранную дату невозможен')]").should(appear, Duration.ofSeconds(15));

    }

    @Test
    void fieldDataTrue() {
        $$x("//input[@type='text']").filter(visible).first().val("Кемерово").pressTab();
        $$x("//input[@type='tel']").exclude(hidden).first().sendKeys(Keys.DELETE, "30.03.2022");
        $$x("//input[@type='text']").filter(visible).last().val("Ужакин Вадим");
        $$x("//input[@type='tel']").exclude(hidden).last().val("+79993336666");
        $("[data-test-id=agreement]").click();
        $$(withText("Забронировать")).get(0).click();
        $x("//*[contains(text(),'Встреча успешно забронирована')]").should(appear, Duration.ofSeconds(15));

    }

    @Test
    void fieldFamilyAndNameEmpty() {
        $$x("//input[@type='text']").filter(visible).first().val("Кемерово").pressTab();
        $$x("//input[@type='tel']").exclude(hidden).first().sendKeys(Keys.DELETE, "30.03.2022");
        $$x("//input[@type='text']").filter(visible).last().val("");
        $$x("//input[@type='tel']").exclude(hidden).last().val("+79993336666");
        $("[data-test-id=agreement]").click();
        $$(withText("Забронировать")).get(0).click();
        $x("//*[contains(text(),'Поле обязательно')]").should(appear, Duration.ofSeconds(15));

    }

    @Test
    void fieldFamilyAndNameTrue() {
        $$x("//input[@type='text']").filter(visible).first().val("Кемерово").pressTab();
        $$x("//input[@type='tel']").exclude(hidden).first().sendKeys(Keys.DELETE, "30.03.2022");
        $$x("//input[@type='text']").filter(visible).last().val("Вадим");
        $$x("//input[@type='tel']").exclude(hidden).last().val("+79993336666");
        $("[data-test-id=agreement]").click();
        $$(withText("Забронировать")).get(0).click();
        $x("//*[contains(text(),'Встреча успешно забронирована')]").should(appear, Duration.ofSeconds(15));

    }

    @Test
    void fieldFamilyAndNameTrue2() {
        $$x("//input[@type='text']").filter(visible).first().val("Кемерово").pressTab();
        $$x("//input[@type='tel']").exclude(hidden).first().sendKeys(Keys.DELETE, "30.03.2022");
        $$x("//input[@type='text']").filter(visible).last().val("Салтыков-Щедрин");
        $$x("//input[@type='tel']").exclude(hidden).last().val("+79993336666");
        $("[data-test-id=agreement]").click();
        $$(withText("Забронировать")).get(0).click();
        $x("//*[contains(text(),'Встреча успешно забронирована')]").should(appear, Duration.ofSeconds(15));

    }

    @Test
    void fieldFamilyAndNameWrong() {
        $$x("//input[@type='text']").filter(visible).first().val("Кемерово").pressTab();
        $$x("//input[@type='tel']").exclude(hidden).first().sendKeys(Keys.DELETE, "30.03.2022");
        $$x("//input[@type='text']").filter(visible).last().val("Салтыков_Щедрин");
        $$x("//input[@type='tel']").exclude(hidden).last().val("+79993336666");
        $("[data-test-id=agreement]").click();
        $$(withText("Забронировать")).get(0).click();
        $x("//*[contains(text(),'Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.')]").should(appear, Duration.ofSeconds(15));

    }

    @Test
    void fieldFamilyAndNameWrong2() {
        $$x("//input[@type='text']").filter(visible).first().val("Кемерово").pressTab();
        $$x("//input[@type='tel']").exclude(hidden).first().sendKeys(Keys.DELETE, "30.03.2022");
        $$x("//input[@type='text']").filter(visible).last().val("Vadim");
        $$x("//input[@type='tel']").exclude(hidden).last().val("+79993336666");
        $("[data-test-id=agreement]").click();
        $$(withText("Забронировать")).get(0).click();
        $x("//*[contains(text(),'Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.')]").should(appear, Duration.ofSeconds(15));

    }

    @Test
    void fieldPhoneEmpty() {
        $$x("//input[@type='text']").filter(visible).first().val("Кемерово").pressTab();
        $$x("//input[@type='tel']").exclude(hidden).first().sendKeys(Keys.DELETE, "30.03.2022");
        $$x("//input[@type='text']").filter(visible).last().val("Салтыков-Щедрин");
        $$x("//input[@type='tel']").exclude(hidden).last().val("");
        $("[data-test-id=agreement]").click();
        $$(withText("Забронировать")).get(0).click();
        $x("//*[contains(text(),'Поле обязательно для заполнения')]").should(appear, Duration.ofSeconds(15));

    }

    @Test
    void fieldPhoneWrong() {
        $$x("//input[@type='text']").filter(visible).first().val("Кемерово").pressTab();
        $$x("//input[@type='tel']").exclude(hidden).first().sendKeys(Keys.DELETE, "30.03.2022");
        $$x("//input[@type='text']").filter(visible).last().val("Салтыков-Щедрин");
        $$x("//input[@type='tel']").exclude(hidden).last().val("79993336666");
        $("[data-test-id=agreement]").click();
        $$(withText("Забронировать")).get(0).click();
        $x("//*[contains(text(),'Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.')]").should(appear, Duration.ofSeconds(15));

    }

    @Test
    void fieldPhoneWrong2() {
        $$x("//input[@type='text']").filter(visible).first().val("Кемерово").pressTab();
        $$x("//input[@type='tel']").exclude(hidden).first().sendKeys(Keys.DELETE, "30.03.2022");
        $$x("//input[@type='text']").filter(visible).last().val("Салтыков-Щедрин");
        $$x("//input[@type='tel']").exclude(hidden).last().val("+7999333666");
        $("[data-test-id=agreement]").click();
        $$(withText("Забронировать")).get(0).click();
        $x("//*[contains(text(),'Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.')]").should(appear, Duration.ofSeconds(15));

    }

    @Test
    void fieldCheckBoxNotValue() {
        $$x("//input[@type='text']").filter(visible).first().val("Кемерово").pressTab();
        $$x("//input[@type='tel']").exclude(hidden).first().sendKeys(Keys.DELETE, "30.03.2022");
        $$x("//input[@type='text']").filter(visible).last().val("Салтыков-Щедрин");
        $$x("//input[@type='tel']").exclude(hidden).last().val("+79993336666");
        $$(withText("Забронировать")).get(0).click();
        $x("//*[contains(text(),'Я соглашаюсь с условиями обработки и использования моих персональных данных')]").should(appear, Duration.ofSeconds(15));

    }

    @Test
    void shouldSubmitRequest() {
        $$x("//input[@type='text']").filter(visible).first().val("ке").sendKeys(Keys.DOWN, Keys.DOWN, Keys.ENTER);
        $$x("//input[@type='tel']").exclude(hidden).first().sendKeys(Keys.ARROW_DOWN, Keys.ARROW_RIGHT, Keys.ARROW_RIGHT, Keys.ARROW_RIGHT, Keys.ARROW_RIGHT);
        $$x("//input[@type='text']").filter(visible).last().val("Ужакин Вадим");
        $$x("//input[@type='tel']").exclude(hidden).last().val("+79993336666");
        $("[data-test-id=agreement]").click();
        $$(withText("Забронировать")).get(0).click();
        $x("//*[contains(text(),'Встреча успешно')]").should(appear, Duration.ofSeconds(15));

    }

}
