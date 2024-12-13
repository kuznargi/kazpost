package com.kaz.post.infrastructure.repository



import com.kaz.post.domain.model.RequestParcel
import com.kaz.post.domain.model.ResponseParcel
import org.springframework.http.MediaType
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.BodyInserters

@Repository
class ExternalParcelApiRepository(private val webClient: WebClient) {
    fun sendParcel(requestParcel: RequestParcel): ResponseParcel {
        return webClient.post()
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(
                BodyInserters.fromFormData("parcel_data", requestParcel.parcel_data)
                    .with("data_digest", requestParcel.data_digest)
                    .with("partner_code", requestParcel.partner_code)
            )
            .retrieve()
            .bodyToMono(ResponseParcel::class.java)
            .block()!!
    }
}