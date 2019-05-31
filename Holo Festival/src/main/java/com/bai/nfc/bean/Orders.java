package com.bai.nfc.bean;

import java.util.List;

public class Orders {

    /**
     * errorCode : 00
     * errorMsg : 成功
     * pageInfo : {"pageNum":1,"pageSize":10,"size":10,"startRow":1,"endRow":10,"total":10,"pages":1,"list":[{"consumptionId":"3621ebf5315f44948225eecd803c4bad","customerId":"373a7f1fca8d4689b98ae0db571a665c","phoneNumber":"13389216484","consumptionAmount":7,"merchantId":"18701646700","consumptionTime":"Apr 16, 2019 9:08:32 PM"},{"consumptionId":"5f295175adef453586ed912d3cd07cb0","customerId":"373a7f1fca8d4689b98ae0db571a665c","phoneNumber":"13389216484","consumptionAmount":1,"merchantId":"18701646700","consumptionTime":"Apr 16, 2019 9:18:25 PM"},{"consumptionId":"6e28941f325346949d7215dfc595b893","customerId":"373a7f1fca8d4689b98ae0db571a665c","phoneNumber":"13389216484","consumptionAmount":7,"merchantId":"18701646700","consumptionTime":"Apr 16, 2019 9:06:56 PM"},{"consumptionId":"98a517bffeac4faaa1933088bc02c979","customerId":"373a7f1fca8d4689b98ae0db571a665c","phoneNumber":"13389216484","consumptionAmount":16,"merchantId":"18701646700","consumptionTime":"Apr 16, 2019 9:34:57 PM"},{"consumptionId":"b48f2b93da00451eb0f6043f8199aea8","customerId":"373a7f1fca8d4689b98ae0db571a665c","phoneNumber":"13389216484","consumptionAmount":1,"merchantId":"18701646700","consumptionTime":"Apr 16, 2019 9:16:46 PM"},{"consumptionId":"b596bbff070f4136a24d448781cf1cd4","customerId":"373a7f1fca8d4689b98ae0db571a665c","phoneNumber":"13389216484","consumptionAmount":1,"merchantId":"18701646700","consumptionTime":"Apr 16, 2019 9:13:40 PM"},{"consumptionId":"d9bcdca2238a4f38829e986ae4ce146d","customerId":"373a7f1fca8d4689b98ae0db571a665c","phoneNumber":"13389216484","consumptionAmount":17,"merchantId":"18701646700","consumptionTime":"Apr 16, 2019 6:48:16 PM"},{"consumptionId":"ebe10e1ca66240ca83f221dbcce941ef","customerId":"373a7f1fca8d4689b98ae0db571a665c","phoneNumber":"13389216484","consumptionAmount":7,"merchantId":"18701646700","consumptionTime":"Apr 16, 2019 9:08:22 PM"},{"consumptionId":"f631e4c8b7644ffba65d5d92cab32c7c","customerId":"373a7f1fca8d4689b98ae0db571a665c","phoneNumber":"13389216484","consumptionAmount":7,"merchantId":"18701646700","consumptionTime":"Apr 16, 2019 8:28:48 PM"},{"consumptionId":"fbb768d6da9b4e5d9133b7eff3c7c7b0","customerId":"373a7f1fca8d4689b98ae0db571a665c","phoneNumber":"13389216484","consumptionAmount":1,"merchantId":"18701646700","consumptionTime":"Apr 16, 2019 9:20:47 PM"}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1}
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
         * pageNum : 1
         * pageSize : 10
         * size : 10
         * startRow : 1
         * endRow : 10
         * total : 10
         * pages : 1
         * list : [{"consumptionId":"3621ebf5315f44948225eecd803c4bad","customerId":"373a7f1fca8d4689b98ae0db571a665c","phoneNumber":"13389216484","consumptionAmount":7,"merchantId":"18701646700","consumptionTime":"Apr 16, 2019 9:08:32 PM"},{"consumptionId":"5f295175adef453586ed912d3cd07cb0","customerId":"373a7f1fca8d4689b98ae0db571a665c","phoneNumber":"13389216484","consumptionAmount":1,"merchantId":"18701646700","consumptionTime":"Apr 16, 2019 9:18:25 PM"},{"consumptionId":"6e28941f325346949d7215dfc595b893","customerId":"373a7f1fca8d4689b98ae0db571a665c","phoneNumber":"13389216484","consumptionAmount":7,"merchantId":"18701646700","consumptionTime":"Apr 16, 2019 9:06:56 PM"},{"consumptionId":"98a517bffeac4faaa1933088bc02c979","customerId":"373a7f1fca8d4689b98ae0db571a665c","phoneNumber":"13389216484","consumptionAmount":16,"merchantId":"18701646700","consumptionTime":"Apr 16, 2019 9:34:57 PM"},{"consumptionId":"b48f2b93da00451eb0f6043f8199aea8","customerId":"373a7f1fca8d4689b98ae0db571a665c","phoneNumber":"13389216484","consumptionAmount":1,"merchantId":"18701646700","consumptionTime":"Apr 16, 2019 9:16:46 PM"},{"consumptionId":"b596bbff070f4136a24d448781cf1cd4","customerId":"373a7f1fca8d4689b98ae0db571a665c","phoneNumber":"13389216484","consumptionAmount":1,"merchantId":"18701646700","consumptionTime":"Apr 16, 2019 9:13:40 PM"},{"consumptionId":"d9bcdca2238a4f38829e986ae4ce146d","customerId":"373a7f1fca8d4689b98ae0db571a665c","phoneNumber":"13389216484","consumptionAmount":17,"merchantId":"18701646700","consumptionTime":"Apr 16, 2019 6:48:16 PM"},{"consumptionId":"ebe10e1ca66240ca83f221dbcce941ef","customerId":"373a7f1fca8d4689b98ae0db571a665c","phoneNumber":"13389216484","consumptionAmount":7,"merchantId":"18701646700","consumptionTime":"Apr 16, 2019 9:08:22 PM"},{"consumptionId":"f631e4c8b7644ffba65d5d92cab32c7c","customerId":"373a7f1fca8d4689b98ae0db571a665c","phoneNumber":"13389216484","consumptionAmount":7,"merchantId":"18701646700","consumptionTime":"Apr 16, 2019 8:28:48 PM"},{"consumptionId":"fbb768d6da9b4e5d9133b7eff3c7c7b0","customerId":"373a7f1fca8d4689b98ae0db571a665c","phoneNumber":"13389216484","consumptionAmount":1,"merchantId":"18701646700","consumptionTime":"Apr 16, 2019 9:20:47 PM"}]
         * prePage : 0
         * nextPage : 0
         * isFirstPage : true
         * isLastPage : true
         * hasPreviousPage : false
         * hasNextPage : false
         * navigatePages : 8
         * navigatepageNums : [1]
         * navigateFirstPage : 1
         * navigateLastPage : 1
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
             * consumptionId : 3621ebf5315f44948225eecd803c4bad
             * customerId : 373a7f1fca8d4689b98ae0db571a665c
             * phoneNumber : 13389216484
             * consumptionAmount : 7
             * merchantId : 18701646700
             * consumptionTime : Apr 16, 2019 9:08:32 PM
             */

            private String consumptionId;
            private String customerId;
            private String phoneNumber;
            private int consumptionAmount;
            private String merchantId;
            private String consumptionTime;

            public String getConsumptionId() {
                return consumptionId;
            }

            public void setConsumptionId(String consumptionId) {
                this.consumptionId = consumptionId;
            }

            public String getCustomerId() {
                return customerId;
            }

            public void setCustomerId(String customerId) {
                this.customerId = customerId;
            }

            public String getPhoneNumber() {
                return phoneNumber;
            }

            public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
            }

            public int getConsumptionAmount() {
                return consumptionAmount;
            }

            public void setConsumptionAmount(int consumptionAmount) {
                this.consumptionAmount = consumptionAmount;
            }

            public String getMerchantId() {
                return merchantId;
            }

            public void setMerchantId(String merchantId) {
                this.merchantId = merchantId;
            }

            public String getConsumptionTime() {
                return consumptionTime;
            }

            public void setConsumptionTime(String consumptionTime) {
                this.consumptionTime = consumptionTime;
            }
        }
    }
}
