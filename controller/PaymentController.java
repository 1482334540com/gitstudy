package com.study.springcloud.controller;


import com.study.springcloud.CommonResult;
import com.study.springcloud.entities.Payment;
import com.study.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: compass
 * @Date: 2021-12-29-20:14
 * @Version:1.0
 * @Description:
 */
@Controller
@Slf4j
@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    // 拿到配置文件中的port
    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("插入结果：{}",result);
        if (result>0){
            return new CommonResult(200,"插入成功："+serverPort,result);
        }
        return new CommonResult(500,"插入失败："+serverPort,null);
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPayment(@PathVariable(name = "id")Long id){
        System.out.println("id="+id);
        int a=0/2;
        Payment result = paymentService.getPayment(id);
        log.info("查询结果：{}",result);
        if (result != null){
            return new CommonResult(200,"查询成功： "+serverPort,result);
        }
        return new CommonResult(500,"没有对应记录："+serverPort,null);
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery()
    {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("*****element: "+element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }

        return discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;//返回服务接口
    }

    @GetMapping("/payment/feign/timout")
    public String paymentFeignTimout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return serverPort;
    }



}
