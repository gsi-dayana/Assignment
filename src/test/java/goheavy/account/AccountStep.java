package goheavy.account;

import general.Steps;
import goheavy.account.page.AccountPage;
import org.junit.Assert;

import static org.junit.Assert.fail;

@SuppressWarnings("unused")
public class AccountStep extends Steps{
    private final AccountPage accountPage;
    public AccountStep() {
        accountPage = new AccountPage();
    }

    @Override
    public void checkPage() {
        String path = accountPage.getPagePath().toLowerCase();
        Assert.assertTrue(" The path provide is not correct in the url. path: " + path,
                accountPage.getCurrentUrl().toLowerCase().contains(path));
        try {
            accountPage.waitForSpinningElementDisappear();
            accountPage.getFrom();
        } catch (Exception e) {
            fail("The view do not match with Account page.");
        }
    }
}
