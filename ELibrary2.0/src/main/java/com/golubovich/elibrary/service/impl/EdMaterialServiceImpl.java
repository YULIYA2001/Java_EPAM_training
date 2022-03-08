package com.golubovich.elibrary.service.impl;

import static com.golubovich.elibrary.utils.Constants.EMPTY_ED_MATERIALS_LIST;
import static com.golubovich.elibrary.utils.Constants.NO_REVIEWS;
import static com.golubovich.elibrary.utils.Constants.REVIEWS;
import static com.golubovich.elibrary.utils.Constants.WRONG_ED_MATERIAL_CODE;

import com.golubovich.elibrary.beans.EducationalMaterial;
import com.golubovich.elibrary.dao.DAOException;
import com.golubovich.elibrary.dao.DAOProvider;
import com.golubovich.elibrary.dao.api.ItemDAO;
import com.golubovich.elibrary.enums.EdMaterialSubjects;
import com.golubovich.elibrary.enums.EdMaterialType;
import com.golubovich.elibrary.service.ServiceException;
import com.golubovich.elibrary.service.api.ItemService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EdMaterialServiceImpl implements ItemService {
    private final DAOProvider provider = DAOProvider.getInstance();
    private final ItemDAO<EducationalMaterial> edMaterialDAO = provider.getEdMaterialDAO();

    public boolean add(String[] orderedParams) throws ServiceException {
        String name = orderedParams[0].replace('_', ' ');
        List<String> reviews = new ArrayList<>();
        String language = orderedParams[1];
        String typeName = orderedParams[3];
        String author = orderedParams[4].replace('_', ' ');

        int code;
        try {
            code = Integer.parseInt(orderedParams[2]);
        } catch (NumberFormatException e) {
            throw new ServiceException(e);
        }

        EdMaterialSubjects subject = EdMaterialSubjects.getByCode(code);
        EdMaterialType type = EdMaterialType.getByName(typeName);

        if (subject == null || type == null) {
            throw new ServiceException("subject == null || type == null");
        }

        EducationalMaterial educationalMaterial = new EducationalMaterial(name, reviews, language,
                subject, type, author);
        try {
            return edMaterialDAO.create(educationalMaterial);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public String showAll() throws ServiceException {
        List<EducationalMaterial> edMaterials;
        try {
            edMaterials = edMaterialDAO.read();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        if (edMaterials != null && !edMaterials.isEmpty()) {
            Collections.sort(edMaterials);
            return objectListToString(edMaterials);
        }
        return EMPTY_ED_MATERIALS_LIST;
    }

    public boolean deleteByCode(int code) throws ServiceException {
        try {
            EducationalMaterial deletedEdMaterial = edMaterialDAO.findByCode(code);

            if (deletedEdMaterial != null && edMaterialDAO.delete(deletedEdMaterial)) {
                EducationalMaterial.decrementCount();
                return true;
            }
            return false;

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public boolean addReviewByCode(int code, String review) throws ServiceException {
        try {
            EducationalMaterial currentEdMaterial = edMaterialDAO.findByCode(code);

            if (currentEdMaterial != null) {
                EducationalMaterial updatedEdMaterial = new EducationalMaterial(currentEdMaterial);
                updatedEdMaterial.getReview().add(review);
                edMaterialDAO.update(currentEdMaterial, updatedEdMaterial);
                return true;
            }
            return false;

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public String showReviewsByCode(int code) throws ServiceException {
        EducationalMaterial edMaterial;
        try {
            edMaterial = edMaterialDAO.findByCode(code);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        if (edMaterial == null) {
            return WRONG_ED_MATERIAL_CODE;
        }
        if (edMaterial.getReview().isEmpty()) {
            return REVIEWS + " (" + edMaterial.getCode() + ", " + edMaterial.getName() + "):\n" +
                NO_REVIEWS;
        }
        return REVIEWS + " (" + edMaterial.getCode() + ", " +
                edMaterial.getName() + "):\n" + objectListToString(edMaterial.getReview());
    }
}

