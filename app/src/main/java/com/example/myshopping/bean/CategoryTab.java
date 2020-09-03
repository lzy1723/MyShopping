package com.example.myshopping.bean;


public class CategoryTab {
    /**
     * id : 1
     * categoryName : 电脑办公
     * parentId : 0
     */

    private int id;
    private String categoryName;
    private int parentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
