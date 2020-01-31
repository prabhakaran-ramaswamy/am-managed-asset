package org.sample.capstone.proxy;

import org.sample.capstone.entity.LimitConfig;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="am-config-service" )
@RibbonClient(name ="am-config-service")
public interface ConfigProxy {
	@GetMapping("/limits")
	public LimitConfig getLimitConfigs() ;
}
