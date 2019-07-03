package com.example.creditmanagement.Data;

public class Transfer {
    private int fromId;
    private int toId;
    private int credit;

    public Transfer() {
    }

    public Transfer(int fromId, int toId, int credit) {
        this.fromId = fromId;
        this.toId = toId;
        this.credit = credit;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
