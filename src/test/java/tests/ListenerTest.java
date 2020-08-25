package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.LogEvent;
import com.codeborne.selenide.logevents.LogEventListener;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static code.NamedBy.css;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Feature("Работа с задачами")
public class ListenerTest {

    private static final String BASE_URL = "https://github.com";
    private static final String REPOSITORY = "ae-podgor/qa_guru_course_2_Alina";
    private static final int ISSUE = 1;

    @BeforeEach
    public void initLogger() {
        SelenideLogger.addListener("allure", new AllureSelenide()
                .savePageSource(true)
                .screenshots(true));

//        Можно еще написать самостоятельно
//        SelenideLogger.addListener("allure", new LogEventListener() {
//            @Override
//            public void afterEvent(LogEvent currentLog) {
//                System.out.printf("Stop %s%n", currentLog.toString());
//            }
//
//            @Override
//            public void beforeEvent(LogEvent currentLog) {
//                System.out.printf("Start %s%n", currentLog.toString());
//            }
//        });
    }

    @Test
    @DisplayName("Пользователь должен иметь возможность найти Issue по номеру")
    public void shouldFindIssueByNumber () {
        open(BASE_URL);
        $(".header-search-input").click();
        $(".header-search-input").setValue(REPOSITORY);
        $(".header-search-input").submit();
        $(By.linkText("ae-podgor/qa_guru_course_2_Alina")).click();
        $(withText("Issues")).click();
        $(withText("#" + ISSUE)).should(Condition.exist);
    }

    @Test
    @DisplayName("Пользователь должен иметь возможность найти Issue по номеру")
    public void withNamedBy () {
        open(BASE_URL);
        $(css(".header-search-input").as("Поисковая строка в заголовке")).click();
        $(css(".header-search-input").as("Поисковая строка в заголовке")).setValue(REPOSITORY);
        $(css(".header-search-input").as("Поисковая строка в заголовке")).submit();
        $(By.linkText("ae-podgor/qa_guru_course_2_Alina")).click();
        $(withText("Issues")).click();
        $(withText("#" + ISSUE)).should(Condition.exist);
    }
}
