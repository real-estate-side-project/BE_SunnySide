package com.yoong.sunnyside.domain.real_estate_option.service

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import com.yoong.sunnyside.domain.real_estate_option.dto.CreateRealEstateListDto
import com.yoong.sunnyside.domain.real_estate_option.dto.DeleteRealEstateOptionDto

interface RealEstateOptionService {
    fun updateRealEstateOption(realEstateId: Long, createRealEstateListDto: CreateRealEstateListDto): DefaultResponseDto
    fun deleteRealEstateOption(realEstateId: Long, deleteRealEstateOptionDto: DeleteRealEstateOptionDto): DefaultResponseDto
}