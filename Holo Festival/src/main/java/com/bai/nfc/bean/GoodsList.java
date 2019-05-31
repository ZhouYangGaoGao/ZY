package com.bai.nfc.bean;

import java.util.List;

public class GoodsList {


    /**
     * errorCode : 00
     * errorMsg : 成功
     * pageInfo : {"pageNum":0,"pageSize":10,"size":10,"startRow":1,"endRow":10,"total":13,"pages":2,"list":[{"goodsId":1,"merchantId":"18701646700","goodsName":"aaaa","goodsPrice":5,"goodsAmount":100,"insertTime":"Mar 29, 2019 1:55:03 AM","updateTime":"Mar 29, 2019 1:55:06 AM"},{"goodsId":2,"merchantId":"18701646700","goodsName":"bbbb","goodsPrice":6,"goodsAmount":100,"insertTime":"Mar 29, 2019 1:55:03 AM","updateTime":"Mar 29, 2019 1:55:06 AM"},{"goodsId":3,"merchantId":"18701646700","goodsName":"cccc","goodsPrice":7,"goodsAmount":100,"insertTime":"Mar 29, 2019 1:55:03 AM","updateTime":"Mar 29, 2019 1:55:06 AM"},{"goodsId":4,"merchantId":"18701646700","goodsName":"dddd","goodsPrice":8,"goodsAmount":100,"insertTime":"Mar 29, 2019 1:55:03 AM","updateTime":"Mar 29, 2019 1:55:06 AM"},{"goodsId":5,"merchantId":"18701646700","goodsName":"eeee","goodsPrice":9,"goodsAmount":100,"insertTime":"Mar 29, 2019 1:55:03 AM","updateTime":"Mar 29, 2019 1:55:06 AM"},{"goodsId":6,"merchantId":"18701646700","goodsName":"ffff","goodsPrice":10,"goodsAmount":100,"insertTime":"Mar 29, 2019 1:55:03 AM","updateTime":"Mar 29, 2019 1:55:06 AM"},{"goodsId":7,"merchantId":"18701646700","goodsName":"gggg","goodsPrice":11,"goodsAmount":100,"insertTime":"Mar 29, 2019 1:55:03 AM","updateTime":"Mar 29, 2019 1:55:06 AM"},{"goodsId":8,"merchantId":"18701646700","goodsName":"hhhh","goodsPrice":12,"goodsAmount":100,"insertTime":"Mar 29, 2019 1:55:03 AM","updateTime":"Mar 29, 2019 1:55:06 AM"},{"goodsId":9,"merchantId":"18701646700","goodsName":"iiii","goodsPrice":13,"goodsAmount":100,"insertTime":"Mar 29, 2019 1:55:03 AM","updateTime":"Mar 29, 2019 1:55:06 AM"},{"goodsId":10,"merchantId":"18701646700","goodsName":"jjjj","goodsPrice":14,"goodsAmount":100,"insertTime":"Mar 29, 2019 1:55:03 AM","updateTime":"Mar 29, 2019 1:55:06 AM"}],"prePage":0,"nextPage":1,"isFirstPage":false,"isLastPage":false,"hasPreviousPage":false,"hasNextPage":true,"navigatePages":8,"navigatepageNums":[1,2],"navigateFirstPage":1,"navigateLastPage":2}
     */

    private String errorCode;
    private String errorMsg;
    private PageInfoBean pageInfo;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public PageInfoBean getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfoBean pageInfo) {
        this.pageInfo = pageInfo;
    }

    public static class PageInfoBean {
        /**
         * pageNum : 0
         * pageSize : 10
         * size : 10
         * startRow : 1
         * endRow : 10
         * total : 13
         * pages : 2
         * list : [{"goodsId":1,"merchantId":"18701646700","goodsName":"aaaa","goodsPrice":5,"goodsAmount":100,"insertTime":"Mar 29, 2019 1:55:03 AM","updateTime":"Mar 29, 2019 1:55:06 AM"},{"goodsId":2,"merchantId":"18701646700","goodsName":"bbbb","goodsPrice":6,"goodsAmount":100,"insertTime":"Mar 29, 2019 1:55:03 AM","updateTime":"Mar 29, 2019 1:55:06 AM"},{"goodsId":3,"merchantId":"18701646700","goodsName":"cccc","goodsPrice":7,"goodsAmount":100,"insertTime":"Mar 29, 2019 1:55:03 AM","updateTime":"Mar 29, 2019 1:55:06 AM"},{"goodsId":4,"merchantId":"18701646700","goodsName":"dddd","goodsPrice":8,"goodsAmount":100,"insertTime":"Mar 29, 2019 1:55:03 AM","updateTime":"Mar 29, 2019 1:55:06 AM"},{"goodsId":5,"merchantId":"18701646700","goodsName":"eeee","goodsPrice":9,"goodsAmount":100,"insertTime":"Mar 29, 2019 1:55:03 AM","updateTime":"Mar 29, 2019 1:55:06 AM"},{"goodsId":6,"merchantId":"18701646700","goodsName":"ffff","goodsPrice":10,"goodsAmount":100,"insertTime":"Mar 29, 2019 1:55:03 AM","updateTime":"Mar 29, 2019 1:55:06 AM"},{"goodsId":7,"merchantId":"18701646700","goodsName":"gggg","goodsPrice":11,"goodsAmount":100,"insertTime":"Mar 29, 2019 1:55:03 AM","updateTime":"Mar 29, 2019 1:55:06 AM"},{"goodsId":8,"merchantId":"18701646700","goodsName":"hhhh","goodsPrice":12,"goodsAmount":100,"insertTime":"Mar 29, 2019 1:55:03 AM","updateTime":"Mar 29, 2019 1:55:06 AM"},{"goodsId":9,"merchantId":"18701646700","goodsName":"iiii","goodsPrice":13,"goodsAmount":100,"insertTime":"Mar 29, 2019 1:55:03 AM","updateTime":"Mar 29, 2019 1:55:06 AM"},{"goodsId":10,"merchantId":"18701646700","goodsName":"jjjj","goodsPrice":14,"goodsAmount":100,"insertTime":"Mar 29, 2019 1:55:03 AM","updateTime":"Mar 29, 2019 1:55:06 AM"}]
         * prePage : 0
         * nextPage : 1
         * isFirstPage : false
         * isLastPage : false
         * hasPreviousPage : false
         * hasNextPage : true
         * navigatePages : 8
         * navigatepageNums : [1,2]
         * navigateFirstPage : 1
         * navigateLastPage : 2
         */

        private int pageNum;
        private int pageSize;
        private int size;
        private int startRow;
        private int endRow;
        private int total;
        private int pages;
        private int prePage;
        private int nextPage;
        private boolean isFirstPage;
        private boolean isLastPage;
        private boolean hasPreviousPage;
        private boolean hasNextPage;
        private int navigatePages;
        private int navigateFirstPage;
        private int navigateLastPage;
        private List<ListBean> list;
        private List<Integer> navigatepageNums;

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public boolean isIsFirstPage() {
            return isFirstPage;
        }

        public void setIsFirstPage(boolean isFirstPage) {
            this.isFirstPage = isFirstPage;
        }

        public boolean isIsLastPage() {
            return isLastPage;
        }

        public void setIsLastPage(boolean isLastPage) {
            this.isLastPage = isLastPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
        }

        public int getNavigateFirstPage() {
            return navigateFirstPage;
        }

        public void setNavigateFirstPage(int navigateFirstPage) {
            this.navigateFirstPage = navigateFirstPage;
        }

        public int getNavigateLastPage() {
            return navigateLastPage;
        }

        public void setNavigateLastPage(int navigateLastPage) {
            this.navigateLastPage = navigateLastPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public static class ListBean {
            /**
             * goodsId : 1
             * merchantId : 18701646700
             * goodsName : aaaa
             * goodsPrice : 5
             * goodsAmount : 100
             * insertTime : Mar 29, 2019 1:55:03 AM
             * updateTime : Mar 29, 2019 1:55:06 AM
             */

            private int goodsId;
            private String merchantId;
            private String goodsName;
            private int goodsPrice;

            public int getCustomizationPrice() {
                return customizationPrice;
            }

            public void setCustomizationPrice(int customizationPrice) {
                this.customizationPrice = customizationPrice;
            }

            private int customizationPrice;

            public int getSelectCount() {
                return selectCount;
            }

            public void setSelectCount(int selectCount) {
                this.selectCount = selectCount;
            }

            private int selectCount;
            private int goodsAmount;
            private String insertTime;
            private String updateTime;

            @Override
            public String toString() {
                return "ListBean{" +
                        "goodsId=" + goodsId +
                        ", merchantId='" + merchantId + '\'' +
                        ", goodsName='" + goodsName + '\'' +
                        ", goodsPrice=" + goodsPrice +
                        ", goodsAmount=" + goodsAmount +
                        ", insertTime='" + insertTime + '\'' +
                        ", updateTime='" + updateTime + '\'' +
                        '}';
            }

            public int getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public String getMerchantId() {
                return merchantId;
            }

            public void setMerchantId(String merchantId) {
                this.merchantId = merchantId;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public int getGoodsPrice() {
                return goodsPrice;
            }

            public void setGoodsPrice(int goodsPrice) {
                this.goodsPrice = goodsPrice;
            }

            public int getGoodsAmount() {
                return goodsAmount;
            }

            public void setGoodsAmount(int goodsAmount) {
                this.goodsAmount = goodsAmount;
            }

            public String getInsertTime() {
                return insertTime;
            }

            public void setInsertTime(String insertTime) {
                this.insertTime = insertTime;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }
        }
    }
}
