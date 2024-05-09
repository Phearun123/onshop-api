package com.kosign.onshopapi.domain.user;

import com.kosign.onshopapi.domain.CreatableEntity;
import com.kosign.onshopapi.domain.seller.Seller;
import com.kosign.onshopapi.enums.AuthProvider;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends CreatableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @Column(name = "usr_nm")
    private String username;

    @Column(name = "pwd")
    private String password;

    @Column(name = "eml")
    private String email;

    @Column(name = "add")
    private String address;

    @Column(name = "phone_num")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private AuthProvider role;

    @Column(name = "sts", length = 1)
    @JdbcTypeCode(Types.CHAR)
    private String status;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Seller> sellers;

    @Builder
    public User(Long uid, String username, String password, String email, String address, String phoneNumber, AuthProvider role, String status, List<Seller> sellers) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.status = status;
        this.sellers = sellers;
    }
}
