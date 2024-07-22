package loginpage;


import com.practicetestautomation.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static com.practicetestautomation.utils.LoginPageUtils.*;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class LoginPageAt {

    protected WebDriver webDriver;

    private static final String USERNAME = "student";
    private static final String PASSWORD = "Password123";

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
        webDriver.get(ENV_BASE_URL);
    }

    @ParameterizedTest
    @MethodSource("invalidCredentials")
    public void checkInvalidCredentialsTest(String userName, String password, String errorMessage) {
        LoginPage.using(webDriver)
                .setUserName(userName)
                .setPassword(password)
                .clickSubmitButton()
                .checkValidationMessageHasText(errorMessage);
    }

    @AfterEach
    public void tearDown() {
        webDriver.quit();
    }

}
