package com.yoong.sunnyside.domain.real_estate_option.repository

import com.yoong.sunnyside.domain.real_estate_option.entity.RealEstateOption
import org.springframework.data.jpa.repository.JpaRepository

interface RealEstateOptionJpaRepository: JpaRepository<RealEstateOption, Long> {

    fun findAllByRealEstateId(realEstateId: Long): List<RealEstateOption>
}