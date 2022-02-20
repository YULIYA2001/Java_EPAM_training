package com.golubovich.elibrary.service.impl;

import com.golubovich.elibrary.beans.EducationalMaterial;
import com.golubovich.elibrary.dao.DAOProvider;
import com.golubovich.elibrary.dao.api.ItemDAO;
import com.golubovich.elibrary.enums.EdMaterialSubjects;
import com.golubovich.elibrary.enums.EdMaterialType;
import com.golubovich.elibrary.service.api.ItemService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EdMaterialServiceImpl implements ItemService {
    private final DAOProvider provider = DAOProvider.getInstance();
    private final ItemDAO edMaterialDAO;

    public EdMaterialServiceImpl() {
        this.edMaterialDAO = this.provider.getEdMaterialDAO();
    }

    public boolean add(String[] orderedParams) {
        ItemDAO edMaterialDAO = this.provider.getEdMaterialDAO();
        EdMaterialSubjects subject = EdMaterialSubjects.getByCode(Integer.parseInt(orderedParams[2]));
        EdMaterialType type = EdMaterialType.getByName(orderedParams[3]);
        if (subject == null || type == null) {
            System.exit(666);
        }

        EducationalMaterial educationalMaterial = new EducationalMaterial(orderedParams[0], new ArrayList(), orderedParams[1], subject, type, orderedParams[4]);
        boolean result = edMaterialDAO.create(educationalMaterial);
        return result;
    }

    public String showReviewsByCode(int code) {
        EducationalMaterial edMaterial = (EducationalMaterial)this.edMaterialDAO.readReviews(code);
        if (edMaterial == null) {
            return "Неверный код учебного материала";
        } else if (edMaterial.getReview().isEmpty()) {
            return "Отзывов нет";
        } else {
            StringBuilder result = new StringBuilder();
            int var10001 = edMaterial.getCode();
            result.append("Отзывы (код учебного материала " + var10001 + " " + edMaterial.getName() + "):\n");
            Iterator var4 = edMaterial.getReview().iterator();

            while(var4.hasNext()) {
                String review = (String)var4.next();
                result.append(" ").append(review).append("\n");
            }

            return String.valueOf(result);
        }
    }

    public boolean addReviewByCode(int code, String review) {
        boolean result = this.edMaterialDAO.addReview(code, review);
        return result;
    }

    public String showAll() {
        List<EducationalMaterial> edMaterials = this.edMaterialDAO.readAll();
        if (this.edMaterialDAO != null && !edMaterials.isEmpty()) {
            StringBuilder result = new StringBuilder();
            Iterator var3 = edMaterials.iterator();

            while(var3.hasNext()) {
                EducationalMaterial edMaterial = (EducationalMaterial)var3.next();
                result.append(" ").append(edMaterial.toReadableString()).append("\n");
            }

            return String.valueOf(result);
        } else {
            return "Список учебных материалов пуст";
        }
    }

    public boolean deleteByCode(int code) {
        boolean result = this.edMaterialDAO.delete(code);
        return result;
    }
}

