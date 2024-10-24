package com.yoong.sunnyside.domain.real_estate.repository

import com.yoong.sunnyside.domain.real_estate.entity.RealEstate
import org.springframework.data.jpa.repository.JpaRepository

interface RealEstateJpaRepository: JpaRepository<RealEstate, Long> {

    fun existsByAddress(address: String): Boolean
}