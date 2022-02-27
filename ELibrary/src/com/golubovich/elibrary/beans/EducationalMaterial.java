package com.golubovich.elibrary.beans;

import com.golubovich.elibrary.enums.EdMaterialSubjects;
import com.golubovich.elibrary.enums.EdMaterialType;
import java.util.List;
import java.util.Objects;

public class EducationalMaterial extends Item {
    private EdMaterialSubjects edMaterialSubject;
    private EdMaterialType edMaterialType;
    private String author;

    public EducationalMaterial(String name, List<String> review,
                               String language, EdMaterialSubjects edMaterialSubject,
                               EdMaterialType edMaterialType, String author) {
        super(name, review, language);
        this.edMaterialSubject = edMaterialSubject;
        this.edMaterialType = edMaterialType;
        this.author = author;
    }

    public EducationalMaterial(EducationalMaterial educationalMaterial) {
        super(educationalMaterial.getName(), educationalMaterial.getReview(), educationalMaterial.getLanguage());
        this.edMaterialSubject = educationalMaterial.edMaterialSubject;
        this.edMaterialType = educationalMaterial.edMaterialType;
        this.author = educationalMaterial.author;
    }

    public EdMaterialSubjects getEdMaterialSubject() {
        return this.edMaterialSubject;
    }

    public void setEdMaterialSubject(EdMaterialSubjects edMaterialSubject) {
        this.edMaterialSubject = edMaterialSubject;
    }

    public EdMaterialType getEdMaterialType() {
        return this.edMaterialType;
    }

    public void setEdMaterialType(EdMaterialType edMaterialType) {
        this.edMaterialType = edMaterialType;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            if (!super.equals(o)) {
                return false;
            } else {
                EducationalMaterial that = (EducationalMaterial)o;
                return this.edMaterialSubject == that.edMaterialSubject
                        && this.edMaterialType == that.edMaterialType &&
                        Objects.equals(this.author, that.author);
            }
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.edMaterialSubject, this.edMaterialType, this.author);
    }

    @Override
    public String toString() {
        return "Учебное пособие  -  " + super.toString() +
                "   Предмет: " + this.edMaterialSubject.getName() +
                "   Тип: " + this.edMaterialType.getName() +
                "   Автор: " + this.author;
    }
}

