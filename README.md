# Getting Clipboard Content from Remote Selenium Chrome nodes in Katalon Studio

Have a look at the following web page

- https://codepen.io/RevCred/pen/vxXrww

in there you can find a button:

![button](https://kazurayam.github.io/chromedriverfactory/images/10_copy-link-button.png)

If you click this button, the URL string displayed on the left of the button will be written into the OS Clipboard. The URL string may change. So a tester of this page would want to read the content text of the clipboard. He/she would want to get text out of the OS clipboard into his/here selenium test script and verify if the value is appropriate.

How to implement this testing story in Katalon Studio?

1. My Test Case script will open the page in a Chrome browser, find the button element, and click it. Then a text will be written into the OS clipboard.

2. My test script will execute a JavaScript script inside Chrome, that is:

```
return navigator.clipboard.readText();
```

3. WebDriver will return a text read out of the clipboard

4. My test script will accept the returned text and do whatever verification.

Here arises a difficulty. Chrome browser blocks my script. Chrome won't let my javascript to read the clipboad. Chrome will prompt a dialog and ask me if I grant the javascript to read the clipboard.

![blocker](https://kazurayam.github.io/chromedriverfactory/images/11_grant_access_to_clipboard.png)

I wanted to automate my test entirely. I wanted to configure Chrome NOT to elicit that disturbing prompt. The following article explained how to configure Chrome's preference.

["Getting Clipboad Content from Remote Selenium Chrome", AMIT RAWAT](https://sahajamit.medium.com/getting-clipboard-content-from-remote-selenium-chrome-nodes-67a4c4d862bd
)

So I have implemented my [`chromedriverfactory`, version 0.6.6](https://github.com/kazurayam/chromedriverfactory/releases/tag/0.6.6) to support configuring Chrome to grand the access to clipboard.

You can see the source of Katalon Test Case at

- []()

https://forum.katalon.com/t/value-from-buffer-copied-link-to-be-assigned-to-groovy-variable/83681/2

https://developer.mozilla.org/en-US/docs/Web/API/Clipboard/readText

https://docs.katalon.com/docs/legacy/katalon-studio-enterprise/keywords/web-ui-keywords/webui-execute-javascript


