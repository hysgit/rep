package com.woslx.rep.rep.controller;

import com.woslx.rep.common.ApiException;
import com.woslx.rep.common.ApiResult;
import com.woslx.rep.common.Constants;
import com.woslx.rep.rep.entity.Item;
import com.woslx.rep.rep.entity.param.ParamItem;
import com.woslx.rep.rep.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by hy on 9/3/16.
 */

@Controller
@RequestMapping("/item")
public class ItemController {

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);


    @Autowired
    private ItemService itemService;

    /**
     * 新增商品
     * @param paramItem
     * @return
     */
    @RequestMapping(value = "/create",
            consumes = "application/json",
            produces = "application/json;charset=utf-8")
    @ResponseBody
    public String create(@RequestBody ParamItem paramItem) {
        ApiResult<String> apiResult = new ApiResult<>(0, Constants.SUCCESS);

        try {
            itemService.insert(createItem(paramItem));
        } catch (Exception e) {
            apiResult.setCode(1);
            apiResult.setMessage(Constants.FAIL);
        }

        return apiResult.toString();
    }

    private Item createItem(ParamItem paramItem) {
        Item item = new Item();
        item.setNameId(paramItem.getNameId());
        item.setTypeId(paramItem.getTypeId());
        item.setCompany(paramItem.getCompany());
        item.setSerialNumber(paramItem.getSerialNumber());
        item.setSpecifications(paramItem.getSpecifications());
        item.setQuantityAll(0);
        item.setQuantityCurrent(0);
        item.setQuantityUse(0);
        item.setState(1);

        Date now = new Date();
        item.setCreateTime(now);
        item.setUpdateTime(now);
        return item;
    }

    /**
     * 根据id更新商品信息
     * @param paramItem
     * @return
     */
    @RequestMapping(value = "/update",
            consumes = "application/json",
            produces = "application/json;charset=utf-8")
    @ResponseBody
    public String update(@RequestBody ParamItem paramItem)
    {
        ApiResult<String> apiResult = new ApiResult<>(0, Constants.SUCCESS);
        Item item = itemService.getById(paramItem.getId());
        if(item == null)
        {
            throw new ApiException(1, "不存在的Item");
        }
        item.setNameId(paramItem.getNameId());
        item.setTypeId(paramItem.getTypeId());
        item.setCompany(paramItem.getCompany());
//        item.setSerialNumber(paramItem.getSerialNumber()); 商品编号不能改
        item.setSpecifications(paramItem.getSpecifications());
        item.setUpdateTime(new Date());

        itemService.update(item);

        return apiResult.toString();
    }

    /**
     * 根据id查询商品
     * @param paramItem
     * @return
     */
    @RequestMapping(value="/get/by/id",
            consumes = "application/json",
            produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getById(@RequestBody ParamItem paramItem)
    {
        ApiResult<Item> apiResult = new ApiResult<>(0, Constants.SUCCESS);
        Item item = itemService.getById(paramItem.getId());
        if(item == null)
        {
            throw new ApiException(1, "不存在的Item");
        }
        apiResult.setData(item);

        return apiResult.toString();
    }

    /**
     * 根据typeid查找商品
     * @param paramItem
     * @return
     */
    @RequestMapping(value="/get/by/typeid",
            consumes = "application/json",
            produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getByTypeId(@RequestBody ParamItem paramItem)
    {
        ApiResult<List<Item>> apiResult = new ApiResult<>(0, Constants.SUCCESS);
        List<Item> itemList = itemService.getByTypeId(paramItem.getTypeId());
        if(itemList == null)
        {
            throw new ApiException(1, "根据TypeId未找到Item");
        }
        if (itemList.size() == 0) {
            apiResult.setCode(2);
            apiResult.setMessage("查询结果为空");
        }
        else {
            apiResult.setData(itemList);
        }

        return apiResult.toString();
    }

    @RequestMapping(value="/get/by/nameid",
            consumes = "application/json",
            produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getByNameId(@RequestBody ParamItem paramItem)
    {
        ApiResult<List<Item>> apiResult = new ApiResult<>(0, Constants.SUCCESS);
        List<Item> itemList = itemService.getByNameId(paramItem.getNameId());
        if(itemList == null)
        {
            throw new ApiException(1, "根据NameId未找到Item");
        }

        if (itemList.size() == 0) {
            apiResult.setCode(2);
            apiResult.setMessage("查询结果为空");
        }
        else {
            apiResult.setData(itemList);
        }

        return apiResult.toString();
    }

    @RequestMapping(value="/get/all",
            consumes = "application/json",
            produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getAll()
    {
        ApiResult<List<Item>> apiResult = new ApiResult<>(0, Constants.SUCCESS);
        List<Item> itemList = itemService.getAll();
        if(itemList == null)
        {
            throw new ApiException(1, "未找到Item");
        }

        if (itemList.size() == 0) {
            apiResult.setCode(2);
            apiResult.setMessage("查询结果为空");
        }
        else {
            apiResult.setData(itemList);
        }

        return apiResult.toString();
    }
}
