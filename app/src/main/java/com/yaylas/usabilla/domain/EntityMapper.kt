package com.yaylas.usabilla.domain

import com.yaylas.usabilla.data.local.models.FeedbackRoomEntity
import com.yaylas.usabilla.data.network.models.FeedbackDTO

interface EntityMapper <Entity, DomainModel>{

    fun mapFromEntity(entity: Entity): DomainModel

    fun mapFromEntityList(entities: List<Entity>): List<DomainModel>
}