package com.bai.nfc.util;

public class Urls {
    final static String IP = "https://www.holofest.cn:8443/";

    public final static String login = IP + "merchantLogin.do";//18701646700  123456
    public final static String goodsList = IP + "queryGoodsList.do";//查询商户商品列表
    public final static String classifyList = IP + "queryClassifyList.do";//查询商户商品分类
    public final static String check_HandRing = IP + "checkHandRing.do";//校验手环是否可消费 String handRingId 手环ID handRingCode 手环密码
    public final static String consumHoloConin = IP + "consumHoloCoin.do";//客户消费Holo币接口
    public final static String queryOrderRecord = IP + "queryOrderRecord.do";//查询商户订单记录接口
    public final static String querySalesRecord = IP + "querySalesRecord.do";//查询订单商品记录接口
    public final static String queryOrderByMIdAndHId = IP + "queryOrderByMIdAndHId.do";//根据商户ID和手环ID查询订单
    public final static String refundHoloCoin = IP + "refundHoloCoin.do";//给客户退Holo币
    public final static String getDetailed = IP + "getDetailed.do";//收支明细
}
