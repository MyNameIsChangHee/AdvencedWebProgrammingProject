package com.example.teamproject.entity;

import com.example.teamproject.dto.ItemUpdateDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor @Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String title;
    private int price;
    private String content;
    private String imageName;
    private String category;

    public Item(String title, int price, String content, String imageName, String category) {
        this.title = title;
        this.price = price;
        this.content = content;
        this.imageName = imageName;
        this.category = category;
    }

    public void patch(ItemUpdateDto dto){
        if(dto.getTitle()!= null)
            this.title = dto.getTitle();
        if(dto.getContent() != null)
            this.content = dto.getContent();
        if (dto.getPrice() != 0)
            this.price = dto.getPrice();
    }
}
