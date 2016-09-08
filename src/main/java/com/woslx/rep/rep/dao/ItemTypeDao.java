package com.woslx.rep.rep.dao;

import com.woslx.rep.rep.entity.ItemType;

import java.util.List;

public interface ItemTypeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ItemType record);

    int insertSelective(ItemType record);

    ItemType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ItemType record);

    int updateByPrimaryKey(ItemType record);

    List<ItemType> getAll();

    List<ItemType> getByName(String name);
}