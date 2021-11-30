package com.congyu.springcloud.controller;

import com.congyu.springcloud.entities.CommonResult;
import com.congyu.springcloud.entities.Payment;
import com.congyu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value ="/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result  = paymentService.create(payment);
        if(result >0){
            return new CommonResult(200,"插入成功,serverPort:"+serverPort,result);
        }else{
            return  new CommonResult(444,"插入失败");
        }


    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long  id){
        Payment payment = paymentService.getPaymentById(id);
        if(payment !=null){
            return new CommonResult(200,"查询成功,serverPort:"+serverPort,payment);
        }else{
            return  new CommonResult(444,"无记录");
        }
    }


    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String element:services){
            log.info("****"+element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance:instances){
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }






        return discoveryClient;

    }
}
