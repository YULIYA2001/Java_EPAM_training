package com.golubovich.elibrary.beans;

import java.util.Objects;

public class Library {
    private String name;
    private String urlAddress;
    private String eMail;

    public Library(String name, String urlAddress, String eMail) {
        this.name = name;
        this.urlAddress = urlAddress;
        this.eMail = eMail;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlAddress() {
        return this.urlAddress;
    }

    public void setUrlAddress(String urlAddress) {
        this.urlAddress = urlAddress;
    }

    public String getEMail() {
        return this.eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Library library = (Library)o;
            return this.name.equals(library.name) && this.urlAddress.equals(library.urlAddress) && this.eMail.equals(library.eMail);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.name, this.urlAddress, this.eMail});
    }

    public String toString() {
        return "Library{name='" + this.name + "', urlAddress='" + this.urlAddress + "', eMail='" + this.eMail + "}";
    }

    public String toReadableString() {
        return "Название: " + this.name + "   URL: " + this.urlAddress + "   e-mail: " + this.eMail;
    }
}

