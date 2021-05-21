package org.felix.myserver.model;

import java.util.Objects;

public class UserForCards {
    private int id;
    private String card;

    public UserForCards(String[] s) {
        id = Integer.parseInt(s[0]);
        card = s[3];
    }

    public UserForCards(int id_user, String card_id) {
        id = id_user;
        card = card_id;
    }

    public UserForCards() {
        id = 0;
        card = "";
    }

    public int getId() {
        return id;
    }

    public String getCard() {
        return card;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCard(String card) {
        this.card = card;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserForCards user = (UserForCards) o;
        return id == user.id && Objects.equals(card, user.card);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, card);
    }

    //    @Override
//    public String toString() {
//        return "User(id='"+ this.id + "', name='" + this.name + "', bill='" + this.bill + "',card='" +
//                this.card + "',money='" + this.balance + "')";
//    }
    @Override
    public String toString() {
        return "User(id='" + this.id +
                "',card='" + this.card + "')";
    }
}
