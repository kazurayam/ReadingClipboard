import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

TestObject makeTestObject(String xpath) {
	TestObject tObj = new TestObject(xpath)
	tObj.addProperty("xpath", ConditionType.EQUALS, xpath)
	return tObj
}

String url = "https://codepen.io/RevCred/pen/vxXrww"
TestObject locatorIFrame = makeTestObject("//iframe[@id='result']")
TestObject locatorButton = makeTestObject("//div[@id='copy']")

WebUI.openBrowser("")
WebUI.navigateToUrl(url)
WebUI.verifyElementPresent(locatorIFrame, 10)
WebUI.switchToFrame(locatorIFrame, 10)
WebUI.verifyElementPresent(locatorButton, 10)
WebUI.click(locatorButton)

String js = """
    return navigator.clipboard.readText();
"""

String text = WebUI.executeJavaScript(js, null)
WebUI.comment("text copied from the clipboard: " + text)

WebUI.closeBrowser()



// https://playwrightsolutions.com/how-do-i-access-the-browser-clipboard-with-playwright/

