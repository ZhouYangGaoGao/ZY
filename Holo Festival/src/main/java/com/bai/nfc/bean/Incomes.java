package com.bai.nfc.bean;

import java.util.List;

public class Incomes {

    /**
     * errorCode : 00
     * errorMsg : 成功
     * detaileds : [{"goodsId":4,"goodsName":"dddd","goodsAmount":108,"goodsPrice":8,"quantity":48,"quantityR":140,"priceAll":-736},{"goodsId":6,"goodsName":"ffff","goodsAmount":104,"goodsPrice":10,"quantity":32,"quantityR":48,"priceAll":-160},{"goodsId":1,"goodsName":"aaaa","goodsAmount":84,"goodsPrice":5,"quantity":44,"quantityR":96,"priceAll":-260},{"goodsId":14,"goodsName":"zzz","goodsAmount":973,"goodsPrice":1,"quantity":28,"quantityR":17,"priceAll":11},{"goodsId":2,"goodsName":"bbbb","goodsAmount":88,"goodsPrice":6,"quantity":12,"quantityR":0,"priceAll":72},{"goodsId":3,"goodsName":"cccc","goodsAmount":91,"goodsPrice":7,"quantity":9,"quantityR":0,"priceAll":63},{"goodsId":5,"goodsName":"eeee","goodsAmount":91,"goodsPrice":9,"quantity":9,"quantityR":0,"priceAll":81},{"goodsId":8,"goodsName":"hhhh","goodsAmount":97,"goodsPrice":12,"quantity":3,"quantityR":0,"priceAll":36},{"goodsId":13,"goodsName":"yyy","goodsAmount":994,"goodsPrice":-1,"quantity":6,"quantityR":0,"priceAll":3274},{"goodsId":7,"goodsName":"gggg","goodsAmount":100,"goodsPrice":11,"quantity":0,"quantityR":0,"priceAll":0},{"goodsId":9,"goodsName":"iiii","goodsAmount":100,"goodsPrice":13,"quantity":0,"quantityR":0,"priceAll":0},{"goodsId":10,"goodsName":"jjjj","goodsAmount":100,"goodsPrice":14,"quantity":0,"quantityR":0,"priceAll":0},{"goodsId":11,"goodsName":"kkkk","goodsAmount":100,"goodsPrice":15,"quantity":0,"quantityR":0,"priceAll":0},{"goodsId":12,"goodsName":"xxx","goodsAmount":1000,"goodsPrice":-1,"quantity":0,"quantityR":0,"priceAll":0}]
     */

    private String errorCode;
    private String errorMsg;
    private List<DetailedsBean> detaileds;

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

    public List<DetailedsBean> getDetaileds() {
        return detaileds;
    }

    public void setDetaileds(List<DetailedsBean> detaileds) {
        this.detaileds = detaileds;
    }

    public static class DetailedsBean {
        /**
         * goodsId : 4
         * goodsName : dddd
         * goodsAmount : 108
         * goodsPrice : 8
         * quantity : 48
         * quantityR : 140
         * priceAll : -736
         */

        private int goodsId;
        private String goodsName;
        private int goodsAmount;
        private int goodsPrice;
        private int quantity;
        private int quantityR;
        private int priceAll;

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public int getGoodsAmount() {
            return goodsAmount;
        }

        public void setGoodsAmount(int goodsAmount) {
            this.goodsAmount = goodsAmount;
        }

        public int getGoodsPrice() {
            return goodsPrice;
        }

        public void setGoodsPrice(int goodsPrice) {
            this.goodsPrice = goodsPrice;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getQuantityR() {
            return quantityR;
        }

        public void setQuantityR(int quantityR) {
            this.quantityR = quantityR;
        }

        public int getPriceAll() {
            return priceAll;
        }

        public void setPriceAll(int priceAll) {
            this.priceAll = priceAll;
        }
    }
}
