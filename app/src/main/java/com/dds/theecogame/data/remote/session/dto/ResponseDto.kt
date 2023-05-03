package com.dds.theecogame.data.remote.session.dto

import com.dds.theecogame.domain.model.Response

data class ResponseDto(
    val message: String
)

fun ResponseDto.toResponse(): Response {
    return Response(
        message = message
    )
}