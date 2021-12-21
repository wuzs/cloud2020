package com.congyu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentHystrixCallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "callback---paymentInfo_OK";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "callback---paymentInfo_TimeOut";
    }
}
