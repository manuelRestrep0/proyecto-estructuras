package com.manuel.proyecto.configuration;

import com.manuel.proyecto.adapters.driven.jpa.mysql.adapter.AlimentoMysqlAdapter;
import com.manuel.proyecto.adapters.driven.jpa.mysql.adapter.DiaMysqlAdapter;
import com.manuel.proyecto.adapters.driven.jpa.mysql.mapper.AlimentoEntityMapper;
import com.manuel.proyecto.adapters.driven.jpa.mysql.mapper.DiasRecomendadosMapper;
import com.manuel.proyecto.adapters.driven.jpa.mysql.mapper.TotalDiarioUsuarioMapper;
import com.manuel.proyecto.adapters.driven.jpa.mysql.repository.IAlimentoRepository;
import com.manuel.proyecto.adapters.driven.jpa.mysql.repository.IDiasRecomendadosRepository;
import com.manuel.proyecto.adapters.driven.jpa.mysql.repository.TotalDiarioUsuarioRepository;
import com.manuel.proyecto.adapters.driven.jpa.mysql.repository.UsuariosAlimentosRepository;
import com.manuel.proyecto.adapters.drivening.http.Handlers.IDiaHandler;
import com.manuel.proyecto.domain.api.IAlimentoServicePort;
import com.manuel.proyecto.domain.api.IDiaServicePort;
import com.manuel.proyecto.domain.spi.AlimentoPersistencePort;
import com.manuel.proyecto.domain.spi.DiaPersistencePort;
import com.manuel.proyecto.domain.usecase.AlimentoUseCase;
import com.manuel.proyecto.domain.usecase.DiaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final DiasRecomendadosMapper diasRecomendadosMapper;
    private final IDiasRecomendadosRepository diasRecomendadosRepository;
    private final AlimentoEntityMapper alimentoEntityMapper;
    private final IAlimentoRepository alimentoRepository;
    private final UsuariosAlimentosRepository usuariosAlimentosRepository;
    private final TotalDiarioUsuarioRepository totalDiarioUsuarioRepository;
    private final TotalDiarioUsuarioMapper totalDiarioUsuarioMapper;

    @Bean
    public IDiaServicePort diaServicePort(){
        return new DiaUseCase(diaPersistencePort());
    }
    @Bean
    public DiaPersistencePort diaPersistencePort(){
        return new DiaMysqlAdapter(diasRecomendadosRepository,diasRecomendadosMapper,totalDiarioUsuarioRepository,totalDiarioUsuarioMapper);
    }
    @Bean
    public AlimentoPersistencePort alimentoPersistencePort(){
        return new AlimentoMysqlAdapter(alimentoRepository,alimentoEntityMapper,usuariosAlimentosRepository,totalDiarioUsuarioRepository,totalDiarioUsuarioMapper);
    }
    @Bean
    public IAlimentoServicePort alimentoServicePort(){
        return new AlimentoUseCase(alimentoPersistencePort(),diaPersistencePort());
    }
}
