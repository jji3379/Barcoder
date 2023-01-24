package com.example.barcoder.Lab;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static com.example.barcoder.item.domain.entity.QItem.item;

@RestController
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LabController {
    private final JPAQueryFactory jpaQueryFactory;

    @GetMapping("/jmeter/{id}")
    public Long getItem(@PathVariable Long id) {

        return jpaQueryFactory.select(item.id)
                .from(item)
                .where(item.id.eq(id))
                .fetchOne();
    }
}
