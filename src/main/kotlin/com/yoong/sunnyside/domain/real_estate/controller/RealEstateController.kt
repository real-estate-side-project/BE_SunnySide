package com.yoong.sunnyside.domain.real_estate.controller

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import com.yoong.sunnyside.domain.real_estate.dto.CreateRealEstateDto
import com.yoong.sunnyside.domain.real_estate.dto.RealEstatePageResponseDto
import com.yoong.sunnyside.domain.real_estate.dto.RealEstateResponseDto
import com.yoong.sunnyside.domain.real_estate.dto.UpdateRealEstateDto
import com.yoong.sunnyside.domain.real_estate.service.RealEstateService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/real-estate")
class RealEstateController(
    private val realEstateService: RealEstateService
){

    @PostMapping
    fun createRealEstate(
        @RequestBody createRealEstateDto: CreateRealEstateDto
    ): ResponseEntity<DefaultResponseDto>
        = ResponseEntity.status(HttpStatus.CREATED).body(realEstateService.createRealEstate(createRealEstateDto))

    @GetMapping("/{real-estate-id}")
    fun getRealEstate(
        @PathVariable("real-estate-id") realEstateId: Long
    ): ResponseEntity<RealEstateResponseDto>
        = ResponseEntity.status(HttpStatus.OK).body(realEstateService.getRealEstate(realEstateId))

    @GetMapping
    fun getRealEstatePage(
        @PageableDefault(size = 10, page = 0) pageable: Pageable
    ): ResponseEntity<Page<RealEstatePageResponseDto>>
            = ResponseEntity.status(HttpStatus.OK).body(realEstateService.getRealEstatePage(pageable))

    @PutMapping("/{real-estate-id}")
    fun updateRealEstate(
        @PathVariable("real-estate-id") realEstateId: Long,
        @RequestBody updateRealEstateDto: UpdateRealEstateDto
    ): ResponseEntity<DefaultResponseDto>
            = ResponseEntity.status(HttpStatus.OK)
                .body(realEstateService.updateRealEstate(realEstateId, updateRealEstateDto))

    @DeleteMapping("/{real-estate-id}")
    fun deleteRealEstate(
        @PathVariable("real-estate-id") realEstateId: Long
    ): ResponseEntity<DefaultResponseDto>
            = ResponseEntity.status(HttpStatus.OK)
                .body(realEstateService.deleteRealEstate(realEstateId))


}