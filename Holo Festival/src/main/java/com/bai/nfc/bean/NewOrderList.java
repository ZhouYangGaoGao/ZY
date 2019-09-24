package com.bai.nfc.bean;

import java.util.List;

public class NewOrderList {
    /**
     * errorCode : 00
     * errorMsg : 成功
     * pageInfo : {"pageNum":1,"pageSize":20,"size":8,"startRow":1,"endRow":8,"total":8,"pages":1,"list":[{"consumptionId":"a545d72c2e1643ee9429ecd0fa6a1fa4","customerId":"c9367c0e76fa4c959dffabbd7886618b","phoneNumber":"18611129804","consumptionAmount":761,"merchantId":"18701646700","consumptionTime":"1556827582160"},{"consumptionId":"a8d1e670451045e9903a817def29ea1f","customerId":"c9367c0e76fa4c959dffabbd7886618b","phoneNumber":"18611129804","consumptionAmount":100,"merchantId":"18701646700","consumptionTime":"1556825032193"},{"consumptionId":"09edefd3ffa54cb8975ca5d31792ce43","customerId":"c9367c0e76fa4c959dffabbd7886618b","phoneNumber":"18611129804","consumptionAmount":100,"merchantId":"18701646700","consumptionTime":"1556824519895"},{"consumptionId":"3185c1db23714b739176a80160901634","customerId":"c9367c0e76fa4c959dffabbd7886618b","phoneNumber":"18611129804","consumptionAmount":100,"merchantId":"18701646700","consumptionTime":"1556824325292"},{"consumptionId":"fc308e3c1f3c41abb3c9c8335f7a7070","customerId":"c9367c0e76fa4c959dffabbd7886618b","phoneNumber":"18611129804","consumptionAmount":1,"merchantId":"18701646700","consumptionTime":"1556823086926"},{"consumptionId":"000a40954fc54c18a6d68a4fa1427757","customerId":"c9367c0e76fa4c959dffabbd7886618b","phoneNumber":"18611129804","consumptionAmount":1,"merchantId":"18701646700","consumptionTime":"1556822842229"},{"consumptionId":"0be5ed7ea6044fd39f8fa0b02a6cb0bb","customerId":"c9367c0e76fa4c959dffabbd7886618b","phoneNumber":"18611129804","consumptionAmount":1,"merchantId":"18701646700","consumptionTime":"1556822598083"},{"consumptionId":"6e4eb4deef8747a4b65242815649755f","customerId":"7b8c532860824ba3831d6f50f801dafe","phoneNumber":"18701646700","consumptionAmount":10,"merchantId":"18701646700","consumptionTime":"1556821596743"}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1}
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
         * pageSize : 20
         * size : 8
         * startRow : 1
         * endRow : 8
         * total : 8
         * pages : 1
         * list : [{"consumptionId":"a545d72c2e1643ee9429ecd0fa6a1fa4","customerId":"c9367c0e76fa4c959dffabbd7886618b","phoneNumber":"18611129804","consumptionAmount":761,"merchantId":"18701646700","consumptionTime":"1556827582160"},{"consumptionId":"a8d1e670451045e9903a817def29ea1f","customerId":"c9367c0e76fa4c959dffabbd7886618b","phoneNumber":"18611129804","consumptionAmount":100,"merchantId":"18701646700","consumptionTime":"1556825032193"},{"consumptionId":"09edefd3ffa54cb8975ca5d31792ce43","customerId":"c9367c0e76fa4c959dffabbd7886618b","phoneNumber":"18611129804","consumptionAmount":100,"merchantId":"18701646700","consumptionTime":"1556824519895"},{"consumptionId":"3185c1db23714b739176a80160901634","customerId":"c9367c0e76fa4c959dffabbd7886618b","phoneNumber":"18611129804","consumptionAmount":100,"merchantId":"18701646700","consumptionTime":"1556824325292"},{"consumptionId":"fc308e3c1f3c41abb3c9c8335f7a7070","customerId":"c9367c0e76fa4c959dffabbd7886618b","phoneNumber":"18611129804","consumptionAmount":1,"merchantId":"18701646700","consumptionTime":"1556823086926"},{"consumptionId":"000a40954fc54c18a6d68a4fa1427757","customerId":"c9367c0e76fa4c959dffabbd7886618b","phoneNumber":"18611129804","consumptionAmount":1,"merchantId":"18701646700","consumptionTime":"1556822842229"},{"consumptionId":"0be5ed7ea6044fd39f8fa0b02a6cb0bb","customerId":"c9367c0e76fa4c959dffabbd7886618b","phoneNumber":"18611129804","consumptionAmount":1,"merchantId":"18701646700","consumptionTime":"1556822598083"},{"consumptionId":"6e4eb4deef8747a4b65242815649755f","customerId":"7b8c532860824ba3831d6f50f801dafe","phoneNumber":"18701646700","consumptionAmount":10,"merchantId":"18701646700","consumptionTime":"1556821596743"}]
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
             * consumptionId : a545d72c2e1643ee9429ecd0fa6a1fa4
             * customerId : c9367c0e76fa4c959dffabbd7886618b
             * phoneNumber : 18611129804
             * consumptionAmount : 761
             * merchantId : 18701646700
             * consumptionTime : 1556827582160
             */

            private String consumptionId;
            private String customerId;
            private String phoneNumber;
            private int consumptionAmount;
            private int payType;
            private int payWay;
            private String merchantId;
            private String body;
            private String consumptionTime;

            public String getBody() {
                return body;
            }

            public void setBody(String body) {
                this.body = body;
            }

            public int getPayType() {
                return payType;
            }

            public void setPayType(int payType) {
                this.payType = payType;
            }

            public int getPayWay() {
                return payWay;
            }

            public void setPayWay(int payWay) {
                this.payWay = payWay;
            }

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
