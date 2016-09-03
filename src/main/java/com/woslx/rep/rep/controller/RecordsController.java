package com.woslx.rep.rep.controller;

import com.woslx.rep.common.ApiException;
import com.woslx.rep.common.ApiResult;
import com.woslx.rep.common.Constants;
import com.woslx.rep.rep.entity.Records;
import com.woslx.rep.rep.entity.param.ParamRecords;
import com.woslx.rep.rep.service.RecordsService;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by hy on 9/4/16.
 */
@Controller
@RequestMapping("/records")
public class RecordsController {

    private static final Logger logger = LoggerFactory.getLogger(RecordsController.class);

    @Autowired
    private RecordsService recordsService;

    /**
     * 新增一条出入库记录
     * @param paramRecords
     * @return
     */
    @RequestMapping(value = "/create",
            consumes = "application/json",
            produces = "application/json;charset=utf-8")
    @ResponseBody
    public String create(@RequestBody ParamRecords paramRecords) {
        ApiResult<String> apiResult = new ApiResult<>(0, Constants.SUCCESS);

        Records records = new Records();
        try {
            paramRecords.setId(null);
            BeanUtils.copyProperties(records, paramRecords);
        } catch (Exception e) {
            logger.error("复制属性发生异常,",e);
            throw new ApiException(1, e.getMessage());
        }
        records.setState(1);
        Date now = new Date();
        records.setCreateTime(now);
        records.setUpdateTime(now);
        recordsService.insert(records);

        return apiResult.toString();
    }

}
