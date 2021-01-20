package com.yaylas.usabilla.data.local.mappers

import com.yaylas.usabilla.data.local.models.BrowserRoomEntity
import com.yaylas.usabilla.data.local.models.FeedbackRoomEntity
import com.yaylas.usabilla.data.network.models.FeedbackDTO
import com.yaylas.usabilla.domain.EntityMapper
import javax.inject.Inject


class BrowserEntityMapper @Inject constructor() : EntityMapper<FeedbackRoomEntity, BrowserRoomEntity> {

    override fun mapFromEntity(entity: FeedbackRoomEntity): BrowserRoomEntity {
        return BrowserRoomEntity(
            browser = entity.browser,
            version = entity.browserVersion,
            platform = entity.platform
        )
    }


    override fun mapFromEntityList(entities: List<FeedbackRoomEntity>): List<BrowserRoomEntity> {
        return entities.map { mapFromEntity(it) }
    }
}

