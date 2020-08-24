package tests;

import code.BasicSteps;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.*;

@Owner("apodgornova")
@Feature("Работа с задачами")
public class IssueTest {

    private static final String BASE_URL = "https://github.com";
    private static final String REPOSITORY = "ae-podgor/qa_guru_course_2_Alina";
    private static final int ISSUE = 1;

    private BasicSteps steps = new BasicSteps();

    @Test
    @DisplayName("Пользователь должен иметь возможность найти Issue по номеру")
    public void shouldFindIssueByNumber () {
        parameter("Репозиторий ", REPOSITORY);
        parameter("Номер задачи ", ISSUE);
        link("Github", String.format("%s/%s", BASE_URL, REPOSITORY));

        step("Открываем главную страницу", () -> {
            open(BASE_URL);
        });
        step("Ищем репозиторий "  + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").setValue(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("Переходим по ссылке репозитория " + REPOSITORY, () -> {
            $(By.linkText("ae-podgor/qa_guru_course_2_Alina")).click();
        });
        step("Открываем страницу с задачами", () -> {
            $(withText("Issues")).click();

        });
        step("Проверяем наличие задачи с номером " + ISSUE, () -> {
            $(withText("#" + ISSUE)).should(Condition.exist);
        });
    }

    @Test
    @DisplayName("Пользователь не должен найти отсутствующую Issue по номеру")
    public void shouldNotFindMissingIssueByNumber () {
        parameter("Репозиторий ", REPOSITORY);
        parameter("Номер задачи ", ISSUE);
        link("Github", String.format("%s/%s", BASE_URL, REPOSITORY));

        step("Открываем главную страницу", () -> {
            open(BASE_URL);
        });
        step("Ищем репозиторий "  + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").setValue(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("Переходим по ссылке репозитория " + REPOSITORY, () -> {
            $(By.linkText("ae-podgor/qa_guru_course_2_Alina")).click();
        });
        step("Открываем страницу с задачами", () -> {
            $(withText("Issues")).click();

        });
        step("Проверяем наличие задачи с номером " + ISSUE);
        $(withText("#" + ISSUE)).should(Condition.exist);
    }


    @Test
    @DisplayName("Пользователь должен иметь возможность найти Issue по номеру")
    public void shouldFindIssueByNumberSecond () {
        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.openRepositoryByLink(REPOSITORY);
        steps.openIssuesPage();
        steps.shouldSeeIssueWithNumber(ISSUE);
    }

    @Test
    @DisplayName("Пользователь не должен найти отсутствующую Issue по номеру2")
    public void shouldNotFindMissingIssueByNumberSecond () {
        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.openRepositoryByLink(REPOSITORY);
        steps.openIssuesPage();
        steps.shouldNotSeeIssueWithNumber(1111);
    }


//     Плюсы  и минусы подходов
//     1. Подход, где прописывается сценарий полностью внутри теста
//     (shouldFindIssueByNumber и shouldNotFindMissingIssueByNumber)
//     + наглядность
//     + если тест упал, то понятно где именно
//
//     - нагроможденность
//
//     Используется при создании инструментов самостоятельно или при самоятельной разработке веб-интерфейсов
//     2. Подход выноса степов отдельно
//     (shouldFindIssueByNumber2 и shouldNotFindMissingIssueByNumber2)
//     + лаконичность
//     + переиспользуемость кода
//     + легче редактировать тесты
//
//     - скрытая реализация, т.е. логика расположена в других классах
//
//     Чаще используется при тестировании черного ящика

}
