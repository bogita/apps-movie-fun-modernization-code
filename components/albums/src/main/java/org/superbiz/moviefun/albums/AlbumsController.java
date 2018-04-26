package org.superbiz.moviefun.albums;

import org.apache.tika.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.superbiz.moviefun.blobstore.Blob;
import org.superbiz.moviefun.blobstore.BlobStore;

import java.util.List;
import static org.springframework.http.HttpStatus.OK;

@Controller
@RequestMapping("/albums")
public class AlbumsController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final AlbumsRepository albumsRepository;
    private final BlobStore blobStore;

    public AlbumsController(AlbumsRepository albumsRepository, BlobStore blobStore) {
        this.albumsRepository = albumsRepository;
        this.blobStore = blobStore;
    }


    @GetMapping
    public ResponseEntity<List<Album>> index() {
        return new ResponseEntity<>(albumsRepository.getAlbums(), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> index(@PathVariable Long id) {
        return new ResponseEntity<>(albumsRepository.find(id), OK);
    }


}
