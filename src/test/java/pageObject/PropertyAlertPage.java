package pageObject;

import common.CommonFunctions;
import common.Hooks;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PropertyAlertPage {
    WebDriver driver;
    CommonFunctions commonFunctions = new CommonFunctions(Hooks.driver);

    int intTIMEOUT = Integer.parseInt(System.getProperty("objectTimeout").trim());
    By lblHeaderListDetail = By.xpath("//thead/tr/th");
    By lblValueListDetail = By.xpath("//tbody/tr/td");


    public PropertyAlertPage (WebDriver driver) {
        this.driver = driver;
    }

    public List<Map<String, String>> funcGetAllDataOfGridView() throws Exception {
        try {
            CommonFunctions.funcWaitUntilElementVisibility(driver, lblHeaderListDetail, intTIMEOUT);
            CommonFunctions.funcWaitUntilElementVisibility(driver, lblValueListDetail, intTIMEOUT);

            List<Map<String, String>> data = new ArrayList<>();
            List<String> headerList = new ArrayList<>();

            driver.findElements(lblHeaderListDetail).forEach(it -> {
                headerList.add(it.getText());
            });

            while (true) {
                List<String> valueList = new ArrayList<>();
                driver.findElements(lblValueListDetail).forEach(it -> {
                    valueList.add(it.getText());
                });

                for (int i = 0; i < valueList.size() / headerList.size(); i++) {
                    Map<String, String> record = new HashMap<>();
                    for (String key : headerList) {
                        int index = headerList.indexOf(key);
                        if (i > 0) index = index + i * headerList.size();
                        record.put(key, valueList.get(index));
                    }
                    data.add(record);
                }
                return data;
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void funcVerifyMyNewPropertyAlert() throws Exception {
        List<Map<String, String>> gridViewData;

        gridViewData = funcGetAllDataOfGridView();

        for (int i = 0; i < gridViewData.size(); i++) {
            Map<String, String> expectedRecord = gridViewData.stream().toList().get(i);

            String strMinPrice = System.getProperty("Min price" + Thread.currentThread().getName());
            String strMaxPrice = System.getProperty("Max price" + Thread.currentThread().getName());
            String strPriceRangeExpected = "$" + strMinPrice + " – " +"$" + strMaxPrice;

            if (expectedRecord.get("Name").equals(System.getProperty("Alert name" + Thread.currentThread().getName()))){
                Assert.assertEquals(expectedRecord.get("City"), System.getProperty("City" + Thread.currentThread().getName()));
                Assert.assertEquals(expectedRecord.get("Price range").replace(",",""), strPriceRangeExpected);
                Assert.assertEquals(expectedRecord.get("Bedrooms").replace("+",""), System.getProperty("Minimum bedrooms" + Thread.currentThread().getName()));
                Assert.assertEquals(expectedRecord.get("Type"), System.getProperty("Property type" + Thread.currentThread().getName()).toLowerCase());
            }
        }
    }

    public void funcSelectData() throws Exception {
        List<Map<String, String>> gridViewData;

        gridViewData = funcGetAllDataOfGridView();

        for (int i = 0; i < 1; i++) {
            Map<String, String> expectedRecord = gridViewData.stream().toList().get(i);

            System.setProperty("Alert name" + Thread.currentThread().getName(), expectedRecord.get("Name"));
            System.out.println("The selected data to be deleted: " + expectedRecord.get("Name"));

        }
    }

    public void funcConfirmDeleteAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // wait for alert show up
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());

            String alertText = alert.getText();
            System.out.println("Alert text: " + alertText);

            // Click OK
            alert.accept();

        } catch (Exception e) {
            System.out.println("Alert not found " + e.getMessage());
            throw e;
        }
    }

    public void funcEditDeletePropertyAlert(String strAction) throws Exception {
        String strItemDeleted = System.getProperty("Alert name" + Thread.currentThread().getName());
        By elePropertyAlertDeleted = By.xpath("//*[text()='" + strItemDeleted + "']/following-sibling::td[@class='text-end']//*[text()='" + strAction + "']");
        CommonFunctions.funcClickElement(driver, driver.findElement(elePropertyAlertDeleted), intTIMEOUT);
    }
}
