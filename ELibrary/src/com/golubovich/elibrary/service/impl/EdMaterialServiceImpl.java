package com.golubovich.elibrary.service.impl;

import com.golubovich.elibrary.beans.EducationalMaterial;
import com.golubovich.elibrary.dao.DAOProvider;
import com.golubovich.elibrary.dao.api.ItemDAO;
import com.golubovich.elibrary.enums.EdMaterialSubjects;
import com.golubovich.elibrary.enums.EdMaterialType;
import com.golubovich.elibrary.service.api.ItemService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EdMaterialServiceImpl implements ItemService {
    private final DAOProvider provider = DAOProvider.getInstance();
    private final ItemDAO<EducationalMaterial> edMaterialDAO = provider.getEdMaterialDAO();

    public boolean add(String[] orderedParams) {
        String name = orderedParams[0];
        List<String> reviews = new ArrayList<>();
        String language = orderedParams[1];
        EdMaterialSubjects subject = EdMaterialSubjects.getByCode(Integer.parseInt(orderedParams[2]));
        EdMaterialType type = EdMaterialType.getByName(orderedParams[3]);
        String author = orderedParams[4];

        if (subject == null || type == null) {
            return false;
            //TODO
        }

        EducationalMaterial educationalMaterial = new EducationalMaterial(name, reviews, language,
                subject, type, author);
        return edMaterialDAO.create(educationalMaterial);
    }

    public String showAll() {
        List<EducationalMaterial> edMaterials = edMaterialDAO.read();
        if (edMaterials != null && !edMaterials.isEmpty()) {
            Collections.sort(edMaterials);
            return objectListToString(edMaterials);
        }
        return "Список учебных материалов пуст";
    }

    public boolean deleteByCode(int code) {
        EducationalMaterial deletedEdMaterial = edMaterialDAO.findByCode(code);

        if (deletedEdMaterial != null && edMaterialDAO.delete(deletedEdMaterial)) {
            EducationalMaterial.decrementCount();
            return true;
        }
        return false;
    }

    public boolean addReviewByCode(int code, String review) {
        EducationalMaterial currentEdMaterial = edMaterialDAO.findByCode(code);

        if (currentEdMaterial != null) {
            EducationalMaterial updatedEdMaterial = new EducationalMaterial(currentEdMaterial);
            updatedEdMaterial.getReview().add(review);
            edMaterialDAO.update(currentEdMaterial, updatedEdMaterial);
            return true;
        }
        return false;
    }

    public String showReviewsByCode(int code) {
        EducationalMaterial edMaterial = edMaterialDAO.findByCode(code);

        if (edMaterial == null) {
            return "Неверный код учебного материала";
        }
        if (edMaterial.getReview().isEmpty()) {
            return "Отзывов нет";
        }
        return "Отзывы (код учебного материала " + edMaterial.getCode() + ", " +
                edMaterial.getName() + "):\n" + objectListToString(edMaterial.getReview());
    }
}

