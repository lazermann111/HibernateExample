package com.dreamshop.controller;

import com.dreamshop.dto.AddressDto;
import com.dreamshop.dto.CustomerDto;
import com.dreamshop.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {


	@Autowired
	CustomerService customerService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
	List<CustomerDto> getCustomers() {

		List<CustomerDto> customerList = null;
		try {
			
			customerList = customerService.getCustomerList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return customerList;
	}

	@RequestMapping(value = "/addresses", method = RequestMethod.GET)
	public @ResponseBody
	List<AddressDto> getCustomerAddresses() {

		List<AddressDto> customerList = null;
		try
		{
			customerList = customerService.getCustomerAddressesList();

		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return customerList;
	}

	@RequestMapping(value = "/cheat", method = RequestMethod.GET)
	public void cheat()
	{

		customerService.FillDb();
	}


}
