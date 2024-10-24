package com.yoong.sunnyside.domain.real_estate.dto

import com.yoong.sunnyside.domain.real_estate.enum_class.GoodsType
import com.yoong.sunnyside.domain.real_estate.enum_class.HouseType
import java.time.LocalDateTime

data class UpdateRealEstateDto (
    var name: String,
    var address: String,
    var completionDate: LocalDateTime,
    var houseType: HouseType,
    var goodsType: GoodsType,
    var security: Int,
    var rent : Int,
    var size: Double,
    var maintenanceCost : Int,
    var roomCount: Int,
    var floor: Int,
    var contractPeriod: LocalDateTime,
    var description: String,
    var bathroomCount: Int,
    var isParked: Boolean,
)