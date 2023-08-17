package cinemaMvc.controller;

import cinemaMvc.model.Cinema;
import cinemaMvc.model.Room;
import cinemaMvc.service.Impl.CinemaService;
import cinemaMvc.service.Impl.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/room")
public class RoomController {
    private RoomService roomService;
    private CinemaService cinemaService;


    @Autowired
    public RoomController(RoomService roomService, CinemaService cinemaService) {
        this.roomService = roomService;
        this.cinemaService = cinemaService;
    }

    @ModelAttribute("cinemaList")
    public List<Cinema> cinemaList(){
        return cinemaService.findAll();
    }

    @GetMapping("/save")
    public String save(Model model) {
        List<Cinema> cinemas = cinemaService.findAll();
        model.addAttribute("room", new Room());
        model.addAttribute("cinema1", cinemas);
        return "room/save";
    }


    @PostMapping("/save_room")
    public String saveRoom(@ModelAttribute Room room){
        roomService.save(room);
        return "redirect:/cinema/find_all";
    }

    @GetMapping("/find_all")
    public String findAll(Model model){
        model.addAttribute("room_all",roomService.findAll());
        return "/room/find_all";
    }

    @GetMapping("/findAllId/{id}")
    public String findAllId(@PathVariable("id") int id, Model model){
        model.addAttribute("room_all_id", roomService.findAllId(id));
        return "/room/room_all_id";
    }

    @GetMapping("/find_by_id/{id}")
    public String findById(@PathVariable("id") int id,Model model){
        model.addAttribute("find_id",roomService.findById(id));
        return "room/find_by_id";
    }

    @GetMapping("/update/{id}")
    public String update(Model model,@PathVariable("id") int id){
        Room room = roomService.findById(id);
        model.addAttribute("room",room);
        return "room/update_room";
    }

    @PostMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute Room room,@PathVariable("id") int id){
        roomService.updateById(id,room);
        return "redirect:/room/find_all";
    }

    @GetMapping("delete_by_id{id}")
    public String deleteRoom(@PathVariable("id") int id){
        roomService.deleteById(id);
        return "redirect:room/find_all";
    }


}

