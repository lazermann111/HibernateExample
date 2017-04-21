package com.dreamshop.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "S")
public class StockItem extends Item
{

}
