package com.woslx.rep.rep.service.impl;

import com.woslx.rep.rep.dao.ItemNameDao;
import com.woslx.rep.rep.entity.ItemName;
import com.woslx.rep.rep.service.ItemNameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hy on 9/3/16.
 */
@Service
public class ItemNameServiceImpl implements ItemNameService {

    private static final Logger logger = LoggerFactory.getLogger(ItemNameServiceImpl.class);

    @Autowired
    private ItemNameDao itemNameDao;

    @Override
    public void insert(ItemName itemName) {
        itemNameDao.insertSelective(itemName);
    }

    @Override
    public ItemName getById(Integer id) {
        return itemNameDao.selectByPrimaryKey(id);
    }

    @Override
    public void update(ItemName itemName) {
        itemNameDao.updateByPrimaryKeySelective(itemName);
    }

    @Override
    public List<ItemName> getAll() {
        return itemNameDao.getAll();
    }

    @Override
    public List<ItemName> getByTypeId(Integer typeId) {
        return itemNameDao.getByTypeId(typeId);
    }
}
