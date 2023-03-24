package pages;

import Utilities.Helper;
import base.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import java.util.List;

public class SearchPage extends BasePage {
    public SearchPage(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    Helper helper = new Helper();

    @AndroidFindBy(id = "com.zzkko:id/tv_searchbar_box")
    private AndroidElement searchField;

    @AndroidFindBy(id = "com.zzkko:id/root_container")
    private List<AndroidElement> items;

    @AndroidFindBy(accessibility = "BACK")
    private AndroidElement backBtn;

    @AndroidFindBy(id = "com.zzkko:id/cl_attribute")
    private AndroidElement buttons;

    @AndroidFindBy(id = "com.zzkko:id/btn_filter_layout")
    private AndroidElement filterBtn;

    @AndroidFindBy(className = "android.widget.TextView")
    private List<AndroidElement> sizeList;

    @AndroidFindBy(id = "com.zzkko:id/tv_apply")
    private AndroidElement doneBtn;

    @AndroidFindBy(id = "com.zzkko:id/tv_filter")
    private List<AndroidElement> filterCategory;


    public void searchAnItem() {
        addText(searchField, helper.getValuesFromPropertiesFile("ProductDetails", "SearchItem"));
        clickEnterBtn();
        waitVisibilityOfElement(buttons);
    }

    public void clickBackBtn() {
        waitVisibilityOfElement(backBtn);
        click(backBtn);
    }


    public void clickFilterButton() {
        click(filterBtn);
    }

    public void filterWithSize() {
        for (AndroidElement element : sizeList) {
            if (element.getText().equals(helper.getValuesFromPropertiesFile("ProductDetails", "ItemSize"))) {
                click(element);
                break;
            }
        }
    }

    public void clickApplyFilter() {
        click(doneBtn);
    }

    public void navigateItems() {
        for (AndroidElement element: filterCategory){
            if (element.getText().equals(helper.getValuesFromPropertiesFile("ProductDetails","FilterBy"))){
                click(element);
                break;
            }
        }
        swipe(Direction.UP);
    }
}
