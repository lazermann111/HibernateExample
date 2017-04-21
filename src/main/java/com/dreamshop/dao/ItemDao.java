package com.dreamshop.dao;


import com.dreamshop.dto.ItemDto;

import java.util.Collection;
import java.util.List;

public interface ItemDao
{
     List<ItemDto> getStockItems() throws Exception;
     List<ItemDto> getStockItems(Collection<String> stockKeepingUnits) throws Exception;
     ItemDto getStockItem(String stockKeepingUnit) throws Exception;

}
