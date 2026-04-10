package br.com.comidascesar.service;

import br.com.comidascesar.dto.ComidasDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class comidasServiceImpl implements ComidasService{

    private List<ComidasDTO> repositorioEmMemoria;

    public ComidasServiceImpl() {
        this.repositorioEmMemoria = new ArrayList<>();
    }

    @Override
    public List<ComidasDTO> findAll() {
        return this.repositorioEmMemoria;
    }

    @Override
    public void save(ComidasDTO novaComida) {
        boolean isIdAusente = novaComida.getId() == null || novaComida.getId().trim().isEmpty();
        if (isIdAusente) {String idGerado = UUID.randomUUID().toString();
            novaComida.setId(idGerado);
        }
        this.repositorioEmMemoria.add(novaComida);
    }

    @Override
    public void deleteById(String codigo) {
        this.repositorioEmMemoria.removeIf(c -> c.getId().equals(codigo));
    }

    @Override
    public void update(String codigo, ComidasDTO comidaAtualizada) {
        this.repositorioEmMemoria.replaceAll(itemAtual -> 
            itemAtual.getId().equals(codigo) ? comidaAtualizada : itemAtual
        );
    }

    @Override
    public ComidasDTO findById(String codigo) {
        return this.repositorioEmMemoria.stream()
                .filter(item -> item.getId().equals(codigo))
                .findFirst()
                .orElse(null);
    }
}
