package com.yoong.sunnyside.domain.real_estate.repository

import com.querydsl.core.QueryFactory
import com.querydsl.jpa.impl.JPAQueryFactory
import com.yoong.sunnyside.domain.real_estate.entity.QRealEstate
import com.yoong.sunnyside.domain.real_estate.entity.RealEstate
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class RealEstateRepositoryImpl(
    private val realEstateJpaRepository: RealEstateJpaRepository,
    @PersistenceContext
    private val em: EntityManager
): RealEstateRepository {

    private val queryFactory = JPAQueryFactory(em)
    private val realEstate = QRealEstate.realEstate

    override fun existsByAddress(address: String): Boolean {
        return realEstateJpaRepository.existsByAddress(address)
    }

    override fun save(realEstate: RealEstate): RealEstate {
        return realEstateJpaRepository.save(realEstate)
    }

    override fun findAll(pageable: Pageable): Page<RealEstate> {

        val query = queryFactory
            .selectFrom(realEstate)
            .where(realEstate.deletedAt.isNull)
            .fetch()

        val count = query.count()

        return PageImpl(query, pageable, count.toLong())
    }

    override fun findByIdOrNull(realEstateId: Long): RealEstate? {
        return realEstateJpaRepository.findByIdOrNull(realEstateId)
    }

    override fun saveAndFlush(realEstate: RealEstate): RealEstate {
        return realEstateJpaRepository.saveAndFlush(realEstate)
    }

    override fun existsById(realEstateId: Long): Boolean {
        return realEstateJpaRepository.existsById(realEstateId)
    }
}