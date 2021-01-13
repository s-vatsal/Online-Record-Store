package edu.trs.service;

import edu.trs.model.Album;

import java.util.List;

public interface AlbumService {
    List<Album> getAlbums();

    List<Album> searchAlbums(String term);

    void addAlbum(String albumName, String artistName, String dateReleased,
                  String genreName, int numTracks, String albumPrice);

    void deleteAlbum(Long albumId);

    Album getAlbumById(Long albumId);

    void editAlbum(String albumName, String artistName, String dateReleased, String genreName, int numTracks, String albumPrice, Long albumId);

    void discount();
}
