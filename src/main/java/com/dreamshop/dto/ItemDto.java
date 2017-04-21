package com.dreamshop.dto;


public class ItemDto
{
    private long id;
    private String stockKeepingUnit;
    private String name;
    private int price;
    private int amount;


    public ItemDto() {

    }

    public ItemDto(ItemDto other) {
        stockKeepingUnit = other.stockKeepingUnit;
        name = other.name;
        price = other.price;
        amount = other.amount;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStockKeepingUnit() {
        return stockKeepingUnit;
    }

    public void setStockKeepingUnit(String stockKeepingUnit) {
        this.stockKeepingUnit = stockKeepingUnit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
