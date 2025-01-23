package com.enviro.assessment.grad001.asimbongembende.domain;

import jakarta.persistence.*;
import jakarta.persistence.Entity;

import java.util.List;
import java.util.Objects;

/**
 * Entity class representing a WasteCategory.
 */
@Entity
public class WasteCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double pricePerKg;
    @OneToMany(mappedBy = "wasteCategory")
    private List<DisposalGuideline> disposalGuidelines;

    /**
     * Default constructor for JPA.
     */
    protected WasteCategory() {
    }

    /**
     * Constructor using the Builder pattern.
     * @param builder the Builder instance
     */
    private WasteCategory(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.pricePerKg = builder.pricePerKg;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public double getPricePerKg() {
        return pricePerKg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WasteCategory that)) return false;
        return Double.compare(getPricePerKg(), that.getPricePerKg()) == 0 && Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(disposalGuidelines, that.disposalGuidelines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPricePerKg(), disposalGuidelines);
    }

    /**
     * Builder class for WasteCategory.
     */
    public static class Builder {
        private Long id;
        private String name;
        private double pricePerKg;

        /**
         * Sets the ID of the WasteCategory.
         *
         * @param id the ID to set
         * @return the Builder instance
         */
        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        /**
         * Sets the name of the WasteCategory.
         *
         * @param name the name to set
         * @return the Builder instance
         */
        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the price per kilogram of the WasteCategory.
         * @param pricePerKg the price per kilogram to set
         * @return the Builder instance
         */
        public Builder setPricePerKg(double pricePerKg) {
            this.pricePerKg = pricePerKg;
            return this;
        }

        /**
         * Builds and returns a WasteCategory instance.
         * @return the created WasteCategory
         */
        public WasteCategory build() {
            return new WasteCategory(this);
        }
    }
}