package ws;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import model.Publicacion;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/publicaciones/imagenes")
public class PublicContentController extends AbstractController {

    @Autowired private PublicacionRepository filesRepo;
    @Autowired private PublicacionStore contentStore;

    @RequestMapping(value="/{fileId}", method = RequestMethod.PUT)
    public ResponseEntity<?> setContent(@PathVariable("fileId") String id, @RequestParam("file") MultipartFile file)
            throws IOException {

        Publicacion f = filesRepo.findById(id);
        if (null != f) {
            f.setMimeType(file.getContentType());

            contentStore.setContent(f, file.getInputStream());

            // save updated content-related info
            filesRepo.save(f);

            return new ResponseEntity<Object>(HttpStatus.OK);
        }
        return null;
    }

    @RequestMapping(value="/{fileId}", method = RequestMethod.GET)
    public ResponseEntity<?> getContent(@PathVariable("fileId") String id) {

        Publicacion f = filesRepo.findById(id);
        if (null != f) {
            InputStreamResource inputStreamResource = new InputStreamResource(contentStore.getContent(f));
            HttpHeaders headers = new HttpHeaders();
            headers.setContentLength(f.getContentLength());
            headers.set("Content-Type", f.getMimeType());
            return new ResponseEntity<Object>(inputStreamResource, headers, HttpStatus.OK);
        }
        return null;
    }

}