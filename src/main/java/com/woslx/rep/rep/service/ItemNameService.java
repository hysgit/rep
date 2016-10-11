package com.woslx.rep.rep.service;

import com.woslx.rep.rep.entity.ItemName;

import java.util.List;

/**
 * Created by hy on 9/3/16.
 */
public interface ItemNameService {

    void insert(ItemName itemName);

    ItemName getById(Integer id);

    void update(ItemName itemName);

    List<ItemName> getAll();

    List<ItemName> getByTypeId(Integer typeId);

    ItemName getByName(String name);
}
