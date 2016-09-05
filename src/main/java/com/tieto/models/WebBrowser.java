package com.tieto.models;

import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//7. Написать класс WebBrowser,
//имплементирующий в себе средства seleniumWebDriver.
//В тестах не использую этот класс, так как в самом  фреймворке Serenity
//заложена кроссбраузерность. В данном случае в serenity.properties указано использование
//хром браузера, то есть в тестах будет создаваться экземпляр класса CromeDriver 
public abstract class WebBrowser implements WebDriver {

	@Override
	public String getTitle() {
		return "It's fake title for testing purpose!";
	}

	@Override
	public String getPageSource() {
		return "It's fake page source!";
	}

	@Override
	public abstract void get(String url);

	@Override
	public abstract String getCurrentUrl();

	@Override
	public abstract List<WebElement> findElements(By by);

	@Override
	public abstract WebElement findElement(By by);

	@Override
	public abstract void close();

	@Override
	public abstract void quit();

	@Override
	public abstract Set<String> getWindowHandles();

	@Override
	public abstract String getWindowHandle();

	@Override
	public abstract TargetLocator switchTo();

	@Override
	public abstract Navigation navigate();

	@Override
	public abstract Options manage();

}
