package com.tieto.pages;

import com.tieto.models.Tariff;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class TariffContainer extends PageObject {

	@FindBy(linkText = "Подключить")
	private WebElementFacade buyBtn;

	@FindBy(css = ".tariff.unavailable .time>strong")
	private WebElementFacade curTime;

	@FindBy(css = ".tariff.unavailable .speed")
	private WebElementFacade curSpeed;

	@FindBy(css = ".tariff.unavailable .cost>strong")
	private WebElementFacade curCost;

	@FindBy(css = ".main-offer .time>strong")
	private WebElementFacade offerTime;

	@FindBy(css = ".main-offer .speed")
	private WebElementFacade offerSpeed;

	@FindBy(css = ".main-offer .cost>strong")
	private WebElementFacade offerCost;

	@FindBy(xpath = "//div[@class='increase']/a")
	private WebElementFacade increaseBtn;

	@FindBy(xpath = "//div[@class='decrease']/a")
	private WebElementFacade decreaseBtn;

	public Tariff getCurrentTariff() {
		Tariff currentTariff = new Tariff();
		currentTariff.setTime(curTime.getText());
		currentTariff.setCost(curCost.getText());
		currentTariff.setSpeed(curSpeed.getText().replaceAll("\n", " ").trim());
		return currentTariff;
	}

	public Tariff getOffer() {
		Tariff offer = new Tariff();
		offer.setTime(offerTime.getText());
		offer.setCost(offerCost.getText());
		offer.setSpeed(offerSpeed.getText().replaceAll("\n", " ").trim());
		return offer;
	}

	public void clickBtnBuy() {
		buyBtn.click();
	}

	public boolean isBuyBtnEnabled() {
		return buyBtn.getAttribute("class").equals("btn disabled");
	}

	public void clickBtnIncrease() {
//		WA элемент не кликабельный - перкрыт другим
		evaluateJavascript("$('div.increase').css({'z-index': '100000'})");
		increaseBtn.click();
	}

	public void clickBtnDecrease() {
//		WA элемент не кликабельный - перкрыт другим
		evaluateJavascript("$('div.decrease').css({'z-index': '100000'})");
		decreaseBtn.click();
	}
}
