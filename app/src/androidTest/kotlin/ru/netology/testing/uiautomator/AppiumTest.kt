package ru.netology.testing.uiautomator

import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.remote.MobileCapabilityType
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.remote.DesiredCapabilities
import java.net.URL

class AppiumTest {
    private lateinit var driver: AppiumDriver
    private val textToSet = "Netology"
    private val emptyText = ""
    private val whitespaceText = " "
    private val appPackage = "ru.netology.testing.uiautomator"

    @Before
    fun setUp() {
        val capabilities = DesiredCapabilities().apply {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("appium:automationName", "AppiumTest");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:appPackage", appPackage);
        desiredCapabilities.setCapability("appium:appActivity", appPackage".MainActivity");
        desiredCapabilities.setCapability("appium:deviceName", "some name");

        }

        driver = AndroidDriver(URL("http://127.0.0.1:4723/wd/hub"), capabilities)
        mainScreen = new MainScreen(driver);
    }

    @Test
    fun testEmptyInput() {
        val initialText = driver.findElementById("$appPackage:id/textToBeChanged").text

        // Пустая строка
        driver.findElementById("$appPackage:id/userInput").sendKeys(emptyText)
        driver.findElementById("$appPackage:id/buttonChange").click()
        var result = driver.findElementById("$appPackage:id/textToBeChanged").text
        assertEquals(initialText, result)

        // с пробелами
        driver.findElementById("$appPackage:id/userInput").sendKeys(whitespaceText)
        driver.findElementById("$appPackage:id/buttonChange").click()
        result = driver.findElementById("$appPackage:id/textToBeChanged").text
        assertEquals(initialText, result)
    }

    @Test
    fun testOpenTextInNewActivity() {
        driver.findElementById("$appPackage:id/userInput").sendKeys(textToSet)
        driver.findElementById("$appPackage:id/buttonActivity").click()

        
        Thread.sleep(2000)

        val result = driver.findElementById("$appPackage:id/text").text
        assertEquals(textToSet, result)
    }

    @After
    fun tearDown() {
        driver.quit()
    }
}