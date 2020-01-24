package org.sample.capstone.proxy;

import org.sample.capstone.entity.LimitConfig;
import org.springframework.web.bind.annotation.GetMapping;

public interface ConfigProxy {
	@GetMapping("/limits")
	public LimitConfig getLimitConfigs() ;
}
