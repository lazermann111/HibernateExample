package com.dreamshop.services;


import com.dreamshop.dao.CustomerDao;
import com.dreamshop.dto.AddressDto;
import com.dreamshop.dto.CustomerDto;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomerServiceImpl implements CustomerService
{

    @Autowired
    CustomerDao customerDao;


    @Autowired
    DozerBeanMapper dozerMapper;

    @Override
    public List<AddressDto> getCustomerAddressesList() throws Exception {
        return  customerDao.getCustomerAddressesList();
    }

    @Override
    public List<CustomerDto> getCustomerList() throws Exception {
        return customerDao.getCustomerList();
    }

    @Override
    public void FillDb() {
        customerDao.FillDb();
    }
}
