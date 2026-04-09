package br.com.comidascesar.service;

import br.com.comidascesar.dto.ComidasDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class comidasServiceImpl implements ComidasService{

    private List<ComidasDTO> Comidas;

    public ComidasServiceImpl(){
       Comidas = new ArrayList<ComidasDTO>();
    }

    @Override
    public List<ComidasDTO> findAll() {
        return Comidas;
    }

    @Override
    public void save(ComidasDTO ComidasDTO) {
        // Valida null e vazio, pois coloquei um input hidden no index.html em form.
        if (ComidasDTO.getId() == null || ComidasDTO.getId().isEmpty()) {

            ComidasDTO.setId(UUID.randomUUID().toString());
        }
        this.Comidas.add(ComidasDTO);
    }

    @Override
    public void deleteById(String id) {

        this.Comidas.removeIf(Comidas -> Comidas.getId().equals(id));
    }

    @Override
    public void update(String id, ComidasDTO ComidasDTO) {

        this.Comidas.replaceAll(Comidas -> Comidas.getId().equals(id) ? ComidasDTO : Comidas);

    }

    
    @Override
    public ComidasDTO findById(String id) {
        return this.Comidas.stream()
                .filter(Comidas -> Comidas.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

}
