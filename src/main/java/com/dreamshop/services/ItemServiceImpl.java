package com.dreamshop.services;


import com.dreamshop.dao.ItemDao;
import com.dreamshop.dto.ItemDto;
import com.dreamshop.model.StockItem;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ItemServiceImpl implements ItemService
{

    @Autowired
    ItemDao itemDao;


    @Override
    public List<ItemDto> getStockItems() throws Exception
    {
        return itemDao.getStockItems();
    }

    @Override
    public List<ItemDto> getStockItems(List<String> stockKeepingUnits) throws Exception {
        return itemDao.getStockItems(stockKeepingUnits);
    }

    @Override
    public ItemDto getStockItem(String stockKeepingUnit) throws Exception
    {
        return itemDao.getStockItem(stockKeepingUnit);
    }


}
