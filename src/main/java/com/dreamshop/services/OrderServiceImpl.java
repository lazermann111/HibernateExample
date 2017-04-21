package com.dreamshop.services;

import com.dreamshop.dao.CustomerDao;
import com.dreamshop.dao.ItemDao;
import com.dreamshop.dao.OrderDao;
import com.dreamshop.dto.AddressDto;
import com.dreamshop.dto.ItemDto;
import com.dreamshop.dto.OrderDto;
import com.dreamshop.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService
{

	@Autowired
	OrderDao orderDao;

	@Autowired
	CustomerDao customerDao;

	@Autowired
	ItemDao itemDao;
	
	@Override
	public OrderDto addOrder(long addressId, Map<String,Integer> clientOrder ) throws Exception {



		AddressDto shippingAddress = customerDao.getAddress(addressId);
		if(shippingAddress == null)  throw new ServiceException("No such address");


		List<ItemDto> items = itemDao.getStockItems(clientOrder.keySet());

		if(items.size() < clientOrder.size()) throw new ServiceException("Some items are not in stock");


		OrderDto result = new OrderDto();
		result.setShippingAddress(shippingAddress);
		result.setItems(new ArrayList<ItemDto>());

		int totalOrderCost =0;

		Map<String, ItemDto> newOrderItems = new HashMap<>();
		for (ItemDto stockItem : items)
		{

			// trying to assemble order from stock items
			// it may happen so we have to assemble 2  stock items with same SKU
			// if client order is big enough
			Integer clientOrderAmount = clientOrder.get(stockItem.getStockKeepingUnit());
			if(clientOrderAmount == null) continue;

			int stockItemAmount = stockItem.getAmount();
			int amountToAdd;
			if(stockItemAmount < clientOrderAmount)
			{
				stockItem.setAmount(0);
				clientOrder.put(stockItem.getStockKeepingUnit(), clientOrderAmount - stockItemAmount) ;
				amountToAdd = stockItemAmount;
			}
			else
			{
				stockItem.setAmount(stockItemAmount - clientOrderAmount);
				clientOrder.remove(stockItem.getStockKeepingUnit());
				amountToAdd = clientOrderAmount;
			}

			ItemDto i = newOrderItems.get(stockItem.getStockKeepingUnit());
			if(i == null)
			{
				ItemDto newItem = new ItemDto(stockItem);
				newItem.setAmount(amountToAdd);
				newOrderItems.put(stockItem.getStockKeepingUnit(), newItem);
			}
			else
			{
				i.setAmount(i.getAmount() + amountToAdd);
			}
			totalOrderCost += clientOrderAmount*stockItem.getPrice();
		}

		result.setItems(new ArrayList<>(newOrderItems.values()));

		if(!clientOrder.isEmpty()) throw new ServiceException("Some items are not in stock");
		if(shippingAddress.getCustomer().getCredit() < totalOrderCost) throw new ServiceException("Customer has not enough money");
		result.setTotalPrice(totalOrderCost);

		int customerCredit = result.getShippingAddress().getCustomer().getCredit();
		result.getShippingAddress().getCustomer().setCredit(customerCredit - totalOrderCost);

		return orderDao.addOrder(result, items);
	}

	@Override
	public List<OrderDto> getAllOrders() throws Exception {
		return orderDao.getOrderList();
	}





}
