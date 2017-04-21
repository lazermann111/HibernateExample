package com.dreamshop.dao;

import com.dreamshop.dto.ItemDto;
import com.dreamshop.dto.OrderDto;
import com.dreamshop.helpers.DozerHelper;
import com.dreamshop.model.Customer;
import com.dreamshop.model.Order;
import com.dreamshop.model.StockItem;
import org.dozer.DozerBeanMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class OrderDaoImpl implements OrderDao {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DozerBeanMapper mapper;

	Session session = null;
	Transaction tx = null;

	@Override
	@Transactional
	public OrderDto addOrder(OrderDto order, List<ItemDto> itemsToUpdate) throws Exception {

		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		Order orderEntity = mapper.map(order, Order.class);
		session.save(orderEntity);

		for (ItemDto item : itemsToUpdate)
		{
			StockItem itemEntity = mapper.map(item, StockItem.class);

			if(itemEntity.getAmount() > 0)
			{
				session.update(itemEntity);
			}

			else
			{
				session.delete(itemEntity);
			}

		}
		Customer customer = orderEntity.getShippingAddress().getCustomer();
		session.update(customer);


		order = mapper.map(orderEntity, OrderDto.class);
		tx.commit();
		session.close();

		return order;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderDto> getOrderList() throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		List<Order> entities = session.createCriteria(Order.class).list();

		List<OrderDto> result = DozerHelper.map(mapper, entities, OrderDto.class);

		tx.commit();
		session.close();
		return result;
	}
	


}
