package goheavy.document;

import general.Steps;
import goheavy.document.pages.DocumentPage;
import org.junit.Assert;

public class DocumentStep extends Steps {

    private DocumentPage documentPage;

    public DocumentStep() {
        super();
        documentPage = new DocumentPage();
    }

    public void clicksOnDocumentIcon() {
        Assert.assertTrue(documentPage.clickOnDocIcon());
    }

    @Override
    public void checkPage() {
        documentPage.checkDocumentView();
    }

}
