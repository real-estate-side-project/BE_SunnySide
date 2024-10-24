package com.yoong.sunnyside.domain.real_estate_option.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import com.yoong.sunnyside.domain.real_estate_option.entity.QRealEstateOption
import com.yoong.sunnyside.domain.real_estate_option.entity.RealEstateOption
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.stereotype.Repository

@Repository
class RealEstateOptionRepositoryImpl(
    private val realEstateOptionJpaRepository: RealEstateOptionJpaRepository,
    @PersistenceContext
    private val em: EntityManager
): RealEstateOptionRepository {

    val queryFactory = JPAQueryFactory(em)
    val realEstateOption = QRealEstateOption.realEstateOption

    override fun saveAll(options: List<RealEstateOption>) {
        realEstateOptionJpaRepository.saveAll(options)
    }

    override fun findAllByRealEstateId(realEstateId: Long): List<RealEstateOption> {
        return realEstateOptionJpaRepository.findAllByRealEstateId(realEstateId)
    }

    override fun findAllByIdAndRealEstateId(realEstateId: Long, ids: List<Long>): List<RealEstateOption> {

        val query = queryFactory.selectFrom(realEstateOption)
            .where(realEstateOption.id.`in`(ids)
                .and(realEstateOption.realEstate.id.eq(realEstateId)))
            .fetch()

        return query
    }
}