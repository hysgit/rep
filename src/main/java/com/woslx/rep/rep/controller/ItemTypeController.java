package com.woslx.rep.rep.controller;

import com.woslx.rep.common.ApiException;
import com.woslx.rep.common.ApiResult;
import com.woslx.rep.common.BaseController;
import com.woslx.rep.common.Constants;
import com.woslx.rep.rep.entity.ItemType;
import com.woslx.rep.rep.entity.param.ParamItemType;
import com.woslx.rep.rep.service.ItemTypeService;
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
 * Created by hy on 9/2/16.
 */

@Controller
@RequestMapping("/item/type")
public class ItemTypeController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(ItemTypeController.class);

    @Autowired
    private ItemTypeService itemTypeService;

    /**
     * 创建商品类型
     * @param paramItemType
     * @return
     */
    @RequestMapping(value = "/create",
            consumes = "application/json",
            produces = "application/json;charset=utf-8")
    @ResponseBody
    public String create(@RequestBody ParamItemType paramItemType){
        ApiResult<String> apiResult = new ApiResult<>(0, Constants.SUCCESS);
        String name = paramItemType.getName().trim();
        List<ItemType> list = itemTypeService.getByName(name);
        if (list.size() != 0) {
            throw new ApiException(1, "该名称的类型已经存在");
        }
        ItemType itemType = new ItemType();
        itemType.setName(name);
        Date now = new Date();
        itemType.setCreateTime(now);
        itemType.setUpdateTime(now);

        try {
            itemTypeService.insert(itemType);
        } catch (Exception e) {
            apiResult.setCode(1);
            apiResult.setMessage(Constants.FAIL);
        }

        return apiResult.toString();
    }

    /**
     * 根据id修改商品类型名称
     * @param paramItemType
     * @return
     */
    @RequestMapping(value = "/update",
                    consumes = "application/json",
                    produces = "application/json;charset=utf-8")
    @ResponseBody
    public String update(@RequestBody ParamItemType paramItemType)
    {
        ApiResult<String> apiResult = new ApiResult<>(0, Constants.SUCCESS);
        ItemType itemType = itemTypeService.getById(paramItemType.getId());
        if(itemType == null)
        {
            throw new ApiException(1, "不存在的ItemType");
        }
        String name = paramItemType.getName().trim();
        List<ItemType> list = itemTypeService.getByName(name);
        if (list.size() != 0) {
            throw new ApiException(1, "该名称的类型已经存在");
        }
        itemType.setName(name);
        itemType.setUpdateTime(new Date());


        itemTypeService.update(itemType);

        return apiResult.toString();
    }

    /**
     * 根据id查询商品类型信息
     * @param paramItemType
     * @return
     */
    @RequestMapping(value="/get/by/id",
                    consumes = "application/json",
                    produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getById(@RequestBody ParamItemType paramItemType)
    {
        ApiResult<ItemType> apiResult = new ApiResult<>(0, Constants.SUCCESS);
        ItemType itemType = itemTypeService.getById(paramItemType.getId());
        if(itemType == null)
        {
            throw new ApiException(1, "不存在的ItemType");
        }
        apiResult.setData(itemType);

        return apiResult.toString();
    }

    /**
     * 查询所有的商品类型
     * @return
     */
    @RequestMapping(value = "/get/all",
            consumes = "application/json",
            produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getAll()
    {
        ApiResult<List<ItemType>> apiResult = new ApiResult<>(0, Constants.SUCCESS);
        List<ItemType> list = itemTypeService.getAll();

        if (list.size() == 0) {
            apiResult.setCode(2);
            apiResult.setMessage("查询结果为空");
        }
        else {
            apiResult.setData(list);
        }

        return apiResult.toString();
    }
}
