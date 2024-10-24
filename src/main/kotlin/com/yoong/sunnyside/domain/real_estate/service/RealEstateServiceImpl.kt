package com.yoong.sunnyside.domain.real_estate.service

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import com.yoong.sunnyside.domain.real_estate.dto.CreateRealEstateDto
import com.yoong.sunnyside.domain.real_estate.dto.RealEstatePageResponseDto
import com.yoong.sunnyside.domain.real_estate.dto.RealEstateResponseDto
import com.yoong.sunnyside.domain.real_estate.dto.UpdateRealEstateDto
import com.yoong.sunnyside.domain.real_estate.entity.RealEstate
import com.yoong.sunnyside.domain.real_estate.repository.RealEstateRepository
import com.yoong.sunnyside.domain.real_estate_option.entity.RealEstateOption
import com.yoong.sunnyside.domain.real_estate_option.repository.RealEstateOptionRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RealEstateServiceImpl(
    private val realEstateRepository: RealEstateRepository,
    private val realEstateOptionRepository: RealEstateOptionRepository
): RealEstateService {

    @Transactional
    override fun createRealEstate(createRealEstateDto: CreateRealEstateDto): DefaultResponseDto {

        // 협의 필요
        if(realEstateRepository.existsByAddress(createRealEstateDto.address)) throw RuntimeException("중복 되는 매물 입니다")

        val realEstate = realEstateRepository.saveAndFlush(
            //business 테이블 이 없는 관계로 우선 1L 로 설정
            RealEstate(1L, createRealEstateDto)
        )

        realEstateOptionRepository.saveAll(createRealEstateDto.options.map { RealEstateOption(it, realEstate) })

        return DefaultResponseDto("매물 생성이 완료 되었습니다")
    }

    override fun getRealEstate(realEstateId: Long): RealEstateResponseDto {

        val realEstate = realEstateRepository.findByIdOrNull(realEstateId) ?: throw RuntimeException("해당 매물이 존재 하지 않습니다")

        val options = realEstateOptionRepository.findAllByRealEstateId(realEstateId)

        return RealEstateResponseDto.from(realEstate, options)
    }

    override fun getRealEstatePage(pageable: Pageable): Page<RealEstatePageResponseDto> {

        val realEstatePage = realEstateRepository.findAll(pageable)

        return realEstatePage.map { RealEstatePageResponseDto.from(it) }
    }

    @Transactional
    override fun updateRealEstate(realEstateId: Long, updateRealEstateDto: UpdateRealEstateDto): DefaultResponseDto {

        val realEstate = realEstateRepository.findByIdOrNull(realEstateId) ?: throw RuntimeException("해당 매물이 존재 하지 않습니다")

        realEstate.update(updateRealEstateDto)

        return DefaultResponseDto("매물 수정이 완료 되었습니다")

    }

    @Transactional
    override fun deleteRealEstate(realEstateId: Long): DefaultResponseDto {

        val realEstate = realEstateRepository.findByIdOrNull(realEstateId) ?: throw RuntimeException("해당 매물이 존재 하지 않습니다")

        realEstate.delete()

        return DefaultResponseDto("매물 삭제가 완료 되었습니다")
    }
}