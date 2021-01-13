package edu.trs.repository;

import edu.trs.model.Album;

import java.util.List;

public interface AlbumRepository {
    List<Album> getAlbums();

    void addAlbum(Album album);

    List<Album> searchAlbums(String term);

    void deleteAlbum(Long albumId);

    Album getAlbumById(Long albumId);

    void editAlbum(String albumName, String artistName, String dateReleased, String genreName,
                   int numTracks, double price, Long albumId);

    void discount();
}
