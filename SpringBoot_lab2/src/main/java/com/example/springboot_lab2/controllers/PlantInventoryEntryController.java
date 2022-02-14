package com.example.springboot_lab2.controllers;

import com.example.springboot_lab2.models.PlantInventoryEntry;
import com.example.springboot_lab2.models.PlantInventoryEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;

@Controller
public class PlantInventoryEntryController {
    @Autowired
    PlantInventoryEntryRepository repo;

    @GetMapping("/plants")
    public String list(Model model){
        model.addAttribute("plants", repo.findAll());
        return "plants/list";
    }

    @GetMapping(value="/plants/form")
    public String form(Model model){
        model.addAttribute("plant", new PlantInventoryEntry());
        return "plants/create";
    }

    @PostMapping(value="/plants/form")
    public String create(PlantInventoryEntry plant){
        repo.save(plant);
        return "redirect:/plants";
    }

    @GetMapping(value="/plants/remove")
    public String remove(Model model){
        model.addAttribute("plant", new PlantInventoryEntry());
        return "plants/delete";
    }
    @PostMapping(value="/plants/remove")
    public String delete(long id){
        PlantInventoryEntry plant = repo.findById(id).orElseThrow();
        repo.delete(plant);
        return "redirect:/plants";
    }

    @GetMapping(value="/plants/search")
    public String search(Model model){
        model.addAttribute("plant", new PlantInventoryEntry());
        return "plants/find";
    }
    @PostMapping(value="/plants/search")
    public String findingres(long id,Model model){
        PlantInventoryEntry plant = repo.findById(id).orElseThrow();
        model.addAttribute("plants", plant);
        return "plants/resfind";
    }
    @GetMapping(value="/plants/search/res")
    public String searching(Model model)
    {
        model.addAttribute("plant", new PlantInventoryEntry());
        return "plants/editor";
    }
    @PostMapping(value="/plants/search/res")
    public String saving(long id, String name, String description, BigDecimal price){
        PlantInventoryEntry plant = repo.findById(id).orElseThrow();
        plant.setName(name);
        plant.setDescription(description);
        plant.setPrice(price);
        repo.save(plant);
        return "redirect:/plants";
    }


}
