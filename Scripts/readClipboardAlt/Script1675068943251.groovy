import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import groovy.json.JsonSlurper

TestObject makeTestObject(String xpath) {
	TestObject tObj = new TestObject(xpath)
	tObj.addProperty("xpath", ConditionType.EQUALS, xpath)
	return tObj
}

String url = "https://codepen.io/RevCred/pen/vxXrww"
TestObject locatorIFrame = makeTestObject("//iframe[@id='result']")
TestObject locatorButton = makeTestObject("//div[@id='copy']")

Object chromePrefs = new JsonSlurper().parseText("""
{
  "profile.content_settings.exceptions.clipboard" : {
    "[*.],*": {
      "last_modified": "1576491240619",
      "setting": 1
    }
  }
}
""")
RunConfiguration.setWebDriverPreferencesProperty('prefs', chromePrefs)

WebUI.openBrowser("")
WebUI.navigateToUrl(url)
WebUI.verifyElementPresent(locatorIFrame, 10)
WebUI.switchToFrame(locatorIFrame, 10)
WebUI.verifyElementPresent(locatorButton, 10)
WebUI.click(locatorButton)

// execute a JavaScript script in the Chrome browser, which will read a text
// from the clipboard and return it to the caller
String js = """
    return navigator.clipboard.readText();
"""
String text = WebUI.executeJavaScript(js, null)

WebUI.comment("text read from the clipboard: " + text)
assert text.startsWith("https://staging.revolutioncredit.com/signupc/")

WebUI.closeBrowser()
