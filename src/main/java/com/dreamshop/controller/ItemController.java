package com.dreamshop.controller;

import com.dreamshop.dto.ItemDto;
import com.dreamshop.model.StockItem;
import com.dreamshop.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemController
{

    @Autowired
    ItemService itemService;

    @RequestMapping(value = "/stock", method = RequestMethod.GET)
    public @ResponseBody
    List<ItemDto> getItemsStock()
    {

        List<ItemDto> result = null;
        try
        {
            result = itemService.getStockItems();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return result;
    }

}
