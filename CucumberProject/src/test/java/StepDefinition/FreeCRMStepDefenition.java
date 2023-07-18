package StepDefinition;

import Basemethods.BaseClass;
import Basemethods.LocatorType;
import CommoMethods.CommonMethods;
import FreeCRMApplictions.CommonStepsInFreeCRMAppliaction;
import ObjectRepo.ObjectRepo;
import io.cucumber.java.en.*;

public class FreeCRMStepDefenition extends BaseClass {

	CommonStepsInFreeCRMAppliaction commonSteps = new CommonStepsInFreeCRMAppliaction();

	@Given("^User is in FreeCRM Login Page$")
	public void user_User_is_in_FreeCRM_Login_Page() throws Exception {
		try {
			commonSteps.enterUserNameAndPassword();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Given("^Validate error Message$")
	public void Validate_error_Message() throws Exception {
		try {
			commonSteps.validateLoginPageErrorMessage();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
