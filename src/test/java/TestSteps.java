import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class TestSteps {

    @Step("Открываем главную страницу")
    public void openMainPage() {
        open("");
    }

    @Step("Ищем репозиторий {repo}")
    public void searchForRepository(String repo) {
        $(".search-input").click();
        $("#query-builder-test").setValue(repo).pressEnter();
    }

    @Step("Переходим в репозиторий {repo}")
    public void clickOnRepositoryLink(String repo) {
        $(linkText(repo)).click();
    }

    @Step("Переходим на вкладку Issues")
    public void openIssuesTab() {
        $("#issues-tab").click();
    }

    @Step("Проверяем наличие Issue с номером {issueNumber} и названием {issueTitle}")
    public void shouldSeeIssueWithNumberAndTitle(int issueNumber, String issueTitle) {
        $("#issue_" + issueNumber + "_link").should(Condition.exist).shouldHave(text(issueTitle));
    }

}
