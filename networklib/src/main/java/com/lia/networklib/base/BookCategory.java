package com.lia.networklib.base;

import com.lia.networklib.base.BaseEntity;

public class BookCategory extends BaseEntity {
    /**
     * "id": 758,
     *             "code": 1,
     *             "name": "财经管理",
     *             "parentCode": null,
     *             "status": 1,
     *             "addTime": 1472630524000,
     *             "addUserId": 1,
     *             "updateTime": 1472691636000,
     *             "updateUserId": 1,
     *             "count": 392,
     *             "categoryType": 2,
     *             "sort": 1
     */
    private String id;
    private int code;
    private String name;
    private String parentCode;
    private String status;
    private String addTime;
    private String addUserId;
    private String updateTime;
    private String updateUserId;
    private int count;
    private String categoryType;
    private int sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getAddUserId() {
        return addUserId;
    }

    public void setAddUserId(String addUserId) {
        this.addUserId = addUserId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "BookCategory{" +
                "id='" + id + '\'' +
                ", code=" + code +
                ", name='" + name + '\'' +
                ", parentCode='" + parentCode + '\'' +
                ", status='" + status + '\'' +
                ", addTime='" + addTime + '\'' +
                ", addUserId='" + addUserId + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", updateUserId='" + updateUserId + '\'' +
                ", count=" + count +
                ", categoryType=" + categoryType +
                ", sort=" + sort +
                '}';
    }
}
