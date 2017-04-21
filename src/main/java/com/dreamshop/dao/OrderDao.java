package com.dreamshop.dao;

import com.dreamshop.dto.ItemDto;
import com.dreamshop.dto.OrderDto;

import java.util.List;

public interface OrderDao {

	 OrderDto addOrder(OrderDto order, List<ItemDto> itemsToUpdate) throws Exception;
	 List<OrderDto> getOrderList() throws Exception;
}
