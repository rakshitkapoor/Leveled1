package com.example.expense_manager;

import java.io.Serializable;

public class ExpenseModel implements Serializable {

    private String expenseId;
    private String noteId;
    private String categoryId;
    private String type;
    private long amount;
    private long time;
    private String uid;

    public ExpenseModel() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ExpenseModel(String expenseId, String noteId, String categoryId, String type, long amount, long time, String uid) {
        this.expenseId = expenseId;
        this.noteId = noteId;
        this.categoryId = categoryId;
        this.type = type;
        this.amount = amount;
        this.time = time;
        this.uid = uid;
    }

    public String getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(String expenseId) {
        this.expenseId = expenseId;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
