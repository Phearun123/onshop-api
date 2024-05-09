package com.kosign.onshopapi.domain.seller;

import com.kosign.onshopapi.domain.product.Product;
import com.kosign.onshopapi.domain.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "sellers")
@Entity
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sid;

    @Column(name = "sllr_nm")
    private String seller_name;

    @Column(name = "bus_lic_num")
    private String business_license_number;

    @Column(name = "sllr_eml")
    private String seller_email;

    @Column(name = "sllr_ph_num")
    private String seller_phoneNumber;

    @Column(name = "sllr_addr")
    private String seller_address;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usr_id", nullable = false)
    private User user;

    @Builder
    public Seller(Long sid, String seller_name, String business_license_number, String seller_email, String seller_phoneNumber, String seller_address, List<Product> products) {
        this.sid = sid;
        this.seller_name = seller_name;
        this.business_license_number = business_license_number;
        this.seller_email = seller_email;
        this.seller_phoneNumber = seller_phoneNumber;
        this.seller_address = seller_address;
        this.products = products;
    }
}
