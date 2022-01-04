package com.study.springcloud.dao;


import com.study.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * Created with IntelliJ IDEA.
 *
 * @Author: compass
 * @Date: 2021-12-29-19:49
 * @Version:1.0
 * @Description:
 */
@Mapper
@Repository
public interface PaymentDao {
      int create(Payment payment);
      Payment getPayment(@Param("id") Long id);
}
