package org.sample.capstone.web;
import java.util.Collection;

import javax.validation.Valid;

import org.sample.capstone.entity.ManagedAssert;
import org.sample.capstone.service.api.ManagedAssertService;
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
@RequestMapping(value = "/managedasserts", name = "ManagedAssertsCollectionJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class ManagedAssertsCollectionJsonController {

    @Autowired
    private ManagedAssertService managedAssertService;

   
    @GetMapping(name = "list")
    public ResponseEntity<Page<ManagedAssert>> list(Pageable pageable) {
        Page<ManagedAssert> managedAsserts = managedAssertService.findAll(pageable);
        return ResponseEntity.ok(managedAsserts);
    }

    public static UriComponents listURI() {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(ManagedAssertsCollectionJsonController.class).list(null)).build().encode();
    }

    @PostMapping(name = "create")
    public ResponseEntity<?> create(@Valid @RequestBody ManagedAssert managedAssert, BindingResult result) {
        if (managedAssert.getId() != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        ManagedAssert newManagedAssert = managedAssertService.save(managedAssert);
        UriComponents showURI = ManagedAssertsItemJsonController.showURI(newManagedAssert);
        return ResponseEntity.created(showURI.toUri()).build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param managedAsserts
     * @param result
     * @return ResponseEntity
     */
    @PostMapping(value = "/batch", name = "createBatch")
    public ResponseEntity<?> createBatch(@Valid @RequestBody Collection<ManagedAssert> managedAsserts, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        managedAssertService.save(managedAsserts);
        return ResponseEntity.created(listURI().toUri()).build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param managedAsserts
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(value = "/batch", name = "updateBatch")
    public ResponseEntity<?> updateBatch(@Valid @RequestBody Collection<ManagedAssert> managedAsserts, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        managedAssertService.save(managedAsserts);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return ResponseEntity
     */
    @DeleteMapping(value = "/batch/{ids}", name = "deleteBatch")
    public ResponseEntity<?> deleteBatch(@PathVariable("ids") Collection<Long> ids) {
        managedAssertService.delete(ids);
        return ResponseEntity.ok().build();
    }
}
