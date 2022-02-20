package com.golubovich.elibrary.dao.impl;

import com.golubovich.elibrary.beans.EducationalMaterial;
import com.golubovich.elibrary.dao.api.ItemDAO;
import com.golubovich.elibrary.enums.ItemType;
import com.golubovich.elibrary.source.DataSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class EducationalMaterialDAOImpl implements ItemDAO<EducationalMaterial> {
    private final DataSource dataSource = DataSource.getInstance();

    public EducationalMaterialDAOImpl() {
    }

    public boolean create(EducationalMaterial bean) {
        boolean result = ((List)this.dataSource.getItems().get(ItemType.EDUCATIONAL_MATERIAL)).add(bean);
        return result;
    }

    public EducationalMaterial readReviews(int code) {
        Iterator iterator = ((List)this.dataSource.getItems().get(ItemType.EDUCATIONAL_MATERIAL)).iterator();

        EducationalMaterial edMaterial;
        do {
            if (!iterator.hasNext()) {
                return null;
            }

            edMaterial = (EducationalMaterial)iterator.next();
        } while(edMaterial.getCode() != code);

        return edMaterial;
    }

    public boolean addReview(int code, String review) {
        Iterator iterator = ((List)this.dataSource.getItems().get(ItemType.EDUCATIONAL_MATERIAL)).iterator();

        EducationalMaterial edMaterial;
        do {
            if (!iterator.hasNext()) {
                return false;
            }

            edMaterial = (EducationalMaterial)iterator.next();
        } while(edMaterial.getCode() != code);

        edMaterial.getReview().add(review);
        return true;
    }

    public List<EducationalMaterial> readAll() {
        return new ArrayList((Collection)this.dataSource.getItems().get(ItemType.EDUCATIONAL_MATERIAL));
    }

    public boolean delete(int code) {
        Iterator iterator = ((List)this.dataSource.getItems().get(ItemType.EDUCATIONAL_MATERIAL)).iterator();

        EducationalMaterial edMaterial;
        do {
            if (!iterator.hasNext()) {
                return false;
            }

            edMaterial = (EducationalMaterial)iterator.next();
        } while(edMaterial.getCode() != code);

        ((List)this.dataSource.getItems().get(ItemType.EDUCATIONAL_MATERIAL)).remove(edMaterial);
        return true;
    }
}

