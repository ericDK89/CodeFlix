package com.admin.catalog.domain.category;

public record CategorySearchQuery(int page, int perPage, String terms, String sorts, String direction) {
}
