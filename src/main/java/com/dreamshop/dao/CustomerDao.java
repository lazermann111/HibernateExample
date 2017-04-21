package com.dreamshop.dao;


import com.dreamshop.dto.AddressDto;
import com.dreamshop.dto.CustomerDto;
import com.dreamshop.model.Customer;

import java.util.List;

public interface CustomerDao
{
    public void addCustomer(Customer customer) throws Exception;
    public List<CustomerDto> getCustomerList() throws Exception;
    public AddressDto getAddress(long id) throws Exception;
    public List<AddressDto> getCustomerAddressesList() throws Exception;


    public void FillDb();
}
