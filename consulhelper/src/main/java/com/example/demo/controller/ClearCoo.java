package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.agent.model.Member;
import com.ecwid.consul.v1.agent.model.Service;
import com.ecwid.consul.v1.health.model.Check;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "清除consul",tags = "清除consul注册的无用信息")
public class ClearCoo {

    @Autowired
    private ConsulClient consulClient;
    @ApiOperation(value="剔除所有无效的服务实例", notes="剔除所有无效的服务实例")
    @RequestMapping(value="/allservicer",method= RequestMethod.GET)
    public void getAllServicer(@ApiParam(value = "服务地址") @RequestParam String address, @ApiParam(value = "端口号")  @RequestParam int port){
                ConsulClient  clearClient =new ConsulClient(address,port);
                //根据clearClient，获取当前IP下所有的服务 使用迭代方式 获取map对象的值
                Iterator<Map.Entry<String,Service>> it =clearClient.getAgentServices().getValue().entrySet().iterator();
                while (it.hasNext()){
                    //迭代数据
                    Map.Entry<String,Service> serviceMap =  it.next();
                    //获得Service对象
                    Service service = serviceMap.getValue();
                    //获取服务名称
                    String serviceName = service.getService();
                    if(serviceName.equals("consul")){
                        continue;
                    }
                    //获取服务ID
                    String serviceId = service.getId();
                    //根据服务名称获取服务的健康检查信息
                    Response<List<Check>> checkList = consulClient.getHealthChecksForService(serviceName, null);
                    System.out.println(JSON.toJSONString(checkList));
                    List<Check> checks = checkList.getValue();
                    if(checks.size() == 0){
                        continue;
                    }
                    System.out.println(JSON.toJSONString(checks));
                    //获取健康状态值  PASSING：正常  WARNING  CRITICAL  UNKNOWN：不正常
                    checks.forEach(c ->{
                        if (c.getStatus() != Check.CheckStatus.PASSING){
                            clearClient.agentServiceDeregister(c.getServiceId());
                        }
                    });
              }
    }

    /**
     *
     */
    @ApiOperation(value="根据服务名清理", notes="根据服务名清理实例")
    @RequestMapping(value="/clearShaBi",method= RequestMethod.GET)
    public void clearBitch(@ApiParam(value = "服务名" ) @RequestParam String  serviceName){
        Response<List<Check>> checkList = consulClient.getHealthChecksForService(serviceName, null);
        System.out.println(JSON.toJSONString(checkList));
        List<Check> checks = checkList.getValue();
        System.out.println(JSON.toJSONString(checks));
        checks.forEach(c ->{
            consulClient.agentServiceDeregister(c.getServiceId());
        });
    }

}
