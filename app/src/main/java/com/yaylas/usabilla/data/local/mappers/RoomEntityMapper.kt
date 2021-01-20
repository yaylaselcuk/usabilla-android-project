package com.yaylas.usabilla.data.local.mappers

import com.yaylas.usabilla.data.local.models.FeedbackRoomEntity
import com.yaylas.usabilla.domain.EntityMapper
import com.yaylas.usabilla.domain.model.*
import com.yaylas.usabilla.util.Converters
import com.yaylas.usabilla.util.toPrintableString
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class RoomEntityMapper @Inject
constructor() : EntityMapper<FeedbackRoomEntity, Feedback> {
    override fun mapFromEntity(entity: FeedbackRoomEntity): Feedback {
        val userInfo = UserInfo(
            email = entity.email,
            ipAddress = entity.ipAddress,
            country = entity.country,
            city = entity.city,
            latitude = entity.latitude,
            longitude = entity.longitude
        )
        val feedbackInfo = FeedbackInfo(
            feedbackType = Converters.stringToFeedbackType(entity.type),
            rating = entity.rating,
            performance = convertPerformanceToString(entity.performance),
            comment = entity.comment,
            labels = entity.labels.toPrintableString()
        )

        val browserInfo = BrowserInfo(
            name = entity.browser,
            version = entity.browserVersion,
            platform = entity.platform
        )
        val imagesInfo = ImagesInfo(
            screenshot = entity.screenshotImage,
            fullImage = entity.fullImage,
            cropped = entity.croppedImage,
            noContext = entity.noContextImage,
            thumbnail = entity.thumbnailImage,
            grid = entity.gridImage,
            list = entity.listImage,
            detail = entity.detailImage
        )

        return Feedback(
            id = entity.id,
            user = userInfo,
            status = Converters.stringToFeedbackStatus(entity.status),
            info = feedbackInfo,
            browser = browserInfo,
            images = imagesInfo,
            htmlSnippet = entity.htmlSnippet,
            creationDate = convertDateToFormattedString(entity.createdAt),
            starred = entity.starred
        )
    }

    private fun convertDateToFormattedString(timeInSeconds: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeInSeconds * 1000
        return SimpleDateFormat("dd MMM yyyy - HH:mm", Locale.ENGLISH).format(calendar.time)
    }

    private fun convertPerformanceToString(performance: Int): String {
        return if (performance in 1..5) {
            "$performance"
        } else {
            "-"
        }
    }


    override fun mapFromEntityList(entities: List<FeedbackRoomEntity>): List<Feedback> {
        return entities.map { mapFromEntity(it) }
    }

}