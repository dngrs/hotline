package pages.entities;

import pages.ShopPage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Igor Odokienko.
 */
public class ShopDB {

    private static Map<String, String> shops = new HashMap<>();

    static {
        shops.put("Техно Ёж", "tehnoezh.ua");
        shops.put("Миньон.com.ua", "minion.com.ua");
        shops.put("AppleCover", "applecover.com.ua");
        shops.put("origi.com.ua", "origi.com.ua");
    }

    private ShopDB() {
    }

    public static String getShopUrlByName(String shopName) {
        return shops.containsKey(shopName) ? shops.get(shopName) : " ";
    }

}
