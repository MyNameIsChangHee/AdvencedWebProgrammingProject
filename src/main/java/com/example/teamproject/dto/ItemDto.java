package com.example.teamproject.dto;

import com.example.teamproject.entity.Item;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor @ToString @Slf4j
public class ItemDto {
    private String title;
    private int price;
    private String content;
    private String imageName;
    private String category;

    public Item toEntity() {
        return new Item(title,price,content,imageName,category);
    }



}
