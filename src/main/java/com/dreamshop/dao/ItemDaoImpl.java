package com.dreamshop.dao;


import com.dreamshop.dto.ItemDto;
import com.dreamshop.dto.OrderDto;
import com.dreamshop.helpers.DozerHelper;
import com.dreamshop.model.StockItem;
import org.dozer.DozerBeanMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

public class ItemDaoImpl implements ItemDao
{
    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    DozerBeanMapper mapper;

    Session session = null;
    Transaction tx = null;

    @Override
    public List<ItemDto> getStockItems() throws Exception {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        List<StockItem> items = session.createCriteria(StockItem.class).list();
        tx.commit();
        session.close();
        return DozerHelper.map(mapper, items, ItemDto.class);
    }

    @Override
    public List<ItemDto> getStockItems(Collection<String> stockKeepingUnits) throws Exception {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        List<StockItem> items =
                   session.createQuery(" from StockItem i where i.stockKeepingUnit in (:SKU)")
                        .setParameterList("SKU", stockKeepingUnits)
                        .list();

        List<ItemDto> result = DozerHelper.map(mapper, items, ItemDto.class);
        tx.commit();
        session.close();
        return  result;
    }

    @Override
    public ItemDto getStockItem(String stockKeepingUnit) throws Exception {
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        StockItem item =
                (StockItem) session.createQuery(" from StockItem i where i.stockKeepingUnit =:SKU")
                        .setParameter("SKU", stockKeepingUnit)
                        .uniqueResult();
        tx.commit();
        session.close();
        return mapper.map(item, ItemDto.class);
    }
}
