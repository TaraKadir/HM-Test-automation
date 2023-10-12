package test;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import testbase.TestBaseHM;

public class TestSubscribeNewsletter extends TestBaseHM {

	@Test
	public void subscribeNewsletterTest() {

		// 1. Navigera till nyhetsbrevssidan.
		page.navigate("https://www2.hm.com/sv_se/customer-service/nyhetsbrev.html");

		Locator acceptCookies = page.getByRole(AriaRole.BUTTON,
				new Page.GetByRoleOptions().setName("Tillåt alla cookies"));

		if (acceptCookies.isVisible()) {
			acceptCookies.click();

			// 2. Ange en e-postadress och postnummer.
			page.locator("#txt-email").type("tara.kadir@hotmail.com");
			page.locator("#txt-postal-code").type("17751");

			// Klicka i villkoren.
			page.locator("#cs-fashion-news").check();

			// 3. Klicka på "Prenumerera".
			page.locator("input[type='submit'][value='Ja tack, jag vill prenumerera!']").click();

			// Locator för elementet
			Locator thankYouMessage = page.locator("p:has-text('TACK! Du prenumererar redan på Fashion News')");

			// Assertion för att verifiera att meddelandet inte är synligt
			assertFalse(thankYouMessage.isVisible(), "TACK! Du prenumererar redan på Fashion News är inte synligt.");

		}
	}

}
