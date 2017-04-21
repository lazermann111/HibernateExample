package com.dreamshop.services;


import com.dreamshop.dto.ItemDto;

import java.util.List;

public interface ItemService
{
     List<ItemDto> getStockItems() throws Exception;
     List<ItemDto> getStockItems(List<String> stockKeepingUnits) throws Exception;
     ItemDto getStockItem(String stockKeepingUnit) throws Exception;
}
