package com.woslx.rep.rep.controller;


import com.woslx.rep.common.ApiException;
import com.woslx.rep.common.ApiResult;
import com.woslx.rep.common.Constants;

import com.woslx.rep.rep.entity.Item;
import com.woslx.rep.rep.entity.ItemName;
import com.woslx.rep.rep.entity.ItemType;
import com.woslx.rep.rep.entity.param.ParamItem;
import com.woslx.rep.rep.entity.vo.ItemOut;
import com.woslx.rep.rep.service.ItemNameService;
import com.woslx.rep.rep.service.ItemService;
import com.woslx.rep.rep.service.ItemTypeService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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

    @Autowired
    private ItemTypeService itemTypeService;

    @Autowired
    private ItemNameService itemNameService;


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

        Item item = itemService.getBySerialNumber(paramItem.getSerialNumber());
        if (item != null) {
            apiResult.setCode(1);
            apiResult.setMessage(Constants.EXISTS);
            return apiResult.toString();
        }

        try {
            itemService.insert(createItem(paramItem));
        } catch (Exception e) {
            apiResult.setCode(1);
            apiResult.setMessage(Constants.FAIL);
        }

        return apiResult.toString();
    }

    @RequestMapping(value = "/edit",
            consumes = "application/json",
            produces = "application/json;charset=utf-8")
    @ResponseBody
    public String edit(@RequestBody ParamItem paramItem) {
        ApiResult<String> apiResult = new ApiResult<>(0, Constants.SUCCESS);

        Item item = itemService.getById(paramItem.getId());
        if (item == null) {
            apiResult.setCode(1);
            apiResult.setMessage("不存在");
            return apiResult.toString();
        }
        item.setBasicPrice(paramItem.getBasicPrice());
        item.setPrice(paramItem.getPrice());

        try {
            itemService.update(item);
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
        if(StringUtils.isEmpty(paramItem.getCompany()))
        {
            item.setCompany("");
        }
        else {
            item.setCompany(paramItem.getCompany());
        }
        item.setPrice(paramItem.getPrice());
        item.setBasicPrice(paramItem.getBasicPrice());
        item.setQuantityAll(0);
        item.setQuantityCurrent(0);
        item.setQuantityUse(0);
        item.setState(1);
        Integer sort = itemService.getMaxSort();
        if(sort ==null || sort.equals(0))
        {
            sort = 1;
        }
        else {
            sort++;
        }
        item.setSort(sort);

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
    public String getAll(@RequestBody ParamItem paramItem, HttpSession session)
    {
        ApiResult<List<ItemOut>> apiResult = new ApiResult<>(0, Constants.SUCCESS);
//        List<Item> itemList = itemService.getAll();

        Integer typeId = paramItem.getTypeId();
        Integer nameId = paramItem.getNameId();
        String spec = paramItem.getSpecifications();

        if(typeId ==null || typeId ==0)
        {
            typeId=null;
            nameId = null;
            spec = null;
        }
        if(nameId == null || nameId == 0)
        {
            nameId = null;
            spec = null;
        }
        if("".equals(spec))
        {
            spec = null;
        }

        List<Item> itemList = itemService.getByTypeIdAndNameIdAndSpec(typeId, nameId, spec);

        if(itemList == null)
        {
            throw new ApiException(1, "未找到Item");
        }

        if (itemList.size() == 0) {
            apiResult.setCode(2);
            apiResult.setMessage("查询结果为空");
        }
        else {
            String username = apiResult.getUser().getUsername();
            List<ItemOut> itemOuts = createItemOut(itemList,username);
            session.setAttribute("result",itemOuts);
            Collections.sort(itemOuts);
            apiResult.setData(itemOuts);
        }

        return apiResult.toString();
    }

    private List<ItemOut> createItemOut(List<Item> itemList,String username) {

        List<ItemOut> itemOuts = new ArrayList<>();

        for(Item item: itemList)
        {
            ItemOut itemOut = new ItemOut();
            itemOuts.add(itemOut);

            ItemName itemName = itemNameService.getById(item.getNameId());
            ItemType itemType = itemTypeService.getById(item.getTypeId());

            itemOut.setTypeName(itemType.getName());
            itemOut.setName(itemName.getName());
            itemOut.setId(item.getId());
            itemOut.setQuantityAll(item.getQuantityAll()/10.0);
            itemOut.setQuantityCurrent(item.getQuantityCurrent()/10.0);
            itemOut.setQuantityUse(item.getQuantityUse()/10.0);
            itemOut.setSerialNumber(item.getSerialNumber());
            itemOut.setSpecifications(item.getSpecifications());
            itemOut.setSort(item.getSort());
            if(username.equals("admin"))
            {
                itemOut.setBasicPrice(item.getBasicPrice());
                itemOut.setPrice(item.getPrice());
            }
        }

        return itemOuts;
    }

    @RequestMapping("download")
    public ResponseEntity<byte[]> download(HttpSession session) throws IOException {

        List<ItemOut> result = (List<ItemOut>)session.getAttribute("result");
        if(result == null)
        {
            throw new RuntimeException("未找到文件");
        }

        URL resource = this.getClass().getClassLoader().getResource(".");
        String path =null;
        if (resource != null) {
            path = resource.getPath();
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日hh时mm分ss秒");
        String format = sdf.format(date);
        path = path + "库存统计"+format +".xlsx";
        String username = ((String) session.getAttribute("username"));
        File file= createFile(path,result,username);
        HttpHeaders headers = new HttpHeaders();
        String fileName=new String(("库存统计" + format +".xlsx").getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题

        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }

    private File createFile(String path, List<ItemOut> result,String username) throws IOException {
        //创建新的Excel工作薄
        SXSSFWorkbook  workbook=new SXSSFWorkbook();
        //如果新建一个名为“sheet1”的工作表
        SXSSFSheet sheet = workbook.createSheet("sheet1");
        addHead(sheet, username);
        addContent(sheet, result, username);


        FileOutputStream fOut=new FileOutputStream(path);
        //将数据写入Excel
        workbook.write(fOut);
        fOut.flush();
        fOut.close();

        return new File(path);
    }

    private void addContent(SXSSFSheet sheet, List<ItemOut> result,String username) {

        double allPrice = 0;
        int i = 0;
        for(; i < result.size(); i++)
        {
            ItemOut itemOut = result.get(i);

            SXSSFRow row=sheet.createRow(i+2);
            SXSSFCell cell1 = row.createCell(0, XSSFCell.CELL_TYPE_STRING);
            cell1.setCellValue(itemOut.getTypeName());

            SXSSFCell cell2 = row.createCell(1, XSSFCell.CELL_TYPE_STRING);
            cell2.setCellValue(itemOut.getName());

            SXSSFCell cell3 = row.createCell(2, XSSFCell.CELL_TYPE_STRING);
            cell3.setCellValue(itemOut.getSerialNumber());

            SXSSFCell cell4 = row.createCell(3, XSSFCell.CELL_TYPE_STRING);
            cell4.setCellValue(itemOut.getSpecifications());

            SXSSFCell cell5 = row.createCell(4, XSSFCell.CELL_TYPE_NUMERIC);
            cell5.setCellValue(itemOut.getQuantityAll());

            SXSSFCell cell6 = row.createCell(5, XSSFCell.CELL_TYPE_NUMERIC);
            cell6.setCellValue(itemOut.getQuantityUse());

            SXSSFCell cell7 = row.createCell(6, XSSFCell.CELL_TYPE_NUMERIC);
            cell7.setCellValue(itemOut.getQuantityCurrent());

            if("admin".equals(username)) {
                SXSSFCell cell8 = row.createCell(7, XSSFCell.CELL_TYPE_NUMERIC);
                cell8.setCellValue(itemOut.getBasicPrice());

                SXSSFCell cell9 = row.createCell(8, XSSFCell.CELL_TYPE_NUMERIC);
                cell9.setCellValue(itemOut.getQuantityCurrent()*itemOut.getBasicPrice());
                allPrice = allPrice + itemOut.getQuantityCurrent()*itemOut.getBasicPrice();
            }
        }
        if("admin".equals(username)) {
            SXSSFRow row = sheet.createRow(i + 2);
            SXSSFCell cell1 = row.createCell(0, XSSFCell.CELL_TYPE_STRING);
            cell1.setCellValue("总库存");

            SXSSFCell cell2 = row.createCell(1, XSSFCell.CELL_TYPE_NUMERIC);
            cell2.setCellValue(allPrice);
        }
    }


    private void addHead(SXSSFSheet sheet, String username) {
        SXSSFRow row=sheet.createRow(0);
        //在索引0的位置创建单元格(左上端)
        Cell cell=row.createCell(0);
        //定义单元格为字符串类型
        cell.setCellType(XSSFCell.CELL_TYPE_STRING);

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日hh时mm分ss秒");
        String format = sdf.format(date);

        //在单元格中输入一些内容
        cell.setCellValue(format);

        SXSSFRow row2=sheet.createRow(1);

        SXSSFCell cell1 = row2.createCell(0, XSSFCell.CELL_TYPE_STRING);
        cell1.setCellValue("产品类型");

        SXSSFCell cell2 = row2.createCell(1, XSSFCell.CELL_TYPE_STRING);
        cell2.setCellValue("产品名称");

        SXSSFCell cell3 = row2.createCell(2, XSSFCell.CELL_TYPE_STRING);
        cell3.setCellValue("产品编号");

        SXSSFCell cell4 = row2.createCell(3, XSSFCell.CELL_TYPE_STRING);
        cell4.setCellValue("产品规格");

        SXSSFCell cell5 = row2.createCell(4, XSSFCell.CELL_TYPE_STRING);
        cell5.setCellValue("总入库");

        SXSSFCell cell6 = row2.createCell(5, XSSFCell.CELL_TYPE_STRING);
        cell6.setCellValue("总出库");

        SXSSFCell cell7 = row2.createCell(6, XSSFCell.CELL_TYPE_STRING);
        cell7.setCellValue("当前数量");
        if("admin".equals(username)) {
            SXSSFCell cell8 = row2.createCell(7, XSSFCell.CELL_TYPE_STRING);
            cell8.setCellValue("单价");

            SXSSFCell cell9 = row2.createCell(8, XSSFCell.CELL_TYPE_STRING);
            cell9.setCellValue("总价");
        }
    }
}
