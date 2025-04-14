package ru.netology.rest;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

class RegistrationTest {

public String generateDate (int days, String pattern) {
return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
}


    @Test
    void shouldBeReturnedFormCcompletedSuccessfully() {
        Selenide.open("http://localhost:9999");

        //Поле "Город"
        $("[data-test-id='city'] input").setValue("Салехард");
        $("[data-test-id='city'] .input__control")
                .shouldHave(value("Салехард"));

        //Поле "Дата встречи"
        String planningDate = generateDate(4, "dd.MM.yyyy");
        $("[data-test-id='date'] input")
                .sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id='date'] input").shouldHave(value(planningDate));

        //Поле "Фамилия и имя"
        $("[data-test-id='name'] input").setValue("Синицин Евгений");
        $("[data-test-id='name'] .input__control")
                .shouldHave(value("Синицин Евгений"));

        //Поле "Мобильный телефон"
        $("[data-test-id='phone'] input").setValue("+79397246154");
        $("[data-test-id='phone'] .input__control")
                .shouldHave(value("+79397246154"));

        //Чек-бокс
        $("[data-test-id='agreement']").click();

        //Кнопка
        $("button[type='button']").click();

        //Проверка успешной отправки
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='notification'] .notification__title")
                .shouldHave(exactText("Успешно!"));
    }
}