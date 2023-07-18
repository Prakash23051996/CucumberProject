package FreeCRMApplictions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Basemethods.BaseClass;
import Basemethods.LocatorType;
import ObjectRepo.ObjectRepo;

public class CommonStepsInFreeCRMAppliaction extends BaseClass {

	private static final Logger log = LogManager.getLogger(CommonStepsInFreeCRMAppliaction.class);

	public void enterUserNameAndPassword() throws Exception {
		try {
			getElement(LocatorType.xpath, ObjectRepo.FreeCRMApplication.emailTextBox_xpath)
					.sendKeys(getDataFromPropertyFile("emailId"));
			getElement(LocatorType.xpath, ObjectRepo.FreeCRMApplication.passwordTextBox_xpath)
					.sendKeys(getDataFromPropertyFile("Password"));
			getElement(LocatorType.xpath, ObjectRepo.FreeCRMApplication.loginBtnHomePage_xpath).click();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void validateLoginPageErrorMessage() throws Exception {
		try {
			waitForElementToVisibile(LocatorType.xpath, ObjectRepo.FreeCRMApplication.loginPageErrorMessage_xpath);
			String text = getElement(LocatorType.xpath, ObjectRepo.FreeCRMApplication.loginPageErrorMessage_xpath)
					.getText();
			if (text.contains("Something went wrong...")) {
				System.out.println("Pass");
			} else {
				System.out.println("Fail");
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
