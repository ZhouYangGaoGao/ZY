package com.bai.nfc.util;

import com.alibaba.fastjson.JSON;
import com.bai.nfc.bean.GoodsList;
import com.bai.nfc.bean.OrderDsc;
import com.orhanobut.hawk.Hawk;
import com.zhy.zlib.listener.CommonListener;
import com.zhy.zlib.utils.OKUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestUtil {

    /**
     * 登录
     *
     * @param merchantId 商户 id
     * @param password   商户密码
     * @param listener   回调
     */
    public static void login(String merchantId, String password, CommonListener listener) {
        Map map = new HashMap();
        map.put(Constant.MERCHAN_ID, merchantId);
        map.put(Constant.PASSWORD, password);
        OKUtils.postJson(Urls.login, Urls.login, JSON.toJSONString(map), listener);
    }

    /**
     * 查询商户商品类别
     *
     * @param merchantId 商户 id
     * @param listener   回调
     */
    public static void classifyList(String merchantId, CommonListener listener) {
        Map map = new HashMap();
        map.put(Constant.MERCHAN_ID, Hawk.get(Constant.MERCHAN_ID));
        OKUtils.postJson(Urls.classifyList, Urls.classifyList, JSON.toJSONString(map), listener);
    }


    /**
     * 商户商品列表
     *
     * @param page     页码
     * @param listener 回调
     */
    public static void goodsList(int page, CommonListener listener) {
        Map pageInfo = new HashMap();
        pageInfo.put(Constant.PAGE_NUM, 0);
        pageInfo.put(Constant.PAGE_SIZE, "0");

        Map map = new HashMap();
        map.put(Constant.MERCHAN_ID, Hawk.get(Constant.MERCHAN_ID));
        map.put(Constant.CLASSIFY_ID, "1");
        map.put(Constant.GOODS_NAME, "");
        map.put(Constant.PAGE_INFO, pageInfo);
        OKUtils.postJson(Urls.goodsList, Urls.goodsList, JSON.toJSONString(map), listener);
    }

    /**
     * 手环检验
     *
     * @param handRingId   手环 id
     * @param handRingCode 手环密码
     * @param listener     回调
     */
    public static void checkHandRing(String handRingId,
                                     String handRingCode, CommonListener listener) {
        Map map = new HashMap();
        map.put(Constant.HAND_RING_ID, handRingId);
        map.put(Constant.HAND_RING_CODE, handRingCode);
        OKUtils.postJson(Urls.check_HandRing, Urls.check_HandRing, JSON.toJSONString(map), listener);
    }

    /**
     * 消费 Holo币
     *
     * @param customerId 客户 id
     * @param listener   回调
     */
    public static void consumHoloConin(String customerId,
                                       List<GoodsList.PageInfoBean.ListBean> goods, CommonListener listener) {
        List<Map<String, Object>> buyJournals = new ArrayList<>();

        for (int i = 0; i < goods.size(); i++) {
            Map one = new HashMap();
            GoodsList.PageInfoBean.ListBean good = goods.get(i);
            one.put(Constant.GOODS_ID, good.getGoodsId());
            one.put(Constant.QUANTITY, good.getSelectCount());
            if (good.getGoodsPrice() == -1) {
                one.put(Constant.PRICE, good.getCustomizationPrice());
            }
            buyJournals.add(one);
        }
        Map map = new HashMap();
        map.put(Constant.COUSTOMER_ID, customerId);
        map.put(Constant.MERCHAN_ID, Hawk.get(Constant.MERCHAN_ID));
        map.put(Constant.BUY_JOURNALs, buyJournals);
        OKUtils.postJson(Urls.consumHoloConin, Urls.consumHoloConin, JSON.toJSONString(map), listener);
    }

    /**
     * 查询商户订单记录
     *
     * @param pageNum  页码
     * @param listener 回调
     */
    public static void queryOrderRecord(
            int pageNum, CommonListener listener) {
        Map pageInfo = new HashMap();
        pageInfo.put(Constant.PAGE_NUM, pageNum);
        pageInfo.put(Constant.PAGE_SIZE, "20");

        Map map = new HashMap();
        map.put(Constant.MERCHAN_ID, Hawk.get(Constant.MERCHAN_ID));
        map.put(Constant.PAGE_INFO, pageInfo);
        OKUtils.postJson(Urls.queryOrderRecord, Urls.queryOrderRecord, JSON.toJSONString(map), listener);
    }

    /**
     * 查询订单商品记录
     *
     * @param consumptionId 订单流水 id
     * @param listener      回调
     */
    public static void querySalesRecord(String consumptionId, CommonListener listener) {
        Map map = new HashMap();
        map.put(Constant.MERCHAN_ID, Hawk.get(Constant.MERCHAN_ID));
        map.put(Constant.CONSUMPTION_ID, consumptionId);
        OKUtils.postJson(Urls.querySalesRecord, Urls.querySalesRecord, JSON.toJSONString(map), listener);
    }

    /**
     * 根据商户ID和手环ID查询订单
     *
     * @param handRingId
     * @param listener
     */
    public static void queryOrderByMIdAndHId(String handRingId, CommonListener listener) {
        Map map = new HashMap();
        map.put(Constant.HAND_RING_ID, handRingId);
        map.put(Constant.MERCHAN_ID, Hawk.get(Constant.MERCHAN_ID));
        OKUtils.postJson(Urls.queryOrderByMIdAndHId, Urls.queryOrderByMIdAndHId,
                JSON.toJSONString(map), listener);
    }

    /**
     * 收支明细
     *
     * @param listener
     */
    public static void getDetailed(CommonListener listener) {
        Map map = new HashMap();
        map.put(Constant.MERCHAN_ID, Hawk.get(Constant.MERCHAN_ID));
        OKUtils.postJson(Urls.getDetailed, Urls.getDetailed,
                JSON.toJSONString(map), listener);
    }

    /**
     * 给客户退Holo币
     *
     * @param customerId
     * @param listener
     */
    public static void refundHoloCoin(List<OrderDsc.BuyJournalsBean> goods,
                                      String customerId, CommonListener listener) {

        List<Map<String, Object>> buyJournals = new ArrayList<>();

        for (int i = 0; i < goods.size(); i++) {
            Map one = new HashMap();
            OrderDsc.BuyJournalsBean good = goods.get(i);
            one.put(Constant.BUY_JOURNAL_ID, good.getBuyJournalId());
            one.put(Constant.GOODS_ID, good.getGoodsId());
            one.put(Constant.QUANTITY, good.getSelectCount());
            buyJournals.add(one);
        }

        Map map = new HashMap();
        map.put(Constant.MERCHAN_ID, Hawk.get(Constant.MERCHAN_ID));
        map.put(Constant.COUSTOMER_ID, customerId);
        map.put(Constant.CONSUMPTION_ID, goods.get(0).getConsumptionId());
        map.put(Constant.BUY_JOURNALs, buyJournals);
        OKUtils.postJson(Urls.refundHoloCoin, Urls.refundHoloCoin, JSON.toJSONString(map), listener);


    }
}
