package com.opening.network.model

data class Data(
    val favourite_links: List<Any>,
    val overall_url_chart: Map<String, Int>,
    val recent_links: List<TopLink>,
    val top_links: List<TopLink>
)