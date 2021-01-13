package edu.trs.repository.impl;

import edu.trs.jpa.JpaAlbumRepository;
import edu.trs.model.Album;
import edu.trs.repository.AlbumRepository;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
//import java.util.stream.Collectors;


@Repository
public class AlbumRepositoryImpl implements AlbumRepository {

    //PS5
//    private final List<Album> albumList;
//
//    public AlbumRepositoryImpl() {
//        this.albumList = new ArrayList<>();
//    }

    //PS6
    private final JpaAlbumRepository jpaAlbumRepository;

    public AlbumRepositoryImpl(JpaAlbumRepository jpaAlbumRepository) {
        this.jpaAlbumRepository = jpaAlbumRepository;
    }

    @Override
    public List<Album> getAlbums() {
//        return albumList;
        return (List<Album>) jpaAlbumRepository.findAll();
    }

    @Override
    public void addAlbum(Album album) {
//        if (album.getAlbumId() <= 0) {
//            if (albumList.isEmpty()) {
//                album.setAlbumId(1);
//            } else {
//                Long nextAlbumId = albumList
//                        .stream()
//                        .map(Album::getAlbumId)
//                        .max(Long::compare)
//                        .get();
//                album.setAlbumId(nextAlbumId + 1);
//            }
//        }
//        albumList.add(album);
        jpaAlbumRepository.save(album);
    }

    @Override
    public List<Album> searchAlbums(String term) {
//        return albumList
//                .stream()
//                .filter(a -> a.getAlbumTitle().toLowerCase().contains(term.toLowerCase()))
//                .collect(Collectors.toList());
        return jpaAlbumRepository.findByAlbumTitleIgnoreCaseContaining(term);
    }

    @Override
    public void deleteAlbum(Long albumId) {
//        albumList.remove(getAlbumById(albumId));
        jpaAlbumRepository.deleteById(albumId);
    }

    @Override
    public Album getAlbumById(Long albumId) {
//        for(Album album : albumList){
//            if(album.getAlbumId() == albumId){
//                return album;
//            }
//        }
//        return null;
        Optional<Album> found = jpaAlbumRepository.findById(albumId);
        if(found.isPresent()){
            return found.get();
        }else{
            throw new IllegalStateException("Album with ID " + albumId + " not found");
        }
    }

    @Override
    public void editAlbum(String albumName, String artistName, String dateReleased,
                          String genreName, int numTracks, double price, Long albumId) {
        Album album = getAlbumById(albumId);
        if(album == null){
            throw new IllegalArgumentException("Album ID-" + albumId + " Not Found");
        }
        Date date;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = format.parse(dateReleased);
        }catch (Exception e){
            throw new IllegalArgumentException("Invalid Date");
        }
        album.setAlbumTitle(albumName);
        album.setArtist(artistName);
        album.setRelDate(date);
        album.setAlbumGenre(genreName);
        album.setNumTracks(numTracks);
        album.setAlbumPrice(price);
        jpaAlbumRepository.save(album);
    }

    @Override
    public void discount() {

        List<Album> temp = (List<Album>) jpaAlbumRepository.findAll();
        for (Album album : temp) {
            if(album.getAlbumPrice() == 4.99){
                throw new IllegalStateException();
            }else {
                album.setAlbumPrice(4.99);
            }
        }
        //jpaAlbumRepository.deleteAll();
        for(Album album : temp){
            jpaAlbumRepository.save(album);
        }

    }

}

