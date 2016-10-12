package com.woslx.rep.rep.controller;

import com.woslx.rep.common.ApiException;
import com.woslx.rep.common.ApiResult;
import com.woslx.rep.common.BaseController;
import com.woslx.rep.common.Constants;
import com.woslx.rep.rep.entity.Item;
import com.woslx.rep.rep.entity.Records;
import com.woslx.rep.rep.entity.RecordsQueryCondition;
import com.woslx.rep.rep.entity.param.ParamRecordsQueryCondition;
import com.woslx.rep.rep.entity.param.ParamRecordsRuku;
import com.woslx.rep.rep.entity.param.Ruku;
import com.woslx.rep.rep.entity.vo.RecordsVOList;
import com.woslx.rep.rep.service.ItemService;
import com.woslx.rep.rep.service.RecordsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
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
     * 新增一条入库记录
     *
     * @param paramRecordsRuku
     * @return
     */
    @RequestMapping(value = "/create/ruku",
            consumes = "application/json",
            produces = "application/json;charset=utf-8")
    @ResponseBody
    @Transactional
    public String create(@RequestBody ParamRecordsRuku paramRecordsRuku) {
        ApiResult<String> apiResult = new ApiResult<>(0, Constants.SUCCESS);

        Integer itemInType = paramRecordsRuku.getItemInType();  // 入库类型
        String transactionalNumber = paramRecordsRuku.getTransactionalNumber();//单号

        Date date = paramRecordsRuku.getDate();     //日期
        if (date == null) {
            apiResult.setCode(1);
            apiResult.setMessage("未设置日期");
            return apiResult.toString();
        }

        if (StringUtils.isEmpty(transactionalNumber)) {
            apiResult.setCode(1);
            apiResult.setMessage("流水单号必填");
            return apiResult.toString();
        }

        List<Ruku> list = paramRecordsRuku.getList();

        //判断该单号是否已经入库
//        List<Records> recordsList = recordsService.getByTransactionalNumber(transactionalNumber);
//
//        if (recordsList != null && recordsList.size() > 0) {
//            apiResult.setCode(1);
//            apiResult.setMessage("该单号已经入库");
//            return apiResult.toString();
//        }

        for (Ruku ruku : list) {
            Records records = new Records();
            records.setActionType(2);   // 入库
            records.setActionDetail(itemInType);
            records.setTransactionalNumber(transactionalNumber);
            records.setTime(date);

            records.setSrcOrDst(ruku.getSrc());

            Item item = itemService.getById(ruku.getItemId());
            if (item == null) {
                throw new ApiException(1, "指定的商品不存在");
            }
            records.setItemId(item.getId());
            records.setItemTypeId(item.getTypeId());
            records.setItemNameId(item.getNameId());

            int quality = ruku.getQuality();
            records.setQuantityBefore(item.getQuantityCurrent());
            records.setQuantity(quality);

            item.setQuantityAll(item.getQuantityAll() + quality);
            item.setQuantityCurrent(item.getQuantityAll() - item.getQuantityUse());

            records.setQuantityAfter(item.getQuantityCurrent());

            records.setState(1);

            Date date2 = new Date();
            records.setCreateTime(date2);
            records.setUpdateTime(date2);


            recordsService.insert(records); //保存记录
            itemService.update(item);
        }

        return apiResult.toString();
    }

    /**
     * 根据id删除出库记录
     * @param paramRecordsRuku
     * @return
     */
//    @RequestMapping(value = "/delete",
//            consumes = "application/json",
//            produces = "application/json;charset=utf-8")
//    @ResponseBody
//    @Transactional
//    public String deleteById(@RequestBody ParamRecordsRuku paramRecordsRuku) {
//        ApiResult<String> apiResult = new ApiResult<>(0, Constants.SUCCESS);
//        Records records = recordsService.getById(paramRecordsRuku.getId());
//        if (records == null) {
//            logger.error("未找到记录, id: {}", paramRecordsRuku.getId());
//            throw new ApiException(1, "未找到记录");
//        }
//
//        Item item = itemService.getById(records.getItemId());
//        if (item == null) {
//            logger.error("根据记录中的商品id未找到商品, record id: {}, item id: {}", paramRecordsRuku.getId(),records.getItemId());
//            throw new ApiException(1, "根据记录中的商品id未找到商品");
//        }
//        Integer quantity = records.getQuantity();
//
//        Integer actionType = records.getActionType();
//
//        if(actionType == 1) //出库
//        {
//            item.setQuantityUse(item.getQuantityUse()-quantity);
//        }
//        else {  //入库
//            item.setQuantityAll(item.getQuantityAll()-quantity);
//        }
//        item.setQuantityCurrent(item.getQuantityAll()-item.getQuantityUse());
//        itemService.update(item);
//        recordsService.delete(records);
//        return apiResult.toString();
//    }

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
