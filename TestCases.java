package demo;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCases {
    ChromeDriver driver;

    public TestCases() {
        System.out.println("Constructor: TestCases");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();
    }

    public void testCase01() throws InterruptedException {
        System.out.println("Start Test case: testCase01");
        navigateToForm();
        fillForm();
        submitForm();
        System.out.println("end Test case: testCase01");
    }

    private void navigateToForm() throws InterruptedException {
        try {
        	driver.get("https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");
        	Thread.sleep(3000);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
    }

    private void fillForm() throws InterruptedException {
        fillName();
        fillTextArea();
        selectExperience();
        selectSkills();
        scrollToElement();
        selectAddressedOption();
        fillDateTime();
    }

    private void submitForm() throws InterruptedException {
        WebElement submitBtn = driver.findElement(By.xpath("//span[text()='Submit']"));
        submitBtn.click();
        sleep(2000);
        WebElement message = driver.findElement(By.xpath("//div[@class='vHW8K']"));
        System.out.println(message.getText());
    }

    private void fillName()  {
        WebElement name = driver.findElement(By.xpath("(//input[contains(@class,'whsOnd') and @jsname='YPqjbf'])[1]"));
        name.sendKeys("Girish Kumar");
    }

    private void fillTextArea() {
        long epochTime = System.currentTimeMillis();
        String finalepochTime = "I want to be the best QA Engineer!" + epochTime;
        WebElement textArea = driver.findElement(By.xpath("//textarea[contains(@class,'KHxj8b')]"));
        textArea.sendKeys(finalepochTime);
    }

    private void selectExperience() {
        List<WebElement> yearOfExperience = driver.findElements(By.xpath("//div//div[contains(@class,'wFGF8')]"));
        for (WebElement expRange : yearOfExperience) {
            if (expRange.getText().contains("3 - 5")) {
                expRange.click();
            }
        }
    }

    private void selectSkills() {
       List<WebElement> skills = driver.findElements(By.xpath("//div[contains(@class,'wFGF8')]//div[@role='checkbox']"));
        for (int i = 0; i < skills.size(); i++) {
            if (i != 2) {
                skills.get(i).click();
            }
        }
    }

    private void scrollToElement() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,200)");
    }

    private void selectAddressedOption() throws InterruptedException {
        WebElement howTobeAddressed = driver.findElement(By.xpath("(//div[contains(@class,'MocG8c') and @jsname='wQNmvb'])[1]"));
        howTobeAddressed.click();
        sleep(2000);
        List<WebElement> addressedOption = driver.findElements(By.xpath("//div[contains(@class,'LMgvRb')]"));
        for (WebElement option : addressedOption) {
            if (option.getText().contains("Mr")) {
                option.click();
            }
        }
    }

    private void fillDateTime() throws InterruptedException {
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, -7);
        Date previousDate = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formatedDate = dateFormat.format(previousDate);
        WebElement date = driver.findElement(By.xpath("(//div//input[contains(@class,'whsOnd') and @jsname='YPqjbf'])[2]"));
        date.sendKeys(formatedDate);

        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String formattedTime = currentTime.format(formatter);

        int hour = currentTime.getHour() - 12;
        String hourValue = Integer.toString(hour);
        int minute = currentTime.getMinute();
        String minuteValue = Integer.toString(minute);
        String am_pm = formattedTime.substring(formattedTime.length() - 2);

        WebElement hourPart = driver.findElement(By.xpath("(//input[@class='whsOnd zHQkBf'])[3]"));
        hourPart.sendKeys(hourValue);

        WebElement minutePart = driver.findElement(By.xpath("(//input[@class='whsOnd zHQkBf'])[4]"));
        minutePart.sendKeys(minuteValue);

        sleep(3000);
    }

    private void sleep(int milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
    }
}
