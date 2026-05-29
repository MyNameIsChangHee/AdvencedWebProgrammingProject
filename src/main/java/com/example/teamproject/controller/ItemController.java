package com.example.teamproject.controller;


import com.example.teamproject.Repository.ItemRepository;
import com.example.teamproject.dto.ItemDto;
import com.example.teamproject.dto.ItemUpdateDto;
import com.example.teamproject.entity.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;


@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
    private final ItemRepository itemRepository;

    @ModelAttribute
    public void addLoginUser(HttpSession session, Model model) {
        String logInUser = (String) session.getAttribute("loginUser");
        if (logInUser != null) {
            model.addAttribute("logInUser", logInUser);
        }
    }

    @GetMapping("")
    public String item(Model model, HttpSession session) {
        List<Item> itemList = itemRepository.findAll();
        model.addAttribute("itemList",itemList);

        return "index";
    }
    @GetMapping("/new")
    public String newItem() {
        return "new";
    }

    @PostMapping("/create")
    public String createItem(ItemDto itemDto,
                             @RequestParam(value = "file") MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path path = Paths.get("C:/SpringBoot/images/" + filename);
            Files.write(path, file.getBytes());
            itemDto.setImageName(filename);
        }
        itemRepository.save(itemDto.toEntity());
        return "redirect:/items";
    }

    @GetMapping("/{category}")
    public String cartegoryItems(@PathVariable String category, Model model) {
        List<Item> itemList = itemRepository.findByCategory(category);
        model.addAttribute("itemList",itemList);
        return "index";
    }
    @GetMapping("/{id}/info")
    public String itemsInfo(@PathVariable Long id, Model model) {
        Item item = itemRepository.findById(id).orElse(null);
        model.addAttribute("item",item);
        return "info";
    }

    @GetMapping("/{id}/buy")
    public String buyItem(@PathVariable Long id, RedirectAttributes rttr){
        Item target = itemRepository.findById(id).orElse(null);
        if(target!=null){
            rttr.addFlashAttribute("msg", "'"+target.getTitle()+"'" + " 구매가 완료되었습니다!");
            itemRepository.deleteById(id);
        }
        return "redirect:/items";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model  model){
        Item item = itemRepository.findById(id).orElse(null);
        model.addAttribute("item", item);
        return "edit";
    }
    @PostMapping("/{id}/update")
    public String updateItem(@PathVariable Long id, ItemUpdateDto itemUpdate){
        Item target = itemRepository.findById(id).orElse(null);
        if(target!= null){
            target.patch(itemUpdate);
            itemRepository.save(target);
        }
        return "redirect:/items/"+target.getId()+"/info";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        Item target = itemRepository.findById(id).orElse(null);
        if (target != null) {
            itemRepository.deleteById(id);
            rttr.addFlashAttribute("msg", "삭제되었습니다.");
        }
        return "redirect:/items";
    }

}

