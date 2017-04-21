package com.dreamshop.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "O")
public class OrderedItem extends Item
{

    public OrderedItem() {
    }

    public OrderedItem(StockItem i)
    {
        name = i.name;
        price = i.price;
        stockKeepingUnit = i.stockKeepingUnit;
        amount = i.amount;

    }
}
