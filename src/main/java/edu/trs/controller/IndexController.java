package edu.trs.controller;

import edu.trs.model.Album;
import edu.trs.service.AlbumService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    private final AlbumService albumService;

    public IndexController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/")
    public String home(Model model){
        List<Album> albumList = albumService.getAlbums();
        model.addAttribute("albumList", albumList);
        return "home";
    }

    @PostMapping("/edit-album")
    public String editAlbumForm(@RequestParam String albumName, @RequestParam String artistName,
                                @RequestParam String dateReleased, @RequestParam String genreName,
                                @RequestParam int numTracks, @RequestParam String albumPrice,
                                @RequestParam(required = false) Long albumId, Model model){
            try{
                albumService.editAlbum(albumName, artistName, dateReleased, genreName, numTracks, albumPrice, albumId);
                model.addAttribute("successMessage", "Changes Saved");
                model.addAttribute("albumList", albumService.getAlbums());
                return "home";
            }catch (Exception e){
                model.addAttribute("errorMessage", e.getMessage());
                model.addAttribute("album", albumService.getAlbumById(albumId));
            }
            return "edit";
    }

    @PostMapping("/")
    public String addAlbumForm(@RequestParam String albumName, @RequestParam String artistName,
                               @RequestParam String dateReleased, @RequestParam String genreName,
                               @RequestParam int numTracks, @RequestParam String albumPrice, Model model){
        List<Album> albumList = new ArrayList<>();
        try {
            albumService.addAlbum(albumName, artistName, dateReleased, genreName, numTracks, albumPrice);
            albumList = albumService.getAlbums();
            model.addAttribute("successMessage", "Album Has Been Saved");
        }catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
        }
        model.addAttribute("albumList", albumList);
        return "home";
    }

    @GetMapping("/search")
    public String search(@RequestParam String term, Model model){
        List<Album> albumList = new ArrayList<>();
        try {
            albumList = albumService.searchAlbums(term);
        }catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
        }
        model.addAttribute("albumList", albumList);
        return "home";
    }

    @GetMapping("/admin/albums/edit/{albumId}")
    public String editPage(@PathVariable long albumId, Model model){
        Album album = albumService.getAlbumById(albumId);
        model.addAttribute("album", album);
        return "edit";
    }

    @GetMapping("/admin/albums/delete/{albumId}")
    public String deletePage(@PathVariable long albumId, Model model){
        List<Album> albumList = new ArrayList<>();
        try {
            albumService.deleteAlbum(albumId);
            albumList = albumService.getAlbums();
            model.addAttribute("successMessage", "Album Has Been Deleted");
        }catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
        }
        model.addAttribute("albumList", albumList);
        return "home";
    }

    @PostMapping("/deals")
    public String discountHalf(Model model){
        List<Album> albumList = new ArrayList<>();
        List<Album> albumList2 = albumService.getAlbums();
        try {
            albumService.discount();
            albumList = albumService.getAlbums();
            model.addAttribute("successMessage", "50% Discount Applied");
            model.addAttribute("albumList", albumList);

        }catch (Exception e){
            model.addAttribute("errorMessage", "50% Discount Has Already Been Applied");
            model.addAttribute("albumList", albumList2);
        }
        return "home";
    }



//    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
//        model.addAttribute("name", name);
//        return "home";
//    }

}