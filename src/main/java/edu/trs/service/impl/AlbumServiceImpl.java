package edu.trs.service.impl;

import edu.trs.model.Album;
import edu.trs.repository.AlbumRepository;
import edu.trs.service.AlbumService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Album> getAlbums() {
        return albumRepository.getAlbums();
    }

    @Override
    public List<Album> searchAlbums(String term) {
        if(!StringUtils.hasText(term)){
            throw new IllegalArgumentException("Search Term Cannot Be Empty");
        }
        return albumRepository.searchAlbums(term);
    }

    @Override
    public void addAlbum(String albumName, String artistName, String dateReleased,
                         String genreName, int numTracks, String albumPrice) {
        if(!StringUtils.hasText(albumName)){
            throw new IllegalArgumentException("Album Name is Required");
        }
        if(!StringUtils.hasText(artistName)){
            throw new IllegalArgumentException("Artist Name is Required");
        }

        Date date;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if(!StringUtils.hasText(dateReleased)){
            throw new IllegalArgumentException("Date is Required");
        }else{
            try{
                date = format.parse(dateReleased);
            }catch (Exception e){
                throw new IllegalArgumentException("Invalid Date");
            }
        }
        if(!StringUtils.hasText(genreName)){
            throw new IllegalArgumentException("Genre is Required");
        }
        if(!StringUtils.hasText(String.valueOf(numTracks))){
            throw new IllegalArgumentException("Number of Tracks is Required");
        }
        double price;
        if(!StringUtils.hasText(albumPrice)){
            throw new IllegalArgumentException("Album Price is Required");
        }else{
            try{
                price = Double.parseDouble(albumPrice);
            }catch (Exception e){
                throw new IllegalArgumentException("Invalid Album Price");
            }
        }
//        Album album = new Album(0, albumName, artistName, date, genreName, numTracks, price);
        Album album = new Album(albumName, artistName, date, genreName, numTracks, price);

        albumRepository.addAlbum(album);
    }

    @Override
    public void deleteAlbum(Long albumId) {
        if(albumId == null || albumId < 0){
            throw new IllegalArgumentException("Album ID Required");
        }
        Album album = albumRepository.getAlbumById(albumId);
        if(album == null || album.getAlbumId() < 0){
            throw new IllegalArgumentException("Album ID-" + albumId + " Not Found");
        }
        albumRepository.deleteAlbum(albumId);
    }

    @Override
    public Album getAlbumById(Long albumId) {
        if(albumId == null || albumId <= 0){
            throw new IllegalArgumentException("Album ID Required");
        }
        Album album = albumRepository.getAlbumById(albumId);
        if(album == null){
            throw new IllegalStateException("Album ID-" + albumId + " Not Found");
        }
        return album;
    }

    @Override
    public void editAlbum(String albumName, String artistName, String dateReleased, String genreName,
                          int numTracks, String albumPrice, Long albumId) {
        if(albumId == null || albumId <= 0){
            throw new IllegalArgumentException("Album ID Required");
        }

        if(!StringUtils.hasText(albumName)){
            throw new IllegalArgumentException("Album Name is Required");
        }
        if(!StringUtils.hasText(artistName)){
            throw new IllegalArgumentException("Artist Name is Required");
        }

        Date date;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if(!StringUtils.hasText(dateReleased)){
            throw new IllegalArgumentException("Date is Required");
        }else{
            try{
                date = format.parse(dateReleased);
            }catch (Exception e){
                throw new IllegalArgumentException("Invalid Date");
            }
        }
        if(!StringUtils.hasText(genreName)){
            throw new IllegalArgumentException("Genre is Required");
        }
        if(!StringUtils.hasText(String.valueOf(numTracks))){
            throw new IllegalArgumentException("Number of Tracks is Required");
        }
        double price;
        if(!StringUtils.hasText(albumPrice)){
            throw new IllegalArgumentException("Album Price is Required");
        }else{
            try{
                price = Double.parseDouble(albumPrice);
            }catch (Exception e){
                throw new IllegalArgumentException("Invalid Album Price");
            }
        }

        albumRepository.editAlbum(albumName, artistName, dateReleased, genreName, numTracks, price, albumId);
    }

    @Override
    public void discount() {
        albumRepository.discount();
    }
}
