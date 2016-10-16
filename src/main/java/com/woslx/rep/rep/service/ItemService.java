package com.woslx.rep.rep.service;

import com.woslx.rep.rep.entity.Item;

import java.util.List;

/**
 * Created by hy on 9/3/16.
 */
public interface ItemService {

    void insert(Item item);

    Item getById(Integer id);

    void update(Item item);

    List<Item> getByTypeId(Integer typeId);

    List<Item> getByNameId(Integer nameId);

    List<Item> getAll();

    Item getBySerialNumber(String serialNumber);

    List<Item> getByTypeIdAndNameIdAndSpec(Integer typeId, Integer nameId, String spec);
}
