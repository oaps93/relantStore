package com.store.relantStore.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.Date;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Not empty name for a product")
    private String name;
    private String description;
    @Positive(message = "No negative values allowed")
    private Integer stock;
    private Double price;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @NotNull(message = "Not null category allowed")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categories_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Category category;
}
