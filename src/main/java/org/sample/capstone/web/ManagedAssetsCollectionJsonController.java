package org.sample.capstone.web;
import java.util.Collection;

import javax.validation.Valid;

import org.sample.capstone.entity.ManagedAssetView;
import org.sample.capstone.service.api.ManagedAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<ManagedAssetView>> list(Pageable pageable) {
        Page<ManagedAssetView> managedAssets = managedAssetService.findAll(pageable);
        return ResponseEntity.ok(managedAssets);
    }

    public static UriComponents listURI() {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(ManagedAssetsCollectionJsonController.class).list(null)).build().encode();
    }

    @PostMapping(name = "create")
    public ResponseEntity<?> create(@Valid @RequestBody ManagedAssetView managedAsset, BindingResult result) {
        if (managedAsset.getId() != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        ManagedAssetView newManagedAsset = managedAssetService.save(managedAsset);
        UriComponents showURI = ManagedAssetsItemJsonController.showURI(newManagedAsset);
        return ResponseEntity.created(showURI.toUri()).build();
    }

    @PostMapping(value = "/batch", name = "createBatch")
    public ResponseEntity<?> createBatch(@Valid @RequestBody Collection<ManagedAssetView> managedAssets, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        managedAssetService.save(managedAssets);
        return ResponseEntity.created(listURI().toUri()).build();
    }

    @PutMapping(value = "/batch", name = "updateBatch")
    public ResponseEntity<?> updateBatch(@Valid @RequestBody Collection<ManagedAssetView> managedAssets, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        managedAssetService.save(managedAssets);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/batch/{ids}", name = "deleteBatch")
    public ResponseEntity<?> deleteBatch(@PathVariable("ids") Collection<Long> ids) {
        managedAssetService.delete(ids);
        return ResponseEntity.ok().build();
    }
}
