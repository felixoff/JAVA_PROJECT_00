package org.felix.myserver.model;

import java.util.Objects;

public class UserForBalance implements Comparable<UserForBalance> {
    private int id;
    private String name;
    private String bill;
    private int balance;

    public UserForBalance(String[] s) {
        id = Integer.parseInt(s[0]);
        name = s[1];
        bill = s[2];
        balance = Integer.parseInt(s[4]);
    }

    public UserForBalance(int id_user, String name_user, String bill_id, int money) {
        id = id_user;
        name = name_user;
        bill = bill_id;
        balance = money;
    }

    public UserForBalance() {
        id = 0;
        name = "";
        bill = "";
        balance = 0;
    }

    public int getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public String getBill() {
        return bill;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public void setBalance(int money) {
        this.balance = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserForBalance user = (UserForBalance) o;
        return id == user.id && balance == user.balance && Objects.equals(name, user.name)
                && Objects.equals(bill, user.bill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, bill, balance);
    }

    //    @Override
//    public String toString() {
//        return "User(id='"+ this.id + "', name='" + this.name + "', bill='" + this.bill + "',card='" +
//                this.card + "',money='" + this.balance + "')";
//    }
    @Override
    public String toString() {
        return "User(id='" + this.id + "',name='" + this.name +
                "',bill='" + this.bill +
                "',balance='" +
                this.balance + "')";
    }

    @Override
    public int compareTo(UserForBalance o) {
        return this.name.compareTo(o.name);
    }

}
