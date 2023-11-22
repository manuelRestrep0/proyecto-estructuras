package com.manuel.proyecto.adapters.drivening.http.Controller;

import com.manuel.proyecto.adapters.drivening.http.Dto.DiaDto;
import com.manuel.proyecto.adapters.drivening.http.Dto.UsuarioDto;
import com.manuel.proyecto.adapters.drivening.http.Handlers.IDiaHandler;
import com.manuel.proyecto.domain.model.DiasRecomendados;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dia")
@RequiredArgsConstructor
public class DiaController {
    private final IDiaHandler diaHandler;

    @PostMapping("/generar-recomendados")
    public ResponseEntity<Map<String,String>> generarRecomendados(@RequestBody UsuarioDto usuarioDto){
        diaHandler.generarDiasRecomendados(usuarioDto.getIdUsuario(), usuarioDto.getPeso(), usuarioDto.getMes());
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Collections.singletonMap("Mensaje: ","Dias recomendados generados")
        );
    }
    @PostMapping("generar-dias")
    public ResponseEntity<Map<String,String>> generarMes(@RequestBody UsuarioDto usuarioDto){
        diaHandler.generarDias(usuarioDto.getIdUsuario(), usuarioDto.getMes());
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Collections.singletonMap("Mensaje: ","Mes generados")
        );
    }
    @GetMapping("/obtener-recomendados")
    public List<DiasRecomendados> obtenerRecomendados(@RequestParam("idUsuario") int idUsuario, @RequestParam("mes") int mes){
        return diaHandler.obtenerDiasRecomendados(idUsuario, mes);
    }
}
