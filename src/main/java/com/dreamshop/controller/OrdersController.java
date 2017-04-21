package com.dreamshop.controller;

import com.dreamshop.dto.ClientOrderDto;
import com.dreamshop.dto.OrderDto;
import com.dreamshop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController
{
    @Autowired
    OrderService orderService;



    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody
    List<OrderDto> getOrderList()
    {

        List<OrderDto> customerList = null;
        try
        {
            customerList = orderService.getAllOrders();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return customerList;
    }


    @RequestMapping(value = "/orderItems", method = RequestMethod.POST)
    public ResponseEntity makeOrder(@RequestBody ClientOrderDto order)
    {

        OrderDto result = null;
        try
        {
            result = orderService.addOrder(order.addressId, order.orderItems);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage() ,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
