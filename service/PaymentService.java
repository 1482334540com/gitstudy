package com.study.springcloud.service;


import com.study.springcloud.entities.Payment;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: compass
 * @Date: 2021-12-29-20:08
 * @Version:1.0
 * @Description:
 */

public interface PaymentService {
    int create(Payment payment);
    Payment getPayment(Long id);
}
