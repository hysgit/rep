package com.woslx.rep.rep.service.impl;

import com.woslx.rep.rep.dao.RecordsDao;
import com.woslx.rep.rep.entity.Records;
import com.woslx.rep.rep.service.RecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
