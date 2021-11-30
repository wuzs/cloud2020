package com.congyu.springcloud.service;

import com.congyu.springcloud.entities.Payment;

public interface PaymentService {

    int create(Payment payment);
    Payment getPaymentById(Long id);
}
