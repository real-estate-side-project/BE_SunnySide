package com.yoong.sunnyside.domain.real_estate.repository

import com.yoong.sunnyside.domain.real_estate.entity.RealEstate
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface RealEstateRepository {
    fun existsByAddress(address: String): Boolean
    fun save(realEstate: RealEstate): RealEstate
    fun findByIdOrNull(realEstateId: Long): RealEstate?
    fun findAll(pageable: Pageable): Page<RealEstate>
    fun saveAndFlush(realEstate: RealEstate): RealEstate
    fun existsById(realEstateId: Long): Boolean
}