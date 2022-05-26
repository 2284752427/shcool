package com.yc.bean;

import lombok.Data;

@Data
public class AlipayBean {
    /*商户订单号，必填*/
    private String out_trade_no;
    /*订单名称，必填*/
    private String subject;
    /*付款金额，必填*/
    private StringBuffer total_amount;
    /*超时时间参数*/
    private String timeout_express="10m";
    private String product_code="FAST_INSTANT_TRADE_PAY";

}
