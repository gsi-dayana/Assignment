package goheavy.account;

import general.*;
import general.Steps;
import goheavy.account.page.AccountPage;
import org.junit.Assert;

import static org.junit.Assert.fail;

public class AccountStep extends Steps{

    private AccountPage accountPage;

    public AccountStep() {
        accountPage = new AccountPage();
    }

    @Override
    public void checkPage() {
        String path = accountPage.getPagePath().toLowerCase();
        Assert.assertTrue(" The path privide is not correct in the url. path: " + path,
                accountPage.getCurrentUrl().toLowerCase().contains(path));
        try {
            accountPage.waitForSpinningElementDissapear();
            accountPage.getFrom();
        } catch (Exception e) {
            fail("The view do not match with Account page.");
        }
    }
}
