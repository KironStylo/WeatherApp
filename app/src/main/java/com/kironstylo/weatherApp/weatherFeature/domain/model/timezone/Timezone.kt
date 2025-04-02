package com.kironstylo.weatherApp.weatherFeature.domain.model.timezone

import java.time.LocalDateTime

data class Timezone (
    val localTime: LocalDateTime = LocalDateTime.now()
)