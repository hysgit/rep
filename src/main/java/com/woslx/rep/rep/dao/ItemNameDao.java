package com.woslx.rep.rep.dao;

import com.woslx.rep.rep.entity.ItemName;

import java.util.List;

public interface ItemNameDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ItemName record);

    int insertSelective(ItemName record);

    ItemName selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ItemName record);

    int updateByPrimaryKey(ItemName record);

    List<ItemName> getAll();

    List<ItemName> getByTypeId(Integer typeId);

    ItemName getByName(String name);
}