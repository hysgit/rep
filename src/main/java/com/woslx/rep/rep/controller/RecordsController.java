package com.woslx.rep.rep.controller;

import com.woslx.rep.common.ApiException;
import com.woslx.rep.common.ApiResult;
import com.woslx.rep.common.BaseController;
import com.woslx.rep.common.Constants;
import com.woslx.rep.rep.entity.Item;
import com.woslx.rep.rep.entity.Records;
import com.woslx.rep.rep.entity.RecordsQueryCondition;
import com.woslx.rep.rep.entity.param.ParamRecords;
import com.woslx.rep.rep.entity.param.ParamRecordsQueryCondition;
import com.woslx.rep.rep.entity.vo.RecordsVOList;
import com.woslx.rep.rep.service.ItemService;
import com.woslx.rep.rep.service.RecordsService;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hy on 9/4/16.
 */
@Controller
@RequestMapping("/records")
public class RecordsController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(RecordsController.class);

    @Autowired
    private RecordsService recordsService;

    @Autowired
    private ItemService itemService;

    /**
     * 新增一条出入库记录
     *
     * @param paramRecords
     * @return
     */
    @RequestMapping(value = "/create",
            consumes = "application/json",
            produces = "application/json;charset=utf-8")
    @ResponseBody
    @Transactional
    public String create(@RequestBody ParamRecords paramRecords) {
        ApiResult<String> apiResult = new ApiResult<>(0, Constants.SUCCESS);

        Records records = new Records();
        try {
            paramRecords.setId(null);
            BeanUtils.copyProperties(records, paramRecords);
        } catch (Exception e) {
            logger.error("复制属性发生异常,", e);
            throw new ApiException(1, e.getMessage());
        }

        Item item = itemService.getById(paramRecords.getItemId());
        if (item == null) {
            throw new ApiException(1, "指定的商品不存在");
        }
        records.setItemNameId(item.getNameId());
        records.setItemTypeId(item.getTypeId());

        //处理数量问题 数量扩大10倍
        Integer quantity = (int) Math.round((Double.parseDouble(paramRecords.getQuantity()) * 10));
        records.setQuantity(quantity);
        if (1 == records.getActionType())        //1 出库
        {
            Integer quantityCurrent = item.getQuantityCurrent();
            if ((quantityCurrent == null) || (quantityCurrent < quantity)) {
                throw new ApiException(1, "库存少于当前出库数,不能出库");
            }
            Integer quantityUse = item.getQuantityUse();        //已经使用
            if (quantityUse == null) {
                item.setQuantityUse(quantity);
            } else {
                item.setQuantityUse(item.getQuantityUse() + quantity);
            }
        } else {      // 2入库
            Integer quantityAll = item.getQuantityAll();
            if (quantityAll == null) {
                item.setQuantityAll(quantity);
            } else {
                item.setQuantityAll(item.getQuantityAll() + quantity);
            }
        }
        if(item.getQuantityUse() == null) {
            item.setQuantityUse(0);
        }
        item.setQuantityCurrent(item.getQuantityAll() - item.getQuantityUse());
        itemService.update(item);//更新商品数量

        records.setState(1);
        Date now = new Date();
        records.setCreateTime(now);
        records.setUpdateTime(now);

        recordsService.insert(records); //保存记录

        return apiResult.toString();
    }

    /**
     * 根据id删除出库记录
     * @param paramRecords
     * @return
     */
    @RequestMapping(value = "/delete",
            consumes = "application/json",
            produces = "application/json;charset=utf-8")
    @ResponseBody
    @Transactional
    public String deleteById(@RequestBody ParamRecords paramRecords) {
        ApiResult<String> apiResult = new ApiResult<>(0, Constants.SUCCESS);
        Records records = recordsService.getById(paramRecords.getId());
        if (records == null) {
            logger.error("未找到记录, id: {}",paramRecords.getId());
            throw new ApiException(1, "未找到记录");
        }

        Item item = itemService.getById(records.getItemId());
        if (item == null) {
            logger.error("根据记录中的商品id未找到商品, record id: {}, item id: {}",paramRecords.getId(),records.getItemId());
            throw new ApiException(1, "根据记录中的商品id未找到商品");
        }
        Integer quantity = records.getQuantity();

        Integer actionType = records.getActionType();

        if(actionType == 1) //出库
        {
            item.setQuantityUse(item.getQuantityUse()-quantity);
        }
        else {  //入库
            item.setQuantityAll(item.getQuantityAll()-quantity);
        }
        item.setQuantityCurrent(item.getQuantityAll()-item.getQuantityUse());
        itemService.update(item);
        recordsService.delete(records);
        return apiResult.toString();
    }

    /**
     * 查询记录
     * 在同一个接口里实现多种查询功能
     *
     * @param paramRecordsQueryCondition
     * @return
     */
    @RequestMapping(value = "/query",
            consumes = "application/json",
            produces = "application/json;charset=utf-8")
    @ResponseBody
    public String query(@RequestBody ParamRecordsQueryCondition paramRecordsQueryCondition) {
        ApiResult<RecordsVOList> apiResult = new ApiResult<>(0, Constants.SUCCESS);
        RecordsVOList recordsVOList = new RecordsVOList();


        Integer id = paramRecordsQueryCondition.getId();
        if (id != null) {
            Records records = recordsService.getById(id);
            if (records != null) {
                List<Records> list = new ArrayList<>();
                recordsVOList.setList(list);
                recordsVOList.setCnt(1);
                list.add(records);
            } else {
                apiResult.setCode(1);
                apiResult.setMessage("根据id: " + id + "未找到纪录");
                apiResult.setData(null);
            }
        } else {
            RecordsQueryCondition recordsQueryCondition = new RecordsQueryCondition();
            recordsQueryCondition.setActionType(paramRecordsQueryCondition.getActionType());
            recordsQueryCondition.setItemId(paramRecordsQueryCondition.getItemId());
            recordsQueryCondition.setItemNameId(paramRecordsQueryCondition.getItemNameId());
            recordsQueryCondition.setItemTypeId(paramRecordsQueryCondition.getItemTypeId());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            recordsQueryCondition.setStartTime(sdf.format(paramRecordsQueryCondition.getStartTime()));
            recordsQueryCondition.setEndTime(sdf.format(paramRecordsQueryCondition.getEndTime()));

            List<Records> list = recordsService.getBySettedConditon(recordsQueryCondition);
            recordsVOList.setList(list);
            recordsVOList.setCnt(list == null ? 0 : list.size());
        }

        return apiResult.toString();
    }
}
