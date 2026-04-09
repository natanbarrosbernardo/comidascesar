package br.com.comidascesar.dto;

import java.util.List;

public class ComidasResponseBody {

    private  List<ComidasDTO> comidass;

    public ComidasResponseBody( List<ComidasDTO> allcomidas){
        this.comidass = allcomidas;

    }
    private ComidasDTO comidas;

    public ComidasDTO getcomidas() {
        return comidas;
    }

    public void setcomidas(ComidasDTO comidas) {
        this.comidas = comidas;
    }
}
