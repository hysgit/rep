package com.woslx.rep.rep.controller;

import com.woslx.rep.common.ApiException;
import com.woslx.rep.common.ApiResult;
import com.woslx.rep.common.Constants;
import com.woslx.rep.rep.entity.ItemName;
import com.woslx.rep.rep.entity.param.ParamItemName;
import com.woslx.rep.rep.service.ItemNameService;
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
@RequestMapping("/item/name")
public class ItemNameController {

    private static final Logger logger = LoggerFactory.getLogger(ItemNameController.class);

    @Autowired
    private ItemNameService itemNameService;


    /**
     * 新增商品名称
     * @param paramItemName
     * @return
     */
    @RequestMapping(value = "/create",
            consumes = "application/json",
            produces = "application/json;charset=utf-8")
    @ResponseBody
    public String create(@RequestBody ParamItemName paramItemName) {
        ApiResult<String> apiResult = new ApiResult<>(0, Constants.SUCCESS);

        ItemName itemName = new ItemName();
        itemName.setName(paramItemName.getName());
        itemName.setTypeId(paramItemName.getTypeId());
        Date now = new Date();
        itemName.setCreateTime(now);
        itemName.setUpdateTime(now);

        try {
            itemNameService.insert(itemName);
        } catch (Exception e) {
            apiResult.setCode(1);
            apiResult.setMessage(Constants.FAIL);
        }

        return apiResult.toString();
    }

    /**
     * 根据id修改商品名称
     * @param paramItemName
     * @return
     */
    @RequestMapping(value = "/update",
            consumes = "application/json",
            produces = "application/json;charset=utf-8")
    @ResponseBody
    public String update(@RequestBody ParamItemName paramItemName)
    {
        ApiResult<String> apiResult = new ApiResult<>(0, Constants.SUCCESS);
        ItemName itemName = itemNameService.getById(paramItemName.getId());
        if(itemName == null)
        {
            throw new ApiException(1, "不存在的ItemType");
        }
        itemName.setTypeId(paramItemName.getTypeId());
        itemName.setName(paramItemName.getName());
        itemName.setUpdateTime(new Date());


        itemNameService.update(itemName);

        return apiResult.toString();
    }

    /**
     * 根据id查询商品名称信息
     * @param paramItemName
     * @return
     */
    @RequestMapping(value="/get/by/id",
            consumes = "application/json",
            produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getById(@RequestBody ParamItemName paramItemName)
    {
        ApiResult<ItemName> apiResult = new ApiResult<>(0, Constants.SUCCESS);
        ItemName itemName = itemNameService.getById(paramItemName.getId());
        if(itemName == null)
        {
            throw new ApiException(1, "不存在的ItemType");
        }
        apiResult.setData(itemName);

        return apiResult.toString();
    }

    /**
     * 根据商品类型id查询商品名称
     * @param paramItemName
     * @return
     */
    @RequestMapping(value="/get/by/typeid",
            consumes = "application/json",
            produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getByTypeId(@RequestBody ParamItemName paramItemName)
    {
        ApiResult<List<ItemName>> apiResult = new ApiResult<>(0, Constants.SUCCESS);
        List<ItemName> itemNameList = itemNameService.getByTypeId(paramItemName.getTypeId());
        if(itemNameList == null)
        {
            throw new ApiException(1, "不存在的ItemType");
        }
        if (itemNameList.size() == 0) {
            apiResult.setCode(1000);
            apiResult.setMessage("该类型下不存在商品名称");
        }
        else {
            apiResult.setData(itemNameList);
        }

        return apiResult.toString();
    }


    /**
     * 查询所有的商品名称
     * @return
     */
    @RequestMapping(value = "/get/all",
            consumes = "application/json",
            produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getAll()
    {
        ApiResult<List<ItemName>> apiResult = new ApiResult<>(0, Constants.SUCCESS);
        List<ItemName> list = itemNameService.getAll();

        if(list.size() == 0)
        {
            apiResult.setCode(2);
            apiResult.setMessage("查询结果为空");
        }
        else {
            apiResult.setData(list);
        }

        return apiResult.toString();
    }
}
