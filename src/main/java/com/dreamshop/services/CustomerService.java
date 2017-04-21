package com.dreamshop.services;


import com.dreamshop.dto.AddressDto;
import com.dreamshop.dto.CustomerDto;

import java.util.List;

public interface CustomerService
{
    public List<AddressDto> getCustomerAddressesList() throws Exception;
    public List<CustomerDto> getCustomerList() throws Exception;
    public void FillDb();
}
