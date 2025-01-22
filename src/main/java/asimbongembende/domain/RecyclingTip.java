package asimbongembende.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class RecyclingTip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tip;

    protected RecyclingTip() {
    }

    private RecyclingTip(Builder builder) {
        this.id = builder.id;
        this.tip = builder.tip;
    }

    public Long getId() {
        return id;
    }

    public String getTip() {
        return tip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecyclingTip that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getTip(), that.getTip());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTip());
    }

    public static class Builder {
        private Long id;
        private String tip;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setTip(String tip) {
            this.tip = tip;
            return this;
        }

        public RecyclingTip build() {
            return new RecyclingTip(this);
        }
    }
}