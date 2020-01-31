package org.sample.capstone.web;

import javax.validation.Valid;

import org.sample.capstone.entity.ManagedAsset;
import org.sample.capstone.exception.NotFoundException;
import org.sample.capstone.helper.ManagedAssetUtil;
import org.sample.capstone.model.ManagedAssetModel;
import org.sample.capstone.service.api.ManagedAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;


@RestController
@RequestMapping(value = "/managedassets/{managedAsset}", name = "ManagedAssetsItemJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class ManagedAssetsItemJsonController {

    @Autowired
    private ManagedAssetService managedAssetService;

    @ModelAttribute
    public ManagedAssetModel getManagedAsset(@PathVariable("managedAsset") Long id) {
        ManagedAsset managedAsset = managedAssetService.findOne(id);
        if (managedAsset == null) {
            throw new NotFoundException(String.format("ManagedAsset with identifier '%s' not found", id));
        }
        ManagedAssetModel managedAssetModel = ManagedAssetUtil.copyManagedAssetToManagedAssetModel(managedAsset);
        return managedAssetModel;
    }

    @GetMapping(name = "show")
    public ResponseEntity<?> show(@ModelAttribute ManagedAssetModel managedAssetModel) {
        return ResponseEntity.ok(managedAssetModel);
    }

    public static UriComponents showURI(ManagedAssetModel managedAssetModel) {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(ManagedAssetsItemJsonController.class).show(managedAssetModel)).buildAndExpand(managedAssetModel.getId()).encode();
    }

    @PutMapping(name = "update")
    public ResponseEntity<?> update(@ModelAttribute ManagedAssetModel storedManagedAsset, @Valid @RequestBody ManagedAssetModel managedAssetModel, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        ManagedAsset managedAsset = ManagedAssetUtil.copyManagedAssetModelToManagedAsset(managedAssetModel);
        managedAsset.setId(storedManagedAsset.getId());
        managedAssetService.save(managedAsset);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute ManagedAssetModel managedAsset) {
        managedAssetService.delete(ManagedAssetUtil.copyManagedAssetModelToManagedAsset(managedAsset));
        return ResponseEntity.ok().build();
    }
}
