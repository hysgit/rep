package com.woslx.rep.rep.service;

import com.woslx.rep.rep.entity.ItemType;

import java.util.List;

/**
 * Created by hy on 9/2/16.
 */
public interface ItemTypeService {
    void insert(ItemType itemType);

    ItemType getById(Integer id);

    void update(ItemType itemType);

    List<ItemType> getAll();

    List<ItemType> getByName(String name);
}
