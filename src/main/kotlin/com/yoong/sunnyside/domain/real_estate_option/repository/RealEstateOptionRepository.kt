package com.yoong.sunnyside.domain.real_estate_option.repository

import com.yoong.sunnyside.domain.real_estate_option.entity.RealEstateOption

interface RealEstateOptionRepository {

    fun saveAll(options: List<RealEstateOption>)

    fun findAllByRealEstateId(realEstateId: Long): List<RealEstateOption>

    fun findAllByIdAndRealEstateId(realEstateId: Long, ids: List<Long>): List<RealEstateOption>
}