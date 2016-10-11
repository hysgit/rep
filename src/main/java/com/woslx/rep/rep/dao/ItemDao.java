package com.woslx.rep.rep.dao;

import com.woslx.rep.rep.entity.Item;

import java.util.List;

public interface ItemDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Item record);

    int insertSelective(Item record);

    Item selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);

    List<Item> selectByTypeId(Integer typeId);

    List<Item> selectByNameId(Integer nameId);

    List<Item> getAll();

    Item getBySerialNumber(String serialNumber);
}