package com.woslx.rep.rep.dao;

import com.woslx.rep.rep.entity.ItemName;

public interface ItemNameDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ItemName record);

    int insertSelective(ItemName record);

    ItemName selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ItemName record);

    int updateByPrimaryKey(ItemName record);
}