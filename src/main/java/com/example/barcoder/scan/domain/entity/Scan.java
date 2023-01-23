package com.example.barcoder.scan.domain.entity;

import com.example.barcoder.common.BaseEntity;
import com.example.barcoder.user.domain.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Scan extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    private String barcodeNumber;

    public Scan(User user) {
        this.userId = user;
    }
}
