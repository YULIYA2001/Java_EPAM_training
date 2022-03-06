package com.golubovich.elibrary.dao.impl;

import com.golubovich.elibrary.beans.EducationalMaterial;
import com.golubovich.elibrary.beans.Item;
import com.golubovich.elibrary.dao.api.ItemDAO;
import com.golubovich.elibrary.enums.ItemType;
import com.golubovich.elibrary.utils.DataSource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EducationalMaterialDAOImpl implements ItemDAO<EducationalMaterial> {
    private final DataSource dataSource = DataSource.getInstance();

    public boolean create(EducationalMaterial item) {
        return dataSource.getItems().get(ItemType.EDUCATIONAL_MATERIAL).add(item);
    }

    public List<EducationalMaterial> read() {
        return new ArrayList(dataSource.getItems().get(ItemType.EDUCATIONAL_MATERIAL));
    }

    public void update(EducationalMaterial currentItem, EducationalMaterial updatedItem) {
        dataSource.getItems().get(ItemType.EDUCATIONAL_MATERIAL).set(
                dataSource.getItems().get(ItemType.EDUCATIONAL_MATERIAL).indexOf(currentItem),
                updatedItem
        );
    }

    public boolean delete(EducationalMaterial deletedItem) {
        return dataSource.getItems().get(ItemType.EDUCATIONAL_MATERIAL).remove(deletedItem);
    }

    public EducationalMaterial findByCode(int code) {
        Iterator<Item> edMaterialsIterator =
                dataSource.getItems().get(ItemType.EDUCATIONAL_MATERIAL).iterator();

        EducationalMaterial edMaterial;
        while(edMaterialsIterator.hasNext()) {
            edMaterial = (EducationalMaterial) edMaterialsIterator.next();
            //TODO exception
            if(edMaterial.getCode() == code) {
                return edMaterial;
            }
        }
        return null;
    }
}

