import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class SearchIssueTests extends TestBase{
    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int NUMBER = 87;
    private static final String TITLE = "Issue for HW qa.guru";

    @Test
    public void searchIssueTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("");
        $(".search-input").click();
        $("#query-builder-test").setValue(REPOSITORY).pressEnter();
        $(linkText(REPOSITORY)).click();
        $("#issues-tab").click();
        $("#issue_"+ NUMBER +"_link").should(Condition.exist).shouldHave(text(TITLE));
    }

    @Test
    void searchIssueLambdaTest()
    {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
            open("");
        });
        step("Ищем репозиторий" + REPOSITORY, () -> {
            $(".search-input").click();
            $("#query-builder-test").setValue(REPOSITORY).pressEnter();
        });
        step("Переходим в репозиторий" + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Переходим на вкладку Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем наличие Issue с номером " + NUMBER + " и названием " + TITLE, () -> {
            $("#issue_"+ NUMBER +"_link").should(Condition.exist).shouldHave(text(TITLE));
        });
    }

    @Test
    void searchIssueAnnotatedStepsTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        TestSteps steps = new TestSteps();
        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumberAndTitle(NUMBER, TITLE);
    }

}
