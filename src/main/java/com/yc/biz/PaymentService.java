package com.yc.biz;

import com.yc.bean.AlipayBean;

public interface PaymentService {
    public String toAlipay(AlipayBean alipayBean);
}
