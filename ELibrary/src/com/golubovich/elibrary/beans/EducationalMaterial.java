package com.golubovich.elibrary.beans;

import com.golubovich.elibrary.enums.EdMaterialSubjects;
import com.golubovich.elibrary.enums.EdMaterialType;
import java.util.List;
import java.util.Objects;

public class EducationalMaterial extends Item {
    private EdMaterialSubjects edMaterialSubject;
    private EdMaterialType edMaterialType;
    private String author;

    public EducationalMaterial(String name, List<String> review, String language, EdMaterialSubjects edMaterialSubject, EdMaterialType edMaterialType, String author) {
        super(name, review, language);
        this.edMaterialSubject = edMaterialSubject;
        this.edMaterialType = edMaterialType;
        this.author = author;
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

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            if (!super.equals(o)) {
                return false;
            } else {
                EducationalMaterial that = (EducationalMaterial)o;
                return this.edMaterialSubject == that.edMaterialSubject && this.edMaterialType == that.edMaterialType && Objects.equals(this.author, that.author);
            }
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{super.hashCode(), this.edMaterialSubject, this.edMaterialType, this.author});
    }

    public String toString() {
        EdMaterialSubjects var10000 = this.edMaterialSubject;
        return "EducationalMaterial{edMaterialSubject=" + var10000 + ", edMaterialType=" + this.edMaterialType + ", author='" + this.author + "'} " + super.toString();
    }

    public String toReadableString() {
        String var10000 = super.toReadableString();
        return "Учебное пособие  -  " + var10000 + "   Предмет: " + this.edMaterialSubject.getName() + "   Тип: " + this.edMaterialType.getName() + "   Автор: " + this.author;
    }
}

