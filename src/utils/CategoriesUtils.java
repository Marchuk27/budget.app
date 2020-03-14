package utils;

import categories.AbstractCategory;
import categories.costs.*;
import categories.incomes.*;
import javafx.scene.control.Label;
import sample.ApplicationController;

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

    public static List<Label> getCategoriesLabels(List<AbstractCategory> categoryList) {
        List<Label> labelList = new ArrayList<>();
        for (AbstractCategory cat : categoryList) {
            labelList.add(cat.getCategoryLabel());
        }
        return  labelList;
    }
}
