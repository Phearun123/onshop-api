package com.kosign.onshopapi.domain.product;

import com.kosign.onshopapi.domain.UpdatableEntity;
import com.kosign.onshopapi.domain.category.Category;
import com.kosign.onshopapi.domain.seller.Seller;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "products")
@Entity
public class Product extends UpdatableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @Column(name = "pro_nm")
    private String product_name;

    @Column(name = "pro_desc")
    private String product_description;

    @Column(name = "quan_avail")
    private String quantity_available;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sllr_id")
    private Seller seller;

    @Builder
    public Product(Long pid, String product_name, String product_description, String quantity_available, Category category, Seller seller) {
        this.pid = pid;
        this.product_name = product_name;
        this.product_description = product_description;
        this.quantity_available = quantity_available;
        this.category = category;
        this.seller = seller;
    }
}
