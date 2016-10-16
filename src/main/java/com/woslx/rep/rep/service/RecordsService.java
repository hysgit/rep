package com.woslx.rep.rep.service;

import com.woslx.rep.rep.entity.Records;
import com.woslx.rep.rep.entity.RecordsQueryCondition;
import com.woslx.rep.rep.entity.param.ParamRecordsQueryCondition;

import java.util.Date;
import java.util.List;

/**
 * Created by hy on 9/4/16.
 */
public interface RecordsService {
    void insert(Records records);

    Records getById(Integer id);

    List<Records> getBySettedConditon(RecordsQueryCondition condition);

    void delete(Records records);

    List<Records> getByTransactionalNumber(String transactionalNumber);

    List<Records> getRecordsByItemId(Integer id);

    List<Records> getRecordsByOperationId(Integer operationId);

    List<Integer> queryOperation(List<String> docNameList, List<String> gentaiList, Date start, Date end);

    List<Integer> getOperationIdByZhuyuanNo(String zhuyuanNO);
}
