package com.woslx.rep.rep.dao;

import com.woslx.rep.rep.entity.Records;

public interface RecordsDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Records record);

    int insertSelective(Records record);

    Records selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Records record);

    int updateByPrimaryKey(Records record);
}