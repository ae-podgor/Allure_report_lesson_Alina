package code;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;

import static io.restassured.RestAssured.given;

@JsonIgnoreProperties(ignoreUnknown = true) //используется для того,чтобы при конвертации json-a пропускать поля, которые мы не объявили
public class ApiSteps {

    @Step("Создаем задачу с заданным названием")
    public Issue createIssue(String title) {
        final Issue toCreate = new Issue();
        toCreate.setTitle(title);
        return given()
                .filter(new AllureRestAssured())
                .header("Authorization", "token fa64c3766b4c369b1e359ead8abc3c5745700e98")
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
