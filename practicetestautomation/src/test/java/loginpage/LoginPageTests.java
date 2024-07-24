package loginpage;


import com.practicetestautomation.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static com.practicetestautomation.pages.LoginPage.openPageAndLoginWithCredentials;
import static com.practicetestautomation.utils.LoginPageUtils.EMPTY_STRING;
import static com.practicetestautomation.utils.LoginPageUtils.LOGIN_PAGE_URL;
import static com.practicetestautomation.utils.LoginPageUtils.INVALID_PASSWORD;
import static com.practicetestautomation.utils.LoginPageUtils.INVALID_USERNAME;
import static com.practicetestautomation.utils.LoginPageUtils.PASSWORD;
import static com.practicetestautomation.utils.LoginPageUtils.USERNAME;
import static com.practicetestautomation.utils.LoginSuccessfulPageUtils.LOGIN_SUCCESSFUL_PAGE_URL;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LoginPageTests {

    protected WebDriver webDriver;

    private static List<Arguments> invalidCredentials() {
        return List.of(
                Arguments.of(EMPTY_STRING, EMPTY_STRING, INVALID_USERNAME),
                Arguments.of(randomAlphabetic(10), randomAlphabetic(10), INVALID_USERNAME),
                Arguments.of(randomAlphabetic(10), PASSWORD, INVALID_USERNAME),
                Arguments.of(USERNAME, randomAlphabetic(10), INVALID_PASSWORD),
                Arguments.of(USERNAME, EMPTY_STRING, INVALID_PASSWORD)
        );
    }

    @BeforeEach
    public void beforeTest() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.get(LOGIN_PAGE_URL);
    }

    @ParameterizedTest
    @MethodSource("invalidCredentials")
    public void checkInvalidCredentialsTest(String userName, String password, String errorMessage) {
        openPageAndLoginWithCredentials(webDriver, userName, password);
        LoginPage.using(webDriver)
                .checkValidationMessageHasText(errorMessage);
    }

    @Test
    public void checkValidCredentialsTest() {
        openPageAndLoginWithCredentials(webDriver, "student", "Password123");

        assertThat(webDriver.getCurrentUrl(),
                is(LOGIN_SUCCESSFUL_PAGE_URL));

    }

    @AfterEach
    public void tearDown() {
        webDriver.quit();
    }
}
