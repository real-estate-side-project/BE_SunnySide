package com.yoong.sunnyside.domain.real_estate_option.controller

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import com.yoong.sunnyside.domain.real_estate_option.dto.CreateRealEstateListDto
import com.yoong.sunnyside.domain.real_estate_option.dto.DeleteRealEstateOptionDto
import com.yoong.sunnyside.domain.real_estate_option.service.RealEstateOptionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/real-estate/option")
class RealEstateOptionController(
    private val realEstateOptionService: RealEstateOptionService
){

    @PostMapping("/{realEstateId}")
    fun updateRealEstateOption(
        @PathVariable("realEstateId") realEstateId: Long,
        @RequestBody createRealEstateListDto: CreateRealEstateListDto
    ): ResponseEntity<DefaultResponseDto> = ResponseEntity.status(HttpStatus.OK).body(realEstateOptionService.updateRealEstateOption(realEstateId, createRealEstateListDto))

    @DeleteMapping("/{realEstateId}")
    fun deleteRealEstateOption(
        @PathVariable("realEstateId") realEstateId: Long,
        @RequestBody deleteRealEstateOptionDto: DeleteRealEstateOptionDto
    ): ResponseEntity<DefaultResponseDto> = ResponseEntity.status(HttpStatus.OK).body(realEstateOptionService.deleteRealEstateOption(realEstateId, deleteRealEstateOptionDto))
}