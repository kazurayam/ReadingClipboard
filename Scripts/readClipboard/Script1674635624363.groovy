import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kazurayam.webdriverfactory.chrome.ChromeDriverFactory;
import com.kazurayam.webdriverfactory.chrome.LaunchedChromeDriver;
import org.openqa.selenium.WebDriver
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kazurayam.webdriverfactory.chrome.ChromePreferencesModifiers
TestObject makeTestObject(String xpath) {
	TestObject tObj = new TestObject(xpath)
	tObj.addProperty("xpath", ConditionType.EQUALS, xpath)
	return tObj
}

String url = "https://codepen.io/RevCred/pen/vxXrww"
TestObject locatorIFrame = makeTestObject("//iframe[@id='result']")
TestObject locatorButton = makeTestObject("//div[@id='copy']")

//WebUI.openBrowser("")

System.setProperty("webdriver.chrome.driver", DriverFactory.getChromeDriverPath())
ChromeDriverFactory cdFactory = ChromeDriverFactory.newChromeDriverFactory()
// grant JavaScript to get access to the OS Clipboard 
cdFactory.addChromePreferencesModifier(ChromePreferencesModifiers.grantAccessToClipboard())
LaunchedChromeDriver launched = cdFactory.newChromeDriver()
WebDriver driver = launched.getDriver()
DriverFactory.changeWebDriver(driver)

WebUI.navigateToUrl(url)
WebUI.verifyElementPresent(locatorIFrame, 10)
WebUI.switchToFrame(locatorIFrame, 10)
WebUI.verifyElementPresent(locatorButton, 10)
WebUI.click(locatorButton)

// execute a JavaScript script in the Chrome browser, which will read a text from the clipboard and return it to the caller
String js = """
    return navigator.clipboard.readText();
"""
String text = WebUI.executeJavaScript(js, null)

WebUI.comment("text read from the clipboard: " + text)
assert text.startsWith("https://staging.revolutioncredit.com/signupc/")

WebUI.closeBrowser()



// https://playwrightsolutions.com/how-do-i-access-the-browser-clipboard-with-playwright/

