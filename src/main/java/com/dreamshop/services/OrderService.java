package com.dreamshop.services;

import com.dreamshop.dto.OrderDto;

import java.util.List;
import java.util.Map;

public interface OrderService
{

	OrderDto addOrder(long addressId, Map<String,Integer> order ) throws Exception;
	 List<OrderDto> getAllOrders() throws Exception;
}
