package com.golubovich.elibrary.beans;

import com.golubovich.elibrary.enums.ClientStatus;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Client extends Person {
    private int age;
    private String eMail;
    private Date registrationDate;
    private ClientStatus clientStatus;

    public Client(String surname, String name, String patronymic, String password, int age, String eMail, Date registrationDate, ClientStatus clientStatus) {
        super(surname, name, patronymic, password);
        this.age = age;
        this.eMail = eMail;
        this.registrationDate = registrationDate;
        this.clientStatus = clientStatus;
    }

    public Client(String surname, String name, String password, int age, String eMail, Date registrationDate, ClientStatus clientStatus) {
        super(surname, name, password);
        this.age = age;
        this.eMail = eMail;
        this.registrationDate = registrationDate;
        this.clientStatus = clientStatus;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String geteMail() {
        return this.eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public Date getRegistrationDate() {
        return this.registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public ClientStatus getClientStatus() {
        return this.clientStatus;
    }

    public void setClientStatus(ClientStatus clientStatus) {
        this.clientStatus = clientStatus;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            if (!super.equals(o)) {
                return false;
            } else {
                Client client = (Client)o;
                return this.age == client.age && this.eMail.equals(client.eMail) && this.registrationDate.equals(client.registrationDate) && this.clientStatus == client.clientStatus;
            }
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{super.hashCode(), this.age, this.eMail, this.registrationDate, this.clientStatus});
    }

    public String toString() {
        int var10000 = this.age;
        return "Client{age=" + var10000 + ", eMail='" + this.eMail + "', registrationDate=" + this.registrationDate + ", clientStatus=" + this.clientStatus + "} " + super.toString();
    }

    public String toReadableString() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String var10000 = super.toReadableString();
        return var10000 + "   E-mail: " + this.eMail + "   Возраст: " + this.age + "   Дата регистрации: " + formatter.format(this.registrationDate) + "   Статус: " + this.clientStatus.getName();
    }
}
