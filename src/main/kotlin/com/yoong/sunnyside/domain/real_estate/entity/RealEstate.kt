package com.yoong.sunnyside.domain.real_estate.entity

import com.yoong.sunnyside.domain.real_estate.dto.CreateRealEstateDto
import com.yoong.sunnyside.domain.real_estate.dto.UpdateRealEstateDto
import com.yoong.sunnyside.domain.real_estate.enum_class.GoodsType
import com.yoong.sunnyside.domain.real_estate.enum_class.HouseType
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.SQLRestriction
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@SQLRestriction("deleted_at=null")
@Table(name = "real_estate")
class RealEstate(

    @Column(name="business_id", nullable = false)
    val businessId: Long?,

    @Column(name="name", nullable = false)
    var name: String,

    @Column(name="address", nullable = false)
    var address: String,

    @Column(name="completion_date", nullable = false)
    var completionDate: LocalDateTime,

    @Enumerated(EnumType.STRING)
    @Column(name="house_type", nullable = false)
    var houseType: HouseType,

    @Enumerated(EnumType.STRING)
    @Column(name = "goods_type", nullable = false)
    var goodsType: GoodsType,

    @Column(name="security", nullable = false)
    var security: Int,

    @Column(name="rent", nullable = false)
    var rent: Int,

    @Column(name="house_size", nullable = false)
    var houseSize: Double,

    @Column(name="maintenance_cost", nullable = false)
    var maintenanceCost: Int,

    @Column(name="room_count", nullable = false)
    var roomCount: Int,

    @Column(name="floor", nullable = false)
    var floor: Int,

    @Column(name="contract_period", nullable = false)
    var contractPeriod: LocalDateTime,

    @Column(name="description", nullable = false)
    var description: String,

    @Column(name="bathroom_count", nullable = false)
    var bathroomCount: Int,

    @Column(name="is_parked", nullable = false)
    var isParked: Boolean,

    @Column(name="rate", nullable = false)
    var rate: Double,

    ){

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name="created_at", nullable = false)
    @CreationTimestamp
    val createdAt: LocalDateTime = LocalDateTime.now()

    @Column(name="updated_at", nullable = false)
    @UpdateTimestamp
    val updatedAt: LocalDateTime = LocalDateTime.now()

    @Column(name="deleted_at", nullable = true)
    var deletedAt: LocalDateTime? = null

    fun update(updateRealEstateDto: UpdateRealEstateDto) {
        this.name = updateRealEstateDto.name
        this.address = updateRealEstateDto.address
        this.completionDate = updateRealEstateDto.completionDate
        this.houseType = updateRealEstateDto.houseType
        this.goodsType = updateRealEstateDto.goodsType
        this.security = updateRealEstateDto.security
        this.rent = updateRealEstateDto.rent
        this.houseSize = updateRealEstateDto.size
        this.maintenanceCost = updateRealEstateDto.maintenanceCost
        this.roomCount = updateRealEstateDto.roomCount
        this.floor = updateRealEstateDto.floor
        this.contractPeriod = updateRealEstateDto.contractPeriod
        this.description = updateRealEstateDto.description
        this.bathroomCount = updateRealEstateDto.bathroomCount
        this.isParked = updateRealEstateDto.isParked
    }

    fun delete() {
        this.deletedAt = LocalDateTime.now()
    }

    constructor(businessId: Long, createRealEstateDto: CreateRealEstateDto) : this(
        businessId = businessId,
        name = createRealEstateDto.name,
        address = createRealEstateDto.address,
        completionDate = createRealEstateDto.completionDate,
        houseType = createRealEstateDto.houseType,
        goodsType = createRealEstateDto.goodsType,
        security = createRealEstateDto.security,
        rent = createRealEstateDto.rent,
        houseSize = createRealEstateDto.size,
        maintenanceCost = createRealEstateDto.maintenanceCost,
        roomCount = createRealEstateDto.roomCount,
        floor = createRealEstateDto.floor,
        contractPeriod = createRealEstateDto.contractPeriod,
        description = createRealEstateDto.description,
        bathroomCount = createRealEstateDto.bathroomCount,
        isParked = createRealEstateDto.isParked,
        rate = 0.0,
    )

}