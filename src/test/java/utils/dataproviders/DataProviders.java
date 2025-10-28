package utils.dataproviders;

import org.testng.annotations.DataProvider;
import utils.models.CheckoutTestData;
import utils.models.LoginTestData;
import utilities.common.JsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataProviders {

    private static final String BASE = "src/test/resources/";

    @DataProvider(name = "validLoginData")
    public static Object[][] validLoginData() {
        List<Map<String, Object>> dataList = (List<Map<String, Object>>) JsonUtils.readJson(BASE + "Login.json");
        List<Object[]> providerData = new ArrayList<>();

        for (Map<String, Object> map : dataList) {
            String scenario = map.getOrDefault("scenario", "valid").toString();
            if (!"valid".equalsIgnoreCase(scenario)) continue;

            String username = map.get("username").toString();
            String password = map.get("password").toString();
            providerData.add(new Object[]{ new LoginTestData(scenario, username, password) });
        }
        return providerData.toArray(new Object[0][]);
    }

    @DataProvider(name = "invalidLoginData")
    public static Object[][] invalidLoginData() {
        List<Map<String, Object>> dataList = (List<Map<String, Object>>) JsonUtils.readJson(BASE + "Login.json");
        List<Object[]> providerData = new ArrayList<>();

        for (Map<String, Object> map : dataList) {
            String scenario = map.getOrDefault("scenario", "valid").toString();
            if (!"invalid".equalsIgnoreCase(scenario)) continue;

            String username = map.get("username").toString();
            String password = map.get("password").toString();
            providerData.add(new Object[]{ new LoginTestData(scenario, username, password) });
        }
        return providerData.toArray(new Object[0][]);
    }

    @DataProvider(name = "validLoginAndCheckoutData")
    public static Object[][] validLoginAndCheckoutData() {
        // قراءة بيانات اللوجن الصحيحة فقط
        List<Map<String, Object>> loginListMap = (List<Map<String, Object>>) JsonUtils.readJson(BASE + "Login.json");
        List<LoginTestData> validLoginList = new ArrayList<>();
        for (Map<String, Object> map : loginListMap) {
            if ("valid".equalsIgnoreCase(map.getOrDefault("scenario", "valid").toString())) {
                validLoginList.add(new LoginTestData(
                        map.get("scenario").toString(),
                        map.get("username").toString(),
                        map.get("password").toString()
                ));
            }
        }

        List<Map<String, Object>> checkoutListMap = (List<Map<String, Object>>) JsonUtils.readJson(BASE + "CheckoutTestData.json");
        CheckoutTestData checkout = new CheckoutTestData();
        Map<String, Object> firstCheckout = checkoutListMap.get(0);
        checkout.firstName = firstCheckout.get("firstName").toString();
        checkout.lastName = firstCheckout.get("lastName").toString();
        checkout.postalCode = firstCheckout.get("postalCode").toString();

        Object[][] providerData = new Object[1][2];
        providerData[0][0] = validLoginList.get(0);
        providerData[0][1] = checkout;

        return providerData;
    }
}
