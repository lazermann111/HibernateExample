package com.dreamshop.dao;


import com.dreamshop.dto.AddressDto;
import com.dreamshop.dto.CustomerDto;
import com.dreamshop.helpers.DozerHelper;
import com.dreamshop.model.*;
import org.dozer.DozerBeanMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {


    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    DozerBeanMapper mapper;

    Session session = null;
    Transaction tx = null;

    @Override
    public void addCustomer(Customer customer) throws Exception {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        session.save(customer);
        tx.commit();
        session.close();
    }

    @Override
    public List<CustomerDto> getCustomerList() throws Exception {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();

        List<Customer> customerList = session.createCriteria(Customer.class).list();
        List<CustomerDto> result = DozerHelper.map(mapper, customerList, CustomerDto.class);

        tx.commit();
        session.close();


        return result;
    }

    @Override
    public AddressDto getAddress(long id) throws Exception {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        Address a = (Address) session.load(Address.class, id);
        AddressDto result = mapper.map(a, AddressDto.class);

        tx.commit();
        session.close();
        return result;
    }

    @Override
    public List<AddressDto> getCustomerAddressesList() throws Exception {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        List<Address> addresses = session.createCriteria(Address.class).list();

        List<AddressDto> result = DozerHelper.map(mapper, addresses, AddressDto.class);
        tx.commit();
        session.close();
        return result;
    }

    @Override
    public void FillDb()
    {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();

        Customer c = new Customer();
        c.setCredit(1000);
        c.setEmail("me@gmail.com");
        c.setFirstName("John");
        c.setLastName("Doe");
        session.save(c);


        Address a = new Address();
        a.setCustomer(c);
        a.setPostalCode("72030");
        a.setFlat("15");
        a.setHouse("20B");
        a.setStreet("Main st.");
        session.save(a);

        StockItem s = new StockItem();
        s.setAmount(100);
        s.setPrice(5);
        s.setStockKeepingUnit("100-R2");
        s.setName("Soap");

        StockItem s2 = new StockItem();
        s2.setAmount(100);
        s2.setPrice(25);
        s2.setStockKeepingUnit("100-HH");
        s2.setName("Coca-Cola");


        session.save(s);
        session.save(s2);

        OrderedItem ordered = new OrderedItem();
        ordered.setAmount(1);
        ordered.setPrice(5);
        ordered.setStockKeepingUnit("100-R2");
        ordered.setName("Soap");
        session.save(ordered);


        List<OrderedItem> orders = new ArrayList<OrderedItem>();
        orders.add(ordered);
        Order o = new Order();
        o.setShippingAddress(a);
        o.setTotalPrice(25);
        o.setItems(orders);
        session.save(o);


        tx.commit();
        session.close();
    }
}
