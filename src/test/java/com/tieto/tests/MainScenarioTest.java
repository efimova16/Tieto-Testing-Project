package com.tieto.tests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import com.tieto.models.Tariff;
import com.tieto.steps.UserSteps;

@RunWith(SerenityRunner.class)
public class MainScenarioTest {

	Process process;

	@Managed(uniqueSession = true)
	public WebDriver driver;

	@Steps
	public UserSteps user;

	@Before
	public void setUp() {
		process = user.run_application();
	}

	@Test
	@Title("Main scenario test.")
	public void test1() {
		user.opens_home_page();
		user.reset_all();
		user.should_see_balance_as(0);
		Tariff tariff = new Tariff(0, "64 Кбит/сек (макс.)", 0);
		user.should_see_current_tariff_with_conditions(tariff);
		user.should_see_buy_tariff_button_as_inactive();
		user.increases_balance("1000");
		user.should_see_balance_as(1000);
		for (int i = 0; i < 23; i++) {
			user.chooses_next_tariff();
			if (user.is_offer_cost_greater_than_balance()
					| user.is_current_tariff_like_offer()) {
				user.should_see_buy_tariff_button_as_inactive();
			} else
				user.should_see_buy_tariff_button_as_active();
		}
		user.chooses_tariff_with_end(16);
		user.clicks_buy_button();
		user.should_see_balance_as(0);
		tariff = new Tariff(0, "6.4 Мбит/сек (макс.)", 1000);
		user.should_see_current_tariff_with_conditions(tariff);
		user.increases_balance("950");
		user.should_see_balance_as(950);
		user.should_see_buy_tariff_button_as_inactive();
		for (int i = 0; i < 15; i++) {
			user.chooses_tariff_before();
			if (user.is_offer_cost_greater_than_balance()
					|| user.is_current_tariff_like_offer()) {
				user.should_see_buy_tariff_button_as_inactive();
			} else
				user.should_see_buy_tariff_button_as_active();
		}
		user.increases_balance("50");
		user.should_see_balance_as(1000);
		user.chooses_tariff_with_start(16);
		user.should_see_buy_tariff_button_as_inactive();
	}

	@After
	public void destroyApp() {
		process.destroy();
	}

}
