package gov.uk.check.visa.pages;

import com.aventstack.extentreports.Status;
import gov.uk.check.visa.customlisteners.CustomListeners;
import gov.uk.check.visa.utility.Utility;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class StartPage extends Utility {
    public StartPage() {
        PageFactory.initElements(driver, this);
    }

    @CacheLookup
    @FindBy(xpath =  "//a[normalize-space()='Start now']")
    WebElement start;


    public void clickStartNow(){
        Reporter.log("Click on start now: " + start.toString());
        clickOnElement(start);
        CustomListeners.test.log(Status.PASS, "Click on start now");
    }
}
