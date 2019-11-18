package com.lia.networklib.base;

import java.util.List;

public class UserInterface extends BaseEntity {

    /**
     * totalCount : 2
     * list : [{"nickName":"nickName1","email":"email1","age":1},{"nickName":"nickName2","email":"email2","age":2}]
     */

    private int totalCount;
    private List<ListBean> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * nickName : nickName1
         * email : email1
         * age : 1
         */

        private String nickName;
        private String email;
        private int age;

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "ListBean{" +
                    "nickName='" + nickName + '\'' +
                    ", email='" + email + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "UserInterface{" +
                "totalCount=" + totalCount +
                ", list=" + list +
                '}';
    }
}
