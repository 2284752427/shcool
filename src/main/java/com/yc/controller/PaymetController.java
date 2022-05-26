package com.yc.controller;

import com.yc.bean.AlipayBean;
import com.yc.biz.PaymentService;
import com.yc.config.AlipayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class PaymetController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    AlipayConfig alipayConfig;

    @PostMapping("/alipay")
    public String toAlipay(StringBuffer total_amount, String subject, String out_trade_no, HttpSession session) throws IOException {
        AlipayBean alipayBean = new AlipayBean();
        alipayBean.setTotal_amount(total_amount);
        alipayBean.setOut_trade_no(out_trade_no);
        alipayBean.setSubject(subject);
        String result = paymentService.toAlipay(alipayBean);
        session.setAttribute("result",result);
        return result;
    }
}
