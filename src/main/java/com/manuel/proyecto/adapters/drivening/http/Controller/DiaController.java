package com.manuel.proyecto.adapters.drivening.http.Controller;

import com.manuel.proyecto.adapters.drivening.http.Dto.DiaDto;
import com.manuel.proyecto.adapters.drivening.http.Dto.UsuarioDto;
import com.manuel.proyecto.adapters.drivening.http.Handlers.IDiaHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/dia")
@RequiredArgsConstructor
public class DiaController {

    private final IDiaHandler diaHandler;

    @PostMapping("/generar-recomendados")
    public ResponseEntity<Map<String,String>> generarRecomendados(@RequestBody UsuarioDto usuarioDto){
        diaHandler.generarDiasRecomendados(usuarioDto.getIdUsuario(), usuarioDto.getPeso());
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Collections.singletonMap("Mensaje: ","Dias recomendados generados")
        );
    }
}
