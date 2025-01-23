package com.enviro.assessment.grad001.asimbongembende.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class DisposalGuideline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String guideline;
    private String disposalMethod;

    @ManyToOne
    @JoinColumn(name = "waste_category_id")
    private WasteCategory wasteCategory;

    protected DisposalGuideline() {
    }

    private DisposalGuideline(Builder builder) {
        this.id = builder.id;
        this.guideline = builder.guideline;
        this.disposalMethod = builder.disposalMethod;
        this.wasteCategory = builder.wasteCategory;
    }

    public Long getId() {
        return id;
    }

    public String getGuideline() {
        return guideline;
    }

    public String getDisposalMethod() {
        return disposalMethod;
    }

    public WasteCategory getWasteCategory() {
        return wasteCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DisposalGuideline that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getGuideline(), that.getGuideline()) && Objects.equals(getDisposalMethod(), that.getDisposalMethod()) && Objects.equals(getWasteCategory(), that.getWasteCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getGuideline(), getDisposalMethod(), getWasteCategory());
    }

    public static class Builder {
        private Long id;
        private String guideline;
        private String disposalMethod;
        private WasteCategory wasteCategory;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setGuideline(String guideline) {
            this.guideline = guideline;
            return this;
        }

        public Builder setDisposalMethod(String disposalMethod) {
            this.disposalMethod = disposalMethod;
            return this;
        }

        public Builder setWasteCategory(WasteCategory wasteCategory) {
            this.wasteCategory = wasteCategory;
            return this;
        }

        public DisposalGuideline build() {
            return new DisposalGuideline(this);
        }
    }
}

