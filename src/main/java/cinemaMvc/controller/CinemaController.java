package cinemaMvc.controller;

import cinemaMvc.model.Cinema;
import cinemaMvc.service.Impl.CinemaService;
import cinemaMvc.service.Impl.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cinema")
public class CinemaController {
    private final CinemaService cinemaService;
    private final RoomService roomService;


    @Autowired
    public CinemaController(CinemaService cinemaService, RoomService roomService) {
        this.cinemaService = cinemaService;
        this.roomService = roomService;
    }

    @GetMapping("/save")
    public String save(Model model) {
        Cinema cinema = new Cinema();
        model.addAttribute("cinema", cinema);
        return "cinema/cinema";
    }

    @PostMapping("/save_cinema")
    public String saveCinema(@ModelAttribute Cinema cinema) {
        cinemaService.save(cinema);
        return "redirect:/cinema/find_all";
    }

    @GetMapping("find_all")
    public String findAll(Model model) {
        model.addAttribute("cinema_all", cinemaService.findAll());
        return "cinema/main";
    }


    @GetMapping("find_by_id{id}")
    public String findById(@PathVariable("id") int id, Model model){
        model.addAttribute("find_id",cinemaService.findById(id));
        return "cinema/find_by_id";
    }

    @GetMapping("/update/{id}")
    public String update(Model model,@PathVariable("id") int id){
        Cinema cinema = cinemaService.findById(id);
        model.addAttribute("cinema",cinema);
        return "cinema/update_cinema";
    }

    @PostMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute Cinema cinema,@PathVariable("id") int id){
        cinemaService.updateById(id,cinema);
        return "redirect:/cinema/find_all";
    }

    @GetMapping("/delete/{id}")
    public String deleteCinema(@PathVariable("id") int id){
        cinemaService.deleteById(id);
        return "redirect:/cinema/find_all";
    }

}