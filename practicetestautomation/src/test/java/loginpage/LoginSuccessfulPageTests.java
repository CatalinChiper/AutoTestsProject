package loginpage;

import com.practicetestautomation.pages.LoginSuccessfulPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.practicetestautomation.pages.LoginPage.openPageAndLoginWithCredentials;
import static com.practicetestautomation.utils.LoginPageUtils.LOGIN_PAGE_URL;
import static com.practicetestautomation.utils.LoginPageUtils.PASSWORD;
import static com.practicetestautomation.utils.LoginPageUtils.USERNAME;
import static com.practicetestautomation.utils.LoginSuccessfulPageUtils.LOGIN_SUCCESSFUL_PAGE_TITLE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LoginSuccessfulPageTests {

    protected WebDriver webDriver;
    protected LoginSuccessfulPage loginSuccessfulPage;

    public final static String LOGIN_SUCCESSFUL_PAGE_CONTENT = "Congratulations student. You successfully logged in!";

    @BeforeEach
    public void beforeTest() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get(LOGIN_PAGE_URL);
        openPageAndLoginWithCredentials(webDriver, USERNAME, PASSWORD);
        loginSuccessfulPage = LoginSuccessfulPage.using(webDriver);
    }

    @Test
    public void checkPageTitleTest() {
        loginSuccessfulPage
                .checkPageTitle(LOGIN_SUCCESSFUL_PAGE_TITLE);
    }

    @Test
    public void checkPageContentTest() {
        loginSuccessfulPage
                .checkPageContent(LOGIN_SUCCESSFUL_PAGE_CONTENT);
    }

    @Test
    public void checkLogoutButtonIsFunctionalTest() {
        loginSuccessfulPage
                .clickLogoutButton();

        assertThat(webDriver.getCurrentUrl(),
                is(LOGIN_PAGE_URL));
    }

    @AfterEach
    public void tearDown() {
        webDriver.quit();
    }
}
