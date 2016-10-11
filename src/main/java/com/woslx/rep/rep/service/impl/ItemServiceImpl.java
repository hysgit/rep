package com.woslx.rep.rep.service.impl;

import com.woslx.rep.rep.dao.ItemDao;
import com.woslx.rep.rep.entity.Item;
import com.woslx.rep.rep.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hy on 9/3/16.
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Override
    public void insert(Item item) {
        itemDao.insertSelective(item);
    }

    @Override
    public Item getById(Integer id) {
        return itemDao.selectByPrimaryKey(id);
    }

    @Override
    public void update(Item item) {
        itemDao.updateByPrimaryKeySelective(item);
    }

    @Override
    public List<Item> getByTypeId(Integer typeId) {
        return itemDao.selectByTypeId(typeId);
    }

    @Override
    public List<Item> getByNameId(Integer nameId) {
        return itemDao.selectByNameId(nameId);
    }

    @Override
    public List<Item> getAll() {
        return itemDao.getAll();
    }

    @Override
    public Item getBySerialNumber(String serialNumber) {
        return itemDao.getBySerialNumber(serialNumber);
    }
}
