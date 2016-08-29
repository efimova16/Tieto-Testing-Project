package com.tieto.tests;

import java.util.Arrays;
import java.util.Collection;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.TestData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import com.tieto.models.Tariff;
import com.tieto.steps.UserSteps;



@RunWith(SerenityParameterizedRunner.class)
public class TariffDataTest {
	@TestData
	public static Collection<Object[]> testData() {
		return Arrays.asList(new Object[][] { { 1, new Tariff(30, "64 Кбит/сек (макс.)", 0) },
				{ 2, new Tariff(30, "320 Кбит/сек (макс.)", 300) },
				{ 3, new Tariff(30, "416 Кбит/сек (макс.)", 350) },
				{ 4, new Tariff(30, "512 Кбит/сек (макс.)", 400) },
				{ 5, new Tariff(30, "640 Кбит/сек (макс.)", 450) },
				{ 6, new Tariff(30, "768 Кбит/сек (макс.)", 500) },
				{ 7, new Tariff(30, "896 Кбит/сек (макс.)", 550) },
				{ 8, new Tariff(30, "1.0 Мбит/сек (макс.)", 600) },
				{ 9, new Tariff(30, "1.3 Мбит/сек (макс.)", 650) },
				{ 10, new Tariff(30, "1.7 Мбит/сек (макс.)", 700) },
				{ 11, new Tariff(30, "2.1 Мбит/сек (макс.)", 750) },
				{ 12, new Tariff(30, "3.1 Мбит/сек (макс.)", 800) },
				{ 13, new Tariff(30, "4.1 Мбит/сек (макс.)", 850) },
				{ 14, new Tariff(30, "5.0 Мбит/сек (макс.)", 900) },
				{ 15, new Tariff(30, "5.7 Мбит/сек (макс.)", 950) },
				{ 16, new Tariff(30, "6.4 Мбит/сек (макс.)", 1000) },
				{ 17, new Tariff(30, "7.1 Мбит/сек (макс.)", 1050) },
				{ 18, new Tariff(30, "7.8 Мбит/сек (макс.)", 1100) },
				{ 19, new Tariff(30, "8.5 Мбит/сек (макс.)", 1150) },
				{ 20, new Tariff(30, "9.2 Мбит/сек (макс.)", 1200) },
				{ 21, new Tariff(30, "10.0 Мбит/сек (макс.)", 1250) },
				{ 22, new Tariff(30, "12.0 Мбит/сек (макс.)", 1300) },
				{ 23, new Tariff(30, "15.0 Мбит/сек (макс.)", 1350) },
				{ 24, new Tariff(30, "20.0 Мбит/сек (макс.)", 1400) } });
	}

	private final Tariff data;
	private final int number;

	public TariffDataTest(int number, Tariff data) {
		this.number = number;
		this.data = data;
	}

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
	@Title("Check offers conditions.")
	public void test() {
		user.opens_home_page();
		user.chooses_tariff_with_start(number);
		user.should_see_offer_with_conditions(data);
	}

	@After
	public void destroyApp() {
		process.destroy();
	}

}
