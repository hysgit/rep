package com.woslx.rep.rep.dao;

import com.woslx.rep.rep.entity.Records;
import com.woslx.rep.rep.entity.RecordsQueryCondition;

import java.util.List;

public interface RecordsDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Records record);

    int insertSelective(Records record);

    Records selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Records record);

    int updateByPrimaryKey(Records record);

    List<Records> getBySettedCondition(RecordsQueryCondition condition);

    List<Records> getByTransactionalNumber(String transactionalNumber);

    List<Records> getRecordsByItemId(Integer itemId);
}