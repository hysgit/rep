package com.woslx.rep.rep.service.impl;

import com.woslx.rep.rep.dao.ItemTypeDao;
import com.woslx.rep.rep.entity.ItemType;
import com.woslx.rep.rep.service.ItemTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hy on 9/2/16.
 */

@Service
public class ItemTypeServiceImpl implements ItemTypeService {

    private static final Logger logger = LoggerFactory.getLogger(ItemTypeServiceImpl.class);

    @Autowired
    private ItemTypeDao itemTypeDao;

    @Override
    public void insert(ItemType itemType) {
        itemTypeDao.insertSelective(itemType);
    }

    @Override
    public ItemType getById(Integer id) {

        return itemTypeDao.selectByPrimaryKey(id);
    }

    @Override
    public void update(ItemType itemType) {
        itemTypeDao.updateByPrimaryKeySelective(itemType);
    }

    @Override
    public List<ItemType> getAll() {
        return itemTypeDao.getAll();
    }

    @Override
    public List<ItemType> getByName(String name) {
        return itemTypeDao.getByName(name);
    }
}
