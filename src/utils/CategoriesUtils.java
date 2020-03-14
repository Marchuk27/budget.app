package utils;

import categories.AbstractCategory;
import categories.costs.*;
import categories.incomes.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategoriesUtils {

    public static List<AbstractCategory> getCategoriesAsList() { //все категории в одном листе
        List<AbstractCategory> categoriesList = new ArrayList<>
                (Arrays.asList(Supermarkets.getInstance(), BeautyAndHealth.getInstance(), HouseAndRepairs.getInstance(),
                Transport.getInstance(), ClothesAndShoes.getInstance(), Entertainments.getInstance(),
                Gifts.getInstance(), StateServices.getInstance(), ZhkuAndNet.getInstance(), AnotherCosts.getInstance(),
                Salary.getInstance(), Business.getInstance(), Investments.getInstance(), Deposits.getInstance(),
                SocialPayments.getInstance(), AnotherIncomes.getInstance()));
        return categoriesList;
    }
}
