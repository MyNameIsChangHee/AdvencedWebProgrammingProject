package com.example.teamproject.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
@ToString
@Slf4j
public class ItemUpdateDto {
    private Long id;
    private String title;
    private int price;
    private String content;
}
