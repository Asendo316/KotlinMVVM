package com.example.kotlinappmvvm.models

data class GithubUserListResponse(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)