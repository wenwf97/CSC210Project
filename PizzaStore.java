package com.example.demo1;

import java.util.Objects;

public class PizzaStore
{
    final private String[][] USERNAME_PASSWORD = {
            {"USERNAME", "PASSWORD"},
            {"Baskin",   "Chambers100"}};
    final private String[] ITEMS = {
            "CHEESE PIE",
            "PEPPERONI PIE",
            "MARGARITA PIE",
            "SODA"};
    final private double[] PRICE = {10.50, 12.59, 11.25, 2.50};
    final private double tax = 0.0875;

    //  Look for username and validate
    public boolean getUsername(String e)
    {
        return Objects.equals(USERNAME_PASSWORD[1][0], e);
    }

    //  Look for password and validate
    public boolean getPassword(String e)
    {
        return Objects.equals(USERNAME_PASSWORD[1][1], e);
    }

    //  Get and item name
    public String getItem(String item)
    {
        item = item.toUpperCase();

        for (String ITEMS : ITEMS)
        {
            if (ITEMS.startsWith(item))
            {
                return ITEMS;
            }
        }
        return "";
    }

    public double getPrice(String item)
    {
        item = item.toUpperCase();

        for (int i = 0; i < ITEMS.length; i++)
        {
            // compare the item name with what’s in the ITEMS array
            if (ITEMS[i].startsWith(item))
            {
                // same index in PRICE[] gives the correct price
                return PRICE[i];
            }
        }

        return 0.0;
    }


    //  Calculate and return the total cost
    public double getCost(double cheeseQTY,
                          double pepperoniQTY,
                          double margaritaQTY,
                          double sodaQTY)

    {
        double total = 0;

        total += cheeseQTY * getPrice("cheese");
        total += pepperoniQTY * getPrice("pepperoni");
        total += margaritaQTY * getPrice("margarita");
        total += sodaQTY * getPrice("soda");

        return total;
    }

    //  Calculate and return the total tax
    public double getTax(double subTotal)
    {
        return subTotal * tax;
    }

    //  Calculate and return the total
    public double getTotal(double subTotal)
    {
        return subTotal + getTax(subTotal);
    }
}


