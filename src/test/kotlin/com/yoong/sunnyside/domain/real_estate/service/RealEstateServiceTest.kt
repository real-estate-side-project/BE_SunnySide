package com.yoong.sunnyside.domain.real_estate.service

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import com.yoong.sunnyside.domain.real_estate.dto.CreateRealEstateDto
import com.yoong.sunnyside.domain.real_estate.dto.UpdateRealEstateDto
import com.yoong.sunnyside.domain.real_estate.entity.RealEstate
import com.yoong.sunnyside.domain.real_estate.enum_class.GoodsType
import com.yoong.sunnyside.domain.real_estate.enum_class.HouseType
import com.yoong.sunnyside.domain.real_estate.repository.RealEstateJpaRepository
import com.yoong.sunnyside.domain.real_estate.repository.RealEstateRepository
import com.yoong.sunnyside.domain.real_estate.repository.RealEstateRepositoryImpl
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.mockk
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import java.time.LocalDateTime

class RealEstateServiceTest : StringSpec({

    val realEstateRepository = mockk<RealEstateRepository>()
    val realEstateService = RealEstateServiceImpl(realEstateRepository)

    "부동산 등록시에 같은 이름이 있을 경우 예외 처리"{

        val createRealEstateDto = CreateRealEstateDto(
            name = "부동산",
            address = "주소",
            completionDate = LocalDateTime.of(2021, 1, 1, 1, 0),
            houseType = HouseType.TOWN_HOUSE,
            goodsType = GoodsType.RENTAL,
            security = 1,
            rent = 3,
            floor = 1,
            maintenanceCost = 100000,
            roomCount = 2,
            contractPeriod = LocalDateTime.of(2021, 1, 1, 1, 0),
            description = "test",
            bathroomCount = 1,
            isParked = false,
            size = 1.0
        )

        every { realEstateRepository.existsByAddress("주소") } returns true
        every { realEstateRepository.save(any()) } returns tempRealEstate2

        shouldThrow<RuntimeException> {
            realEstateService.createRealEstate(createRealEstateDto)
        }.let{
            it.message shouldBe "중복 되는 매물 입니다"
        }

    }

    "부동산이 정상적 으로 등록이 되는지 확인"{

        val createRealEstateDto = CreateRealEstateDto(
            name = "부동산1",
            address = "주소",
            completionDate = LocalDateTime.of(2021, 1, 1, 1, 0),
            houseType = HouseType.TOWN_HOUSE,
            goodsType = GoodsType.RENTAL,
            security = 1,
            rent = 3,
            floor = 1,
            maintenanceCost = 100000,
            roomCount = 2,
            contractPeriod = LocalDateTime.of(2021, 1, 1, 1, 0),
            description = "test",
            bathroomCount = 1,
            isParked = false,
            size = 1.0
        )

        every { realEstateRepository.existsByAddress("주소") } returns false
        every { realEstateRepository.save(any()) } returns tempRealEstate2

        val result = realEstateService.createRealEstate(createRealEstateDto)

        result shouldBe DefaultResponseDto("매물 생성이 완료 되었습니다")
    }

    "부동산이 정상적 으로 수정이 되는지 확인"{

    val updateRealEstateDto = UpdateRealEstateDto(
        name = "부동산3",
        address = "주소2",
        completionDate = LocalDateTime.of(2021, 1, 1, 1, 0),
        houseType = HouseType.TOWN_HOUSE,
        goodsType = GoodsType.RENTAL,
        security = 1,
        rent = 3,
        floor = 1,
        maintenanceCost = 100000,
        roomCount = 2,
        contractPeriod = LocalDateTime.of(2021, 1, 1, 1, 0),
        description = "test",
        bathroomCount = 1,
        isParked = false,
        size = 1.0
    )

    every { realEstateRepository.findByIdOrNull(2L) } returns tempRealEstate2

    val result = realEstateService.updateRealEstate(2L, updateRealEstateDto)

    result shouldBe DefaultResponseDto("매물 수정이 완료 되었습니다")
    tempRealEstate2.name shouldBe "부동산3"
    tempRealEstate2.address shouldBe "주소2"
}

    "부동산이 정상적 으로 삭제가 되는지 확인"{

        every { realEstateRepository.findByIdOrNull(2L) } returns tempRealEstate2

        val result = realEstateService.deleteRealEstate(2L)

        result shouldBe DefaultResponseDto("매물 삭제가 완료 되었습니다")

        tempRealEstate2.deletedAt shouldNotBe null
    }

}){

    companion object{

        val tempRealEstate = RealEstate(
            businessId = 1L,
            name = "부동산",
            address = "주소",
            completionDate = LocalDateTime.of(2021, 1, 1, 1, 0),
            houseType = HouseType.TOWN_HOUSE,
            goodsType = GoodsType.RENTAL,
            security = 1,
            rent = 3,
            houseSize = 1.0,
            floor = 1,
            maintenanceCost = 100000,
            roomCount = 2,
            contractPeriod = LocalDateTime.of(2021, 1, 1, 1, 0),
            description = "test",
            bathroomCount = 1,
            isParked = false,
            rate = 1.0
        )

        val tempRealEstate2 = RealEstate(
            businessId = 2L,
            name = "부동산1",
            address = "주소",
            completionDate = LocalDateTime.of(2021, 1, 1, 1, 0),
            houseType = HouseType.TOWN_HOUSE,
            goodsType = GoodsType.RENTAL,
            security = 1,
            rent = 3,
            houseSize = 1.0,
            floor = 1,
            maintenanceCost = 100000,
            roomCount = 2,
            contractPeriod = LocalDateTime.of(2021, 1, 1, 1, 0),
            description = "test",
            bathroomCount = 1,
            isParked = false,
            rate = 1.0
        )
    }
}
