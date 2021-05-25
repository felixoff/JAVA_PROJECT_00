package org.felix.myserver.model;

public class Users {
    private int id;
    private String bill_from;
    private String bill_to;
    private int balance;

    public Users(String[] s) {
        id = Integer.parseInt(s[0]);
        bill_from = s[1];
        bill_to = s[2];
        balance = Integer.parseInt(s[3]);
    }

    public Users(int id_user, String bil_from, String bil_to, int money) {
        id = id_user;
        bill_from = bil_from;
        bill_to = bil_to;
        balance = money;
    }

    public Users() {
        id = 0;
        bill_from = "";
        bill_to = "";
        balance = 0;
    }

    public int getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    public String getBill_to() {
        return bill_to;
    }

    public String getBill_from() {
        return bill_from;
    }

    public void setBill_from(String bil_from) {
        this.bill_from = bil_from;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBill_to(String bil_to) {
        this.bill_to = bil_to;
    }


    public void setBalance(int money) {
        this.balance = money;
    }

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Users user = (Users) o;
//        return id == user.id && balance == user.balance && Objects.equals(name, user.name)
//                && Objects.equals(bill, user.bill) && Objects.equals(card, user.card);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name, bill, card, balance);
//    }
//
//    //    @Override
////    public String toString() {
////        return "User(id='"+ this.id + "', name='" + this.name + "', bill='" + this.bill + "',card='" +
////                this.card + "',money='" + this.balance + "')";
////    }
    @Override
    public String toString() {
        return "User(id='" + this.id + "',bill_from='" + this.bill_from +
                "',bill_to='" + this.bill_to +
                "',balance='" +
                this.balance + "')";
    }
//
//    @Override
//    public int compareTo(Users o) {
//        return this.name.compareTo(o.name);
//    }

}
