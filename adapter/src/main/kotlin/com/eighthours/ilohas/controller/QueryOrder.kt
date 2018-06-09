package com.eighthours.ilohas.controller

import org.springframework.data.domain.Sort


data class QueryOrder(
        val direction: QueryOrderDirection,
        val property: String
) {
    fun toOrder() = direction.orderOf(property)
}


enum class QueryOrderDirection(val orderOf: (String) -> Sort.Order) {
    ASC(Sort.Order::asc),
    DESC(Sort.Order::desc);
}


fun List<QueryOrder>.toSort() = Sort.by(map { it.toOrder() })
