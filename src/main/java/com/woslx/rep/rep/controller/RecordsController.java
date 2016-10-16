package com.woslx.rep.rep.controller;

import com.woslx.rep.common.ApiException;
import com.woslx.rep.common.ApiResult;
import com.woslx.rep.common.BaseController;
import com.woslx.rep.common.Constants;
import com.woslx.rep.rep.entity.Item;
import com.woslx.rep.rep.entity.ItemName;
import com.woslx.rep.rep.entity.ItemType;
import com.woslx.rep.rep.entity.Records;
import com.woslx.rep.rep.entity.param.*;
import com.woslx.rep.rep.entity.vo.Opertion;
import com.woslx.rep.rep.entity.vo.OpertionMsg;
import com.woslx.rep.rep.entity.vo.RecordsVO;
import com.woslx.rep.rep.entity.vo.RecordsVOList;
import com.woslx.rep.rep.service.ItemNameService;
import com.woslx.rep.rep.service.ItemService;
import com.woslx.rep.rep.service.ItemTypeService;
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
import java.util.Calendar;
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

    @Autowired
    private ItemTypeService itemTypeService;

    @Autowired
    private ItemNameService itemNameService;

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
     * 新增一条出库记录
     *
     * @param paramRecordsOut
     * @return
     */
    @RequestMapping(value = "/create/out",
            consumes = "application/json",
            produces = "application/json;charset=utf-8")
    @ResponseBody
    @Transactional
    public String createOut(@RequestBody ParamRecordsOut paramRecordsOut) {
        ApiResult<String> apiResult = new ApiResult<>(0, Constants.SUCCESS);

        Integer itemOutType = paramRecordsOut.getItemOutType();  // 出库类型

        Date date = paramRecordsOut.getDate();     //日期
        if (date == null) {
            apiResult.setCode(1);
            apiResult.setMessage("未设置日期");
            return apiResult.toString();
        }

        List<Out> list = paramRecordsOut.getList();

        for (Out out : list) {
            Records records = new Records();
            records.setActionType(1);   // 出库
            records.setActionDetail(itemOutType);

            Item item = itemService.getById(out.getItemId());
            if (item == null) {
                throw new ApiException(1, "指定的商品不存在");
            }


            if (itemOutType == 101) {
                records.setDocterName(paramRecordsOut.getDocterName());
                records.setGentaiName(paramRecordsOut.getGentaiName());
                records.setPatientName(paramRecordsOut.getPatientName());
                records.setZhuyuanNo(paramRecordsOut.getZhuyuanNo());
                Integer operationId = recordsService.getMaxOperationId();
                records.setOperationId(operationId + 1);
                Integer price = out.getPrice();
                if (price != null) {
                    records.setPrice(price);
                    records.setPricePutIn(1);   //外部设定
                } else {
                    price = item.getPrice();
                    records.setPrice(price);
                    records.setPricePutIn(0);   //内设定
                }

                Integer priceAll = out.getPriceAll();
                if (priceAll != null) {
                    records.setAllPrice(priceAll);
                    records.setAllPricePutIn(1);   //外部设定
                } else {
                    records.setAllPrice((int) Math.round(price * out.getQuality() / 10.0));
                    records.setAllPricePutIn(0);   //内设定
                }
            }

            records.setTime(date);

            records.setSrcOrDst(out.getDst());


            records.setItemId(item.getId());
            records.setItemTypeId(item.getTypeId());
            records.setItemNameId(item.getNameId());

            int quality = out.getQuality();
            records.setQuantityBefore(item.getQuantityCurrent());       //保存出库前数量

            records.setQuantity(quality);
            item.setQuantityUse(item.getQuantityUse() + quality);       //修改出库数量
            if (item.getQuantityAll() - item.getQuantityUse() < 0) {
                throw new RuntimeException("出库数量大于库存");
            }
            item.setQuantityCurrent(item.getQuantityAll() - item.getQuantityUse());     //修改当前数量

            records.setQuantityAfter(item.getQuantityCurrent());        //保存出库后数量

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
     * @param queryOpertionCondition
     * @return
     */
    @RequestMapping(value = "/query",
            consumes = "application/json",
            produces = "application/json;charset=utf-8")
    @ResponseBody
    public String query(@RequestBody ParamQueryOpertionCondition queryOpertionCondition) {
        ApiResult<Opertion> apiResult = new ApiResult<>(0, Constants.SUCCESS);

        Opertion opertion = new Opertion();
        List<OpertionMsg> msgList = new ArrayList<>();
        opertion.setList(msgList);
        opertion.setSum(0);
        apiResult.setData(opertion);

        Integer queryType = queryOpertionCondition.getQueryType();
        if (queryType == 1)      //多条件
        {
            Calendar calendar = Calendar.getInstance();
            Date end = queryOpertionCondition.getEnd();
            Date start = queryOpertionCondition.getStart();
            calendar.setTime(start);
            calendar.set(Calendar.HOUR,0);
            start = calendar.getTime();
            calendar.setTime(end);
            calendar.set(Calendar.HOUR,0);
            calendar.add(Calendar.DATE,1);
            end = calendar.getTime();

            if (start == null || end == null) {
                apiResult.setCode(1);
                apiResult.setMessage("起止时间必须输入");
                return apiResult.toString();
            }

            if(start.getTime() > end.getTime())
            {
                apiResult.setCode(1);
                apiResult.setMessage("起始时间必须小于结束时间");
                return apiResult.toString();
            }

            List<String> gentaiList = queryOpertionCondition.getGentaiList();
            List<String> docNameList = queryOpertionCondition.getDocNameList();
            List<Integer> typeList = queryOpertionCondition.getTypeList();

            //1. 查询到所有在合适日期内的所有手术id,同时符合医生，跟台人
            List<Integer> zhuyuanNoList = recordsService.queryOperation(docNameList, gentaiList, typeList, start, end);

            //2. 根据住院号查询,查询一个，保存一个
            if (zhuyuanNoList.size() == 0) {
                apiResult.setCode(1);
                apiResult.setMessage("未查找到记录");
                return apiResult.toString();
            } else {
                for (Integer operationId : zhuyuanNoList) {

                    OpertionMsg opertionMsg = getOpertionMsgByOperationId(operationId);
                    msgList.add(opertionMsg);
                    if (opertionMsg == null) {
                        logger.error("手术ID：{}未找到任何记录", operationId);
                        apiResult.setCode(1);
                        apiResult.setMessage("手术ID："+operationId+"未找到任何记录");
                        return apiResult.toString();
                    } else {
                        opertion.setSum(opertion.getSum() + opertionMsg.getTotal());
                    }
                }
            }
        } else    //按住院号
        {
            String zhuyuanNO = queryOpertionCondition.getZhuyuanNO();
            List<Integer> zhuyuanNoList = recordsService.getOperationIdByZhuyuanNo(zhuyuanNO);

            if (zhuyuanNoList.size() == 0) {
                apiResult.setCode(1);
                apiResult.setMessage("未查找到记录");
                return apiResult.toString();
            } else {
                for (Integer operationId : zhuyuanNoList) {

                    OpertionMsg opertionMsg = getOpertionMsgByOperationId(operationId);
                    msgList.add(opertionMsg);
                    if (opertionMsg == null) {
                        logger.error("手术ID：{}未找到任何记录", operationId);
                        apiResult.setCode(1);
                        apiResult.setMessage("手术ID："+operationId+"未找到任何记录");
                        return apiResult.toString();
                    } else {
                        opertion.setSum(opertion.getSum() + opertionMsg.getTotal());
                    }
                }
            }
        }

        return apiResult.toString();
    }

    private OpertionMsg getOpertionMsgByOperationId(Integer operationId) {
        List<Records> recordsList = recordsService.getRecordsByOperationId(operationId);
        OpertionMsg opertionMsg = null;
        if (recordsList.size() == 0) {
            return null;
        } else {
            Records records = recordsList.get(0);
            List<OpertionMsg> msgList = new ArrayList<>();

            opertionMsg = new OpertionMsg();
            opertionMsg.setDocName(records.getDocterName());
            opertionMsg.setGentai(records.getGentaiName());
            opertionMsg.setZhuyuanNo(records.getZhuyuanNo());
            opertionMsg.setPatientName(records.getPatientName());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
            opertionMsg.setDate(sdf.format(records.getTime()));
            msgList.add(opertionMsg);

            List<RecordsVO> recordsVOList = new ArrayList<>();
            opertionMsg.setRecordsVOList(recordsVOList);
            Integer total = 0;
            for (Records rtemp : recordsList) {
                RecordsVO vo = new RecordsVO();
                Integer itemTypeId = rtemp.getItemTypeId();
                Integer itemNameId = rtemp.getItemNameId();

                ItemName itemName = itemNameService.getById(itemNameId);
                ItemType itemType = itemTypeService.getById(itemTypeId);
                Item item = itemService.getById(rtemp.getItemId());
                vo.setItemType(itemType.getName());
                vo.setItemName(itemName.getName());
                vo.setQuality(rtemp.getQuantity() / 10.0);
                vo.setPrice(rtemp.getPrice());
                vo.setPricePutIn(rtemp.getPricePutIn());
                vo.setAllPrice(rtemp.getAllPrice());
                vo.setAllPricePutIn(rtemp.getAllPricePutIn());
                vo.setItemSpec(item.getSpecifications());
                vo.setSn(item.getSerialNumber());
                total = total + rtemp.getAllPrice();

                recordsVOList.add(vo);
            }
            opertionMsg.setTotal(total);
        }

        return opertionMsg;
    }


    /**
     * 查询记录
     * 根据编号查询
     *
     * @param paramRecordsQueryCondition
     * @return
     */
    @RequestMapping(value = "/query/by/sn",
            consumes = "application/json",
            produces = "application/json;charset=utf-8")
    @ResponseBody
    public String queryBySN(@RequestBody ParamRecordsQueryCondition paramRecordsQueryCondition) {
        ApiResult<RecordsVOList> apiResult = new ApiResult<>(0, Constants.SUCCESS);
        String sn = paramRecordsQueryCondition.getSn();
        Item item = itemService.getBySerialNumber(sn);

        if (item == null) {
            apiResult.setCode(1);
            apiResult.setMessage("未查找到商品");
            return apiResult.toString();
        }

        List<Records> list = recordsService.getRecordsByItemId(item.getId());
        if (list.size() == 0) {
            apiResult.setCode(1);
            apiResult.setMessage("未查找到进出库数据");
            return apiResult.toString();
        }


        RecordsVOList recordsVOList = new RecordsVOList();

        ItemName itemName = itemNameService.getById(item.getNameId());

        ItemType itemType = itemTypeService.getById(item.getTypeId());

        recordsVOList.setTypeName(itemType.getName());
        recordsVOList.setItemName(itemName.getName());
        recordsVOList.setSn(sn);
        recordsVOList.setSpec(item.getSpecifications());
        recordsVOList.setAllIn(item.getQuantityAll() / 10.0);
        recordsVOList.setAllOut(item.getQuantityUse() / 10.0);
        recordsVOList.setCur(item.getQuantityCurrent() / 10.0);
        recordsVOList.setId(item.getId());
        List<RecordsVO> list2 = new ArrayList<>();
        recordsVOList.setList(list2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        for (Records records : list) {
            RecordsVO vo = new RecordsVO();
            vo.setDate(sdf.format(records.getTime()));
            vo.setAfter(records.getQuantityAfter() / 10.0);
            vo.setBefore(records.getQuantityBefore() / 10.0);
            vo.setQuality(records.getQuantity() / 10.0);
            vo.setDocName(records.getDocterName());
            vo.setGentai(records.getGentaiName());
            Integer actionType = records.getActionType();
            if (actionType == 1) {
                vo.setInOutType("出库");
            } else {
                vo.setInOutType("入库");
            }
            vo.setTransn(records.getTransactionalNumber());
            list2.add(vo);
        }
        apiResult.setData(recordsVOList);

        return apiResult.toString();
    }
}
