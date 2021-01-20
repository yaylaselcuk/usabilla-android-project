package com.yaylas.usabilla.data.network.mappers

import com.yaylas.usabilla.data.local.models.FeedbackRoomEntity
import com.yaylas.usabilla.data.network.models.FeedbackDTO
import com.yaylas.usabilla.domain.EntityMapper
import javax.inject.Inject

class NetworkMapper @Inject constructor() : EntityMapper<FeedbackDTO, FeedbackRoomEntity> {

    override fun mapFromEntity(entity: FeedbackDTO): FeedbackRoomEntity {
        return FeedbackRoomEntity(
            id = entity.id,
            createdAt = entity.createdAt,
            latitude = entity.geo?.lat ?: 0.0,
            longitude = entity.geo?.lon ?: 0.0,
            city = entity.geo?.city ?: "",
            country = entity.country ?: "",
            browser = entity.browser?.browser ?: "",
            browserVersion = entity.browser?.version ?: "",
            platform = entity.browser?.platform ?: "",
            email = entity.email ?: "xxxxxx@yyyyy.zzz",
            status = entity.status ?: "",
            type = entity.custom?.subject ?: "",
            starred = entity.starred ?: false,
            rating = entity.rating ?: 0,
            performance = entity.performance ?: 0,
            htmlSnippet = entity.htmlSnippet ?: "",
            labels = entity.labels ?: ArrayList(),
            ipAddress = entity.ip ?: "xxx.xxx.xx.xx",
            comment = entity.comment ?: "",
            screenshotImage = entity.images?.screenshot?.url ?: "",
            fullImage = entity.images?.fullImage?.url ?: "",
            noContextImage = entity.images?.noContext?.url ?: "",
            gridImage = entity.images?.grid?.url ?: "",
            listImage = entity.images?.list?.url ?: "",
            thumbnailImage = entity.images?.thumbnail?.url ?: "",
            croppedImage = entity.images?.cropped?.url ?: "",
            detailImage = entity.images?.detail?.url ?: ""
        )
    }


    override fun mapFromEntityList(entities: List<FeedbackDTO>): List<FeedbackRoomEntity> {
        return entities.map { mapFromEntity(it) }
    }
}