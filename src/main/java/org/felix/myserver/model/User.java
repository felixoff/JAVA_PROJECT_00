package org.felix.myserver.model;

import java.util.Objects;

public class User implements Comparable<User> {
    private int id;
    private String name;
    private String bill;
    private String card;
    private int balance;

    public User(String[] s) {
        id = Integer.parseInt(s[0]);
        name = s[1];
        bill = s[2];
        card = s[3];
        balance = Integer.parseInt(s[4]);
    }

    public User(int id_user, String name_user, String bill_id, String card_id, int money) {
        id = id_user;
        name = name_user;
        bill = bill_id;
        card = card_id;
        balance = money;
    }

    public User() {
        id = 0;
        name = "";
        bill = "";
        card = "";
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

    public String getCard() {
        return card;
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

    public void setCard(String card) {
        this.card = card;
    }

    public void setBalance(int money) {
        this.balance = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && balance == user.balance && Objects.equals(name, user.name)
                && Objects.equals(bill, user.bill) && Objects.equals(card, user.card);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, bill, card, balance);
    }

    //    @Override
//    public String toString() {
//        return "User(id='"+ this.id + "', name='" + this.name + "', bill='" + this.bill + "',card='" +
//                this.card + "',money='" + this.balance + "')";
//    }
    @Override
    public String toString() {
        return "User(id='" + this.id + "',name='" + this.name +
                "',bill='" + this.bill + "',card='" + this.card +
                "',balance='" +
                this.balance + "')";
    }

    @Override
    public int compareTo(User o) {
        return this.name.compareTo(o.name);
    }

}
