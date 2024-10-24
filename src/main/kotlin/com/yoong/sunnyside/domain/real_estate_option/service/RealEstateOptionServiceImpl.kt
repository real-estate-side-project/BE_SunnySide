package com.yoong.sunnyside.domain.real_estate_option.service

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import com.yoong.sunnyside.domain.real_estate.repository.RealEstateRepository
import com.yoong.sunnyside.domain.real_estate_option.dto.CreateRealEstateListDto
import com.yoong.sunnyside.domain.real_estate_option.dto.DeleteRealEstateOptionDto
import com.yoong.sunnyside.domain.real_estate_option.entity.RealEstateOption
import com.yoong.sunnyside.domain.real_estate_option.repository.RealEstateOptionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RealEstateOptionServiceImpl(
    private val realEstateOptionRepository: RealEstateOptionRepository,
    private val realEstateRepository: RealEstateRepository,
): RealEstateOptionService {

    @Transactional
    override fun updateRealEstateOption(realEstateId: Long, createRealEstateListDto: CreateRealEstateListDto): DefaultResponseDto {

        val realEstate = realEstateRepository.findByIdOrNull(realEstateId) ?: throw RuntimeException("매물 정보가 존재 하지 않습니다")

        realEstateOptionRepository.saveAll(createRealEstateListDto.options.map { RealEstateOption(it, realEstate) })

        return DefaultResponseDto("매물 옵션 수정이 완료 되었습니다")
    }

    @Transactional
    override fun deleteRealEstateOption(realEstateId: Long, deleteRealEstateOptionDto: DeleteRealEstateOptionDto): DefaultResponseDto {

        if(!realEstateRepository.existsById(realEstateId)) throw RuntimeException("매물 정보가 존재 하지 않습니다")

        val realEstateOptions = realEstateOptionRepository.findAllByIdAndRealEstateId(realEstateId, deleteRealEstateOptionDto.optionIds)

        realEstateOptions.map {
            it.delete()
        }

        return DefaultResponseDto("매물 옵션 삭제가 완료 되었습니다")
    }
}