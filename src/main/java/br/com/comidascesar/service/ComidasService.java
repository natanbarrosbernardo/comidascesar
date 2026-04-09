package br.com.comidascesar.service;

import java.util.List;

import br.com.comidascesar.ComidasDTO;

public interface ComidasService {

    List<ComidasDTO> findAll();

    void save(ComidasDTO comidasDTO);

    void deleteById(String id);

    void update(String id, ComidasDTO comidasDTO);

    //Busca o comidasro pelo Id.
    ComidasDTO findById(String id);

}
