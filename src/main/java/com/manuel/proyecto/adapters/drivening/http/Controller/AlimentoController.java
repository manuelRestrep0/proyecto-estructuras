package com.manuel.proyecto.adapters.drivening.http.Controller;

import com.manuel.proyecto.adapters.drivening.http.Handlers.IAlimentoHandler;
import com.manuel.proyecto.domain.model.Alimento;
import com.manuel.proyecto.domain.model.DiasRecomendados;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alimento")
@RequiredArgsConstructor
public class AlimentoController {

    private final IAlimentoHandler alimentoHandler;

    @GetMapping
    public List<DiasRecomendados> obtenerInformacionAlimento(@RequestParam("nombres") List<String> nombres, @RequestParam("idUsuario") int idUsuario,
                                                             @RequestParam("mes") int mes, @RequestParam("dia") int dia){
        return alimentoHandler.obtenerAlimento(nombres,idUsuario,mes,dia);
    }
}
