package tests;

import code.ApiSteps;
import code.Issue;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import code.BasicSteps;

public class GitHubApiAndWeb {

    private final BasicSteps webSteps = new BasicSteps();

    private static final String REPOSITORY = "ae-podgor/Allure_report_lesson_Alina";

    private final ApiSteps apiSteps = new ApiSteps();

    @BeforeEach
    public void initLogger() {
        SelenideLogger.addListener("allure", new AllureSelenide()
                .savePageSource(true)
                .screenshots(true));
    }

    @Test
    @DisplayName("Пользователь должен найти созданную через API Issue")
    public void shouldNotFindMissingIssueByNumberSecond () {


        final Issue created = apiSteps.createIssue("Hello from second stream!");

        webSteps.openMainPage();
        webSteps.searchForRepository(REPOSITORY);
        webSteps.openRepositoryByLink(REPOSITORY);
        webSteps.openIssuesPage();
        webSteps.shouldSeeIssueWithNumber(created.getNumber());
    }
}
