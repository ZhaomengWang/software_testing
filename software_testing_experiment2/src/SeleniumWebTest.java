import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;

@RunWith(Parameterized.class)
public class SeleniumWebTest
{
    private WebDriver driver;
    private String baseUrl;

    private String testName;
    private String testPwd;
    private String gitHubUrl;

    public SeleniumWebTest(String testName, String testPwd, String githubUrl) {
        this.testName = testName;
        this.testPwd = testPwd;
        this.gitHubUrl = githubUrl;
    }

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver","F:\\java_doc\\software_testing\\software_testing_experiment2\\chromedriver.exe");
        driver =new ChromeDriver();
        baseUrl = "http://103.120.226.190/selenium-demo/git-repo";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getData() {
        ArrayList<ArrayList<String>> excelData = new ArrayList<ArrayList<String>>();
        try {
            excelData = POIExcel.readExcelRow();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Object[][] obj = new Object[20][];

        for (int i = 0; i < excelData.size() ; i++)
        {
            String stuNum = excelData.get(i).get(0);
            String pwd = excelData.get(i).get(1);
            String githubUrl = excelData.get(i).get(1);
            if (stuNum.equals(""))
                break;
            else
                obj[i] = new Object[]{stuNum, pwd, githubUrl};

        }
        return Arrays.asList(obj);
    }

    @Test
    public void testMain() throws Exception {
        driver.get(baseUrl + "/");
        driver.findElement(By.name("user_number")).clear();
        driver.findElement(By.name("user_number")).sendKeys(testName);
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys(testPwd);
        driver.findElement(By.cssSelector("[type='submit']")).click();
        assertEquals(this.gitHubUrl, driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/div[2]/div/form/div[5]/code")).getText());
    }




}
