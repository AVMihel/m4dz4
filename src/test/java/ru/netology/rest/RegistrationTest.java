package ru.netology.rest;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

class RegistrationTest {

    public String generateDate(int days, String pattern) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
    }


    @Test
    void shouldBeReturnedFormCompletedSuccessfully() {
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
        $("button.button_view_extra").click();

        //Проверка успешной отправки
        $(Selectors.withText("Успешно!"))
                .should(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldBeReturnedFormCompletedSuccessfullyComplexElement() {
        Selenide.open("http://localhost:9999");

        //Поле "Город"
        $("[data-test-id='city'] input").setValue("Сал");
        $$(".menu-item__control").findBy(text("Салехард")).click();
        $("[data-test-id='city'] .input__control")
                .shouldHave(value("Салехард"));

        //Поле "Дата встречи"
        $("[data-test-id='date'] input").click();
        String planningDate = generateDate(7, "dd.MM.yyyy");
        String day = generateDate(7, "d");
        $$(".calendar__day").findBy(text(day)).click();
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
        $("button.button_view_extra").click();

        //Проверка успешной отправки
        $(Selectors.withText("Успешно!"))
                .should(visible, Duration.ofSeconds(15));
    }
}
