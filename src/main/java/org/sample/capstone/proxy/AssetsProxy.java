package org.sample.capstone.proxy;

import org.sample.capstone.entity.AssetDetail;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="am-asset-details" )
@RibbonClient(name ="am-asset-details")
public interface AssetsProxy {

	@GetMapping(value="/assetdetails/{assetDetail}",name = "show")
	public ResponseEntity<AssetDetail> show(@PathVariable("assetDetail") Long assetDetail);

}
