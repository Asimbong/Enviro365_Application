package com.enviro.assessment.grad001.asimbongembende.controller;

import com.enviro.assessment.grad001.asimbongembende.domain.WasteCategory;
import com.enviro.assessment.grad001.asimbongembende.service.WasteCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing WasteCategories.
 */
@RestController
@RequestMapping("/api/waste-categories/")
@CrossOrigin(origins = "*")
public class WasteCategoryController {
    private final WasteCategoryService service;

    /**
     * Constructor for WasteCategoryController.
     *
     * @param service the WasteCategoryService to be used
     */
    public WasteCategoryController(WasteCategoryService service) {
        this.service = service;
    }

    /**
     * GET /get-all : Get all waste categories.
     *
     * @return a list of all WasteCategories
     */
    @GetMapping("get-all")
    public List<WasteCategory> getAllWasteCategories() {
        return service.findAll();
    }

    /**
     * GET /get/{id} : Get a waste category by ID.
     * @param id the ID of the WasteCategory to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the WasteCategory, or with status 404 (Not Found)
     */
    @GetMapping("get/{id}")
    public ResponseEntity<WasteCategory> getWasteCategoryById(@PathVariable Long id) {
        Optional<WasteCategory> wasteCategory = service.findById(id);
        return wasteCategory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * POST /create : Create a new waste category.
     * @param wasteCategory the WasteCategory to create
     * @return the created WasteCategory
     */
    @PostMapping("create")
    public WasteCategory createWasteCategory(@Validated @RequestBody WasteCategory wasteCategory) {
        return service.createWasteCategory(wasteCategory.getName(), wasteCategory.getPricePerKg());
    }

    /**
     * PUT /update/{id} : Update an existing waste category.
     * @param id the ID of the WasteCategory to update
     * @param wasteCategory the updated WasteCategory
     * @return the ResponseEntity with status 200 (OK) and with body the updated WasteCategory, or with status 404 (Not Found)
     */
    @PutMapping("update/{id}")
    public ResponseEntity<WasteCategory> updateWasteCategory(@PathVariable Long id, @Validated @RequestBody WasteCategory wasteCategory) {
        if (service.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.updateWasteCategory(id, wasteCategory.getName(), wasteCategory.getPricePerKg()));
    }

    /**
     * DELETE /delete/{id} : Delete a waste category by ID.
     *
     * @param id the ID of the WasteCategory to delete
     * @return the ResponseEntity with status 204 (No Content), or with status 404 (Not Found)
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteWasteCategory(@PathVariable Long id) {
        if (service.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}