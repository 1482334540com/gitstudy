package com.study.springcloud.service;

import com.study.springcloud.dao.PaymentDao;
import com.study.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: compass
 * @Date: 2021-12-29-20:09
 * @Version:1.0
 * @Description:
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPayment(Long id) {
        return paymentDao.getPayment(id);
    }
}
