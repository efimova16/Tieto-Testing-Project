package com.tieto.steps;

import java.io.File;
import java.io.IOException;
import com.tieto.models.Tariff;
import com.tieto.pages.AccountContainer;
import com.tieto.pages.HomePage;
import com.tieto.pages.TariffContainer;
import static org.assertj.core.api.Assertions.*;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import net.thucydides.core.util.EnvironmentVariables;

public class UserSteps extends ScenarioSteps {

	EnvironmentVariables extjar;
	HomePage homePage;
	AccountContainer accountContainer;
	TariffContainer tariffContainer;

	@Step
	public Process run_application() {
		Process process = null;
		String filePath = extjar.getProperty("extjar");
		if (filePath != null) {
			File file = new File(filePath);
			Runtime re = Runtime.getRuntime();
			try {
				process = re.exec("java -jar " + file.getAbsolutePath());
			} catch (IOException ioe) {
				ioe.printStackTrace();
				throw new RuntimeException(
						"Cannot start application! Please check extjar in property file.");
			}
		}
		return process;
	}

	@Step
	public void opens_home_page() {
		homePage.open();
		assertThat(homePage.getTitle()).isEqualTo("Yota test slider");
	}

	@Step
	public void reset_all() {
		accountContainer.clickBtnReset();
	}

	@Step
	public void should_see_balance_as(double balance) {
		assertThat(accountContainer.getBalance()).isEqualTo(balance);
	}

	@Step
	public void should_see_current_tariff_with_conditions(Tariff tariff) {
		Tariff curTariff = tariffContainer.getCurrentTariff();
		assertThat(curTariff).isEqualTo(tariff);
	}

	@Step
	public void should_see_buy_tariff_button_as_inactive() {
		assertThat(tariffContainer.isBuyBtnEnabled()).isTrue();
	}

	@Step
	public void should_see_buy_tariff_button_as_active() {
		assertThat(tariffContainer.isBuyBtnEnabled()).isFalse();
	}

	@Step
	public void increases_balance(String amount) {
		accountContainer.enterAmount(amount);
		accountContainer.clickBtnTopUp();
		waitABit(3000);
	}

	@Step
	public void clicks_buy_button() {
		tariffContainer.clickBtnBuy();
	}

	@Step
	public void chooses_next_tariff() {
		tariffContainer.clickBtnIncrease();
	}
	
	
	@Step
	public void chooses_tariff_before() {
		tariffContainer.clickBtnDecrease();
	}


	@Step
	public void should_see_offer_with_conditions(Tariff tariff) {
		Tariff offer = tariffContainer.getOffer();
		assertThat(offer).isEqualTo(tariff);
	}

	public boolean is_offer_cost_greater_than_balance() {
		return (double) tariffContainer.getOffer().getCost() > accountContainer
				.getBalance();
	}

	public boolean is_current_tariff_like_offer() {
		return tariffContainer.getCurrentTariff().equals(
				tariffContainer.getOffer());
	}

	@Step
	public void chooses_tariff_with_start(int number) {
		for (int i = 1; i < number; i++) {
			tariffContainer.clickBtnIncrease();
		}
	}

	@Step
	public void chooses_tariff_with_end(int number) {
		for (int i = 1; i < (25 - number); i++) {
			tariffContainer.clickBtnDecrease();
		}
	}

}
