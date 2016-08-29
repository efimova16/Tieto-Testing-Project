package com.tieto.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class AccountContainer extends PageObject {
	
	@FindBy(css = "#balance-holder>span")
	private WebElementFacade balanceSpan;
	
	@FindBy(linkText = "Сброс")
	private WebElementFacade resetBtn;
	
	@FindBy(xpath = "//a[normalize-space(text())='Пополнить счет']")
	private WebElementFacade topUpBtn;	

	@FindBy(id = "amount")
	private WebElementFacade amountInput;
	
	
	public double getBalance() {	
		return Double.parseDouble(balanceSpan.getText());
	}

	public void clickBtnTopUp() {
		topUpBtn.click();
	}
	
	

	public void clickBtnReset() {
		resetBtn.click();
	}
	
	
	public void enterAmount(String amount) {
		if (amount != null) {
			amountInput.clear();
			amountInput.sendKeys(amount);
		}
	}

}
