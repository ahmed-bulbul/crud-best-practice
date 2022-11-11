package com.bulbul.bestpractice.common.generic.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageData {
    List<?> model;
    int totalPages;
    int currentPage;
    long totalElements;
}
