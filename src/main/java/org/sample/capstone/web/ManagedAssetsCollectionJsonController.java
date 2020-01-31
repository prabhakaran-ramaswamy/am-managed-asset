package org.sample.capstone.web;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.sample.capstone.entity.ManagedAsset;
import org.sample.capstone.helper.ManagedAssetUtil;
import org.sample.capstone.model.ManagedAssetModel;
import org.sample.capstone.service.api.ManagedAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;


@RestController
@RequestMapping(value = "/managedassets", name = "ManagedAssetsCollectionJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class ManagedAssetsCollectionJsonController {

    @Autowired
    private ManagedAssetService managedAssetService;

   
    @GetMapping(name = "list")
    public ResponseEntity<Page<ManagedAssetModel>> list(Pageable pageable) {
        Page<ManagedAsset> managedAssets = managedAssetService.findAll(pageable);
        List<ManagedAssetModel> managedAssetsModels = ManagedAssetUtil.copyManagedAssetsToManagedAssetModels(managedAssets.getContent());
        Page<ManagedAssetModel> page = new PageImpl<>(managedAssetsModels, managedAssets.getPageable(), managedAssetsModels.size());
        return ResponseEntity.ok(page);
    }

    public static UriComponents listURI() {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(ManagedAssetsCollectionJsonController.class).list(null)).build().encode();
    }

    @PostMapping(name = "create")
    public ResponseEntity<?> create(@Valid @RequestBody ManagedAssetModel managedAssetModel, BindingResult result) {
        if (managedAssetModel.getId() != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        ManagedAsset managedAsset = ManagedAssetUtil.copyManagedAssetModelToManagedAsset(managedAssetModel);
        ManagedAsset newManagedAsset = managedAssetService.save(managedAsset);
        UriComponents showURI = ManagedAssetsItemJsonController.showURI(ManagedAssetUtil.copyManagedAssetToManagedAssetModel(newManagedAsset));
        return ResponseEntity.created(showURI.toUri()).build();
    }

    @PostMapping(value = "/batch", name = "createBatch")
    public ResponseEntity<?> createBatch(@Valid @RequestBody Collection<ManagedAssetModel> managedAssetModels, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        managedAssetService.save(ManagedAssetUtil.copyManagedAssetModelsToManagedAssets(new ArrayList<ManagedAssetModel>(managedAssetModels)));
        return ResponseEntity.created(listURI().toUri()).build();
    }

    @PutMapping(value = "/batch", name = "updateBatch")
    public ResponseEntity<?> updateBatch(@Valid @RequestBody Collection<ManagedAssetModel> managedAssetModels, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        managedAssetService.save(ManagedAssetUtil.copyManagedAssetModelsToManagedAssets(new ArrayList<ManagedAssetModel>(managedAssetModels)));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/batch/{ids}", name = "deleteBatch")
    public ResponseEntity<?> deleteBatch(@PathVariable("ids") Collection<Long> ids) {
        managedAssetService.delete(ids);
        return ResponseEntity.ok().build();
    }
}
