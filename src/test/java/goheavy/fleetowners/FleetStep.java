package goheavy.fleetowners;

import org.junit.Assert;
import general.Steps;
import goheavy.fleetowners.pages.FleetPage;

public class FleetStep extends Steps{
	private final FleetPage fleetPage;
	public FleetStep() {
		fleetPage = new FleetPage();
	}

	public void checkPage() {
		String path = fleetPage.getPagePath().toLowerCase();
		Assert.assertTrue(" The path provided is not correct in the url. path: " + path,
				fleetPage.getCurrentUrl().toLowerCase().contains(path));
	}
}
