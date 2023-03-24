package pages;

import Utilities.Helper;
import base.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import java.util.List;

public class CategoryPage extends BasePage {
    public CategoryPage(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    Helper helper = new Helper();

    @AndroidFindBy(id = "com.zzkko:id/tv_layout_category_left_text")
    private List<AndroidElement> categoriesList;

    @AndroidFindBy(id = "com.zzkko:id/txt_title")
    private List<AndroidElement> categoryType;

    @AndroidFindBy(id = "com.zzkko:id/img")
    private List<AndroidElement> items;

    @AndroidFindBy(id = "com.zzkko:id/ll_sort")
    private AndroidElement sortBtn;


    public void selectCategory() {
        for (AndroidElement element : categoriesList) {
            if (element.getText().equals(helper.getValuesFromPropertiesFile("ProductDetails", "Category"))) {
                click(element);
                break;
            }
        }
    }

    public void selectCategoryType() {
        for (AndroidElement element : categoryType) {
            if (element.getText().equals(helper.getValuesFromPropertiesFile("ProductDetails", "Type"))) {
                click(element);
                break;
            }
        }
    }

    public void clickTheFirstItem(){
        waitVisibilityOfElement(sortBtn);
        click(items.get(0));
    }
}
