package org.superbiz.moviefun.moviesapi.albumsapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestOperations;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

public class AlbumsClient {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static ParameterizedTypeReference<List<AlbumInfo>> albumListType = new ParameterizedTypeReference<List<AlbumInfo>>() {
    };

    private String albumsUrl;
    private RestOperations restOperations;

    public AlbumsClient(String albumsUrl, RestOperations restOperations) {
        this.albumsUrl=albumsUrl;
        this.restOperations=restOperations;
    }

    public void addAlbum(AlbumInfo albumInfo) {
        restOperations.postForEntity(albumsUrl, albumInfo, AlbumInfo.class);
    }

    public AlbumInfo find(long id) {
        return restOperations.getForObject(albumsUrl+"/"+id, AlbumInfo.class);
    }

    public List<AlbumInfo> getAlbums() {
        logger.info("Calling! "+albumsUrl);
        return restOperations.exchange(albumsUrl, GET, null, albumListType).getBody();
    }

    public void deleteAlbum(AlbumInfo albumInfo) {
        restOperations.delete(albumsUrl, albumInfo, AlbumInfo.class);
    }

    public void updateAlbum(AlbumInfo albumInfo) {
        restOperations.put(albumsUrl, albumInfo, AlbumInfo.class);
    }


}
