package com.yoong.sunnyside.domain.real_estate.service

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import com.yoong.sunnyside.domain.real_estate.dto.CreateRealEstateDto
import com.yoong.sunnyside.domain.real_estate.dto.RealEstatePageResponseDto
import com.yoong.sunnyside.domain.real_estate.dto.RealEstateResponseDto
import com.yoong.sunnyside.domain.real_estate.dto.UpdateRealEstateDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface RealEstateService {

    fun createRealEstate(createRealEstateDto: CreateRealEstateDto): DefaultResponseDto

    fun getRealEstate(realEstateId: Long): RealEstateResponseDto

    fun getRealEstatePage(pageable: Pageable): Page<RealEstatePageResponseDto>

    fun updateRealEstate(realEstateId: Long, updateRealEstateDto: UpdateRealEstateDto): DefaultResponseDto

    fun deleteRealEstate(realEstateId: Long): DefaultResponseDto
}