    package br.com.comidascesar.controller;

    import java.util.List;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.DeleteMapping;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.comidascesar.dto.ComidasDTO;
import br.com.comidascesar.dto.ComidasResponseBody;
import br.com.comidascesar.service.Comidaservice;
import jakarta.validation.Valid;

    @org.springframework.web.bind.annotation.RestController
    public class RestController {

        @Autowired
        private final Comidaservice Comidaservice;

        public RestController(Comidaservice service) {
            this.Comidaservice = service;
        }

        @GetMapping("/api/Comidas")
        public ResponseEntity<ComidasResponseBody> home() {
            List<ComidasDTO> allComidas = Comidaservice.findAll();
            ComidasResponseBody ComidasResponseBody = new ComidasResponseBody(allComidas);
            return ResponseEntity.ok(ComidasResponseBody);
        }

        @PostMapping("/api/Comidas")
        public ResponseEntity<ComidasDTO> createComidas(@RequestBody @Valid ComidasDTO Comidas) {
            this.Comidaservice.save(Comidas);
            return ResponseEntity.ok().build();
        }

        @DeleteMapping("/api/Comidas/{id}")
        public ResponseEntity<ComidasDTO> deleteComidas(@PathVariable String id) {
            this.Comidaservice.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        @PutMapping("/api/Comidas/{id}")
        public ResponseEntity<ComidasDTO> updateComidas(@PathVariable String id, @RequestBody @Valid ComidasDTO ComidasDTO) {
            this.Comidaservice.update(id, ComidasDTO);
            return ResponseEntity.ok(ComidasDTO);
        }
    }