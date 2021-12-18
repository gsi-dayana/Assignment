package goheavy.dashboard;

import org.junit.Assert;

import general.Steps;
import goheavy.dashboard.pages.Dashboard;

public class DashboardStep extends Steps{
	private final Dashboard dashboard;

	public DashboardStep() {
		dashboard = new Dashboard();
	}

	public void checkPage() {
		String path = dashboard.getPagePath().toLowerCase();
		Assert.assertTrue("The path provided is not correct in the url. path: " + path,
				dashboard.getCurrentUrl().toLowerCase().contains(path));
	}
}
