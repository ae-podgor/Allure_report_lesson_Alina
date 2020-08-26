package code;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;

import static io.restassured.RestAssured.given;


public class ApiSteps {

    @Step("Создаем задачу с заданным названием")
    public Issue createIssue(String title) {

        final Issue toCreate = new Issue();
        toCreate.setTitle(title);
        return given()
                .proxy(3128)
                .filter(new AllureRestAssured())
                .header("Authorization", "token 7d6f18ef8bfa181ffb08264df7758119c87e0940")
                .baseUri("https://api.github.com")
                .body(toCreate)
        .when()
                .post("/repos/ae-podgor/Allure_report_lesson_Alina/issues")
        .then()
                .statusCode(201)
        .extract()
                .as(Issue.class);
    }
}
