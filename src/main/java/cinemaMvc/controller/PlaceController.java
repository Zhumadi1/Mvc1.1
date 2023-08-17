package cinemaMvc.controller;

import cinemaMvc.model.Place;
import cinemaMvc.model.Room;
import cinemaMvc.service.Impl.PlaceService;
import cinemaMvc.service.Impl.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/place")
public class PlaceController {

    private PlaceService service;
    private RoomService roomService;


    @Autowired
    public PlaceController(PlaceService service,RoomService roomService) {
        this.service = service;
        this.roomService = roomService;
    }

    @ModelAttribute("roomList")
    public List<Room> roomList(){
        return roomService.findAll();
    }

    @GetMapping("save")
    public  String save(Model model){
        model.addAttribute("save", new Place());
        return "place/save";
    }

    @PostMapping("save_place")
    public String savePlace(@ModelAttribute Place place){
        service.save(place);
        return "redirect:place/save";
    }

    @GetMapping("/find_all")
    public String findAll(Model model){
        model.addAttribute("place_all",service.findAll());
        return "/place/find_all";
    }

    @GetMapping("find_by_id{id}")
    public String findById(@PathVariable("id") int id, Model model){
        model.addAttribute("find_by_id",service.findById(id));
        return "place/find_by_id";
    }

    @GetMapping("update{id}")
    public String update(Model model,@PathVariable("id") int id){
        model.addAttribute("update",service.findById(id));
        return "place/update";
    }

    @PostMapping("merge_update{id}")
    public String mergeUpdate(@ModelAttribute Place place,@PathVariable("id") int id){
        service.updateById(id,place);
        return "redirect:place/find_all";
    }

    @GetMapping("delete{id}")
    public String delete(@PathVariable("id") int id){
        service.deleteById(id);
        return "redirect:place/find_all";
    }


}

