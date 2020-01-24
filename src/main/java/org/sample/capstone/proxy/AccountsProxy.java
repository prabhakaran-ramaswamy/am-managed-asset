package org.sample.capstone.proxy;

import org.sample.capstone.entity.Account;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="am-account-details" )
@RibbonClient(name ="am-account-details")
public interface AccountsProxy {
	
    @GetMapping(value="/accounts/{account}",name = "show")
    public ResponseEntity<Account> show(@PathVariable("account") Long account);

}
