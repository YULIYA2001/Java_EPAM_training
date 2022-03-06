package com.golubovich.elibrary.beans;

import com.golubovich.elibrary.enums.ClientStatus;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Client extends Person implements Comparable<Client>, Serializable {
    private static int count = 0;
    private int age;
    private String eMail;
    private Date registrationDate;
    private ClientStatus clientStatus;

    {
        count++;
    }

    public Client() {}

    public Client(String surname, String name, String patronymic, String password, int age,
                  String eMail, Date registrationDate, ClientStatus clientStatus) {
        super(surname, name, patronymic, password);
        this.age = age;
        this.eMail = eMail;
        this.registrationDate = registrationDate;
        this.clientStatus = clientStatus;
    }

    public Client(Client copiedClient) {
        super(copiedClient.getSurname(), copiedClient.getName(),
                copiedClient.getPatronymic(), copiedClient.getPassword());
        this.age = copiedClient.age;
        this.eMail = copiedClient.eMail;
        this.registrationDate = copiedClient.registrationDate;
        this.clientStatus = copiedClient.clientStatus;
    }

    public static int getCount() {
        return count;
    }

    public static void incrementCount() {
        count--;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEMail() {
        return this.eMail;
    }

    public void setEMail(String eMail) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            if (!super.equals(o)) {
                return false;
            } else {
                Client client = (Client)o;
                return this.age == client.age
                        && this.eMail.equals(client.eMail)
                        && this.registrationDate.equals(client.registrationDate)
                        && this.clientStatus == client.clientStatus;
            }
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                super.hashCode(), this.age, this.eMail, this.registrationDate, this.clientStatus);
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return super.toString() +
                "   E-mail: " + this.eMail +
                "   Возраст: " + this.age +
                "   Дата регистрации: " + formatter.format(this.registrationDate) +
                "   Статус: " + this.clientStatus.getName();
    }

    @Override
    public int compareTo(Client o) {
        int result = this.getSurname().compareTo(o.getSurname());
        if (result == 0) {
            result = this.getName().compareTo(o.getName());
        }
        return result;
    }
}
