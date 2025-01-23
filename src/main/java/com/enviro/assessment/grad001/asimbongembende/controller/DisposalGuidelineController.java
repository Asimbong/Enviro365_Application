package com.enviro.assessment.grad001.asimbongembende.controller;

import com.enviro.assessment.grad001.asimbongembende.domain.DisposalGuideline;
import com.enviro.assessment.grad001.asimbongembende.domain.WasteCategory;
import com.enviro.assessment.grad001.asimbongembende.service.DisposalGuidelineService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing DisposalGuidelines.
 */
@RestController
@RequestMapping("/api/disposal-guidelines/")
public class DisposalGuidelineController {
    private final DisposalGuidelineService service;

    /**
     * Constructor for DisposalGuidelineController.
     * @param service the DisposalGuidelineService to be used
     */
    public DisposalGuidelineController(DisposalGuidelineService service) {
        this.service = service;
    }

    /**
     * GET /get-all : Get all disposal guidelines.
     * @return a list of all DisposalGuidelines
     */
    @GetMapping("get-all")
    public List<DisposalGuideline> getAllDisposalGuidelines() {
        return service.findAll();
    }

    /**
     * GET /get/{id} : Get a disposal guideline by ID.
     * @param id the ID of the DisposalGuideline to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the DisposalGuideline, or with status 404 (Not Found)
     */
    @GetMapping("get/{id}")
    public ResponseEntity<DisposalGuideline> getDisposalGuidelineById(@PathVariable Long id) {
        Optional<DisposalGuideline> disposalGuideline = service.findById(id);
        return disposalGuideline.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * POST /create : Create a new disposal guideline.
     */
    @PostMapping("create")
    public DisposalGuideline createDisposalGuideline(@Validated @RequestBody DisposalGuideline disposalGuideline) {
        WasteCategory wasteCategory = disposalGuideline.getWasteCategory();
        return service.createDisposalGuideline(disposalGuideline.getGuideline(), disposalGuideline.getDisposalMethod(), wasteCategory);
    }

    /**
     * PUT /update/{id} : Update an existing disposal guideline.
     * @param id the ID of the DisposalGuideline to update
     * @param disposalGuideline the updated DisposalGuideline
     * @return the ResponseEntity with status 200 (OK) and with body the updated DisposalGuideline, or with status 404 (Not Found)
     */
    @PutMapping("update/{id}")
    public ResponseEntity<DisposalGuideline> updateDisposalGuideline(@PathVariable Long id, @Validated @RequestBody DisposalGuideline disposalGuideline) {
        if (service.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        WasteCategory wasteCategory = disposalGuideline.getWasteCategory();
        return ResponseEntity.ok(service.updateDisposalGuideline(id, disposalGuideline.getGuideline(), disposalGuideline.getDisposalMethod(), wasteCategory));
    }

    /**
     * DELETE /delete/{id} : Delete a disposal guideline by ID.
     * @param id the ID of the DisposalGuideline to delete
     * @return the ResponseEntity with status 204 (No Content), or with status 404 (Not Found)
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteDisposalGuideline(@PathVariable Long id) {
        if (service.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}