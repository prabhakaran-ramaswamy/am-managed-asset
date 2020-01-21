package org.sample.capstone.web;

import javax.validation.Valid;

import org.sample.capstone.entity.ManagedAssert;
import org.sample.capstone.exception.NotFoundException;
import org.sample.capstone.service.api.ManagedAssertService;
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
@RequestMapping(value = "/managedasserts/{managedAssert}", name = "ManagedAssertsItemJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class ManagedAssertsItemJsonController {

    @Autowired
    private ManagedAssertService managedAssertService;

    public void setManagedAssertService(ManagedAssertService managedAssertService) {
        this.managedAssertService = managedAssertService;
    }


    @ModelAttribute
    public ManagedAssert getManagedAssert(@PathVariable("managedAssert") Long id) {
        ManagedAssert managedAssert = managedAssertService.findOne(id);
        if (managedAssert == null) {
            throw new NotFoundException(String.format("ManagedAssert with identifier '%s' not found", id));
        }
        return managedAssert;
    }

    @GetMapping(name = "show")
    public ResponseEntity<?> show(@ModelAttribute ManagedAssert managedAssert) {
        return ResponseEntity.ok(managedAssert);
    }

    public static UriComponents showURI(ManagedAssert managedAssert) {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(ManagedAssertsItemJsonController.class).show(managedAssert)).buildAndExpand(managedAssert.getId()).encode();
    }

    @PutMapping(name = "update")
    public ResponseEntity<?> update(@ModelAttribute ManagedAssert storedManagedAssert, @Valid @RequestBody ManagedAssert managedAssert, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        managedAssert.setId(storedManagedAssert.getId());
        managedAssertService.save(managedAssert);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute ManagedAssert managedAssert) {
        managedAssertService.delete(managedAssert);
        return ResponseEntity.ok().build();
    }
}
