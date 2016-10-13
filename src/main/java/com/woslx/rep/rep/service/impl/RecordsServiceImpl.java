package com.woslx.rep.rep.service.impl;

import com.woslx.rep.rep.dao.RecordsDao;
import com.woslx.rep.rep.entity.Records;
import com.woslx.rep.rep.entity.RecordsQueryCondition;
import com.woslx.rep.rep.entity.param.ParamRecordsQueryCondition;
import com.woslx.rep.rep.service.RecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by hy on 9/4/16.
 */
@Service
public class RecordsServiceImpl implements RecordsService {

    @Autowired
    private RecordsDao recordsDao;

    @Override
    public void insert(Records records) {
        recordsDao.insertSelective(records);
    }

    @Override
    public Records getById(Integer id) {
        return recordsDao.selectByPrimaryKey(id);
    }

    @Override
    public List<Records> getBySettedConditon(RecordsQueryCondition condition) {
        return recordsDao.getBySettedCondition(condition);
    }

    @Override
    public void delete(Records records) {
        records.setState(0);
        records.setUpdateTime(new Date());
        recordsDao.updateByPrimaryKey(records);
    }

    @Override
    public List<Records> getByTransactionalNumber(String transactionalNumber) {
        return recordsDao.getByTransactionalNumber(transactionalNumber);
    }

    @Override
    public List<Records> getRecordsByItemId(Integer itemId) {
        return recordsDao.getRecordsByItemId(itemId);
    }
}
