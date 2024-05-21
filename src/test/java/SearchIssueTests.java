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
    private static final String repository = "eroshenkoam/allure-example";
    private static final int issueNumber = 87;
    private static final String issueTitle = "Issue for HW qa.guru";

    @Test
    public void searchIssueTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://github.com");
        $(".search-input").click();
        $("#query-builder-test").setValue(repository).pressEnter();
        $(linkText(repository)).click();
        $("#issues-tab").click();
        $("#issue_"+issueNumber+"_link").should(Condition.exist).shouldHave(text(issueTitle));
    }

    @Test
    void searchIssueLambdaTest()
    {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });
        step("Ищем репозиторий" + repository, () -> {
            $(".search-input").click();
            $("#query-builder-test").setValue(repository).pressEnter();
        });
        step("Переходим в репозиторий" + repository, () -> {
            $(linkText(repository)).click();
        });
        step("Переходим на вкладку Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем наличие Issue с номером " + issueNumber + " и названием " + issueTitle, () -> {
            $("#issue_"+issueNumber+"_link").should(Condition.exist).shouldHave(text(issueTitle));
        });
    }

    @Test
    void searchIssueAnnotatedStepsTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        TestSteps steps = new TestSteps();
        steps.openMainPage();
        steps.searchForRepository(repository);
        steps.clickOnRepositoryLink(repository);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumberAndTitle(issueNumber, issueTitle);
    }

}
