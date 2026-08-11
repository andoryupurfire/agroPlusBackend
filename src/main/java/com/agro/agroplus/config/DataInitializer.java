package com.agro.agroplus.config;


import com.agro.agroplus.entity.EtapaFenologica;
import com.agro.agroplus.entity.TipoCultivo;
import com.agro.agroplus.entity.Variedad;
import com.agro.agroplus.repository.TipoCultivoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final TipoCultivoRepository tipoCultivoRepository;

    @Override
    public void run (ApplicationArguments args){
        if (tipoCultivoRepository.count() > 0){
            System.out.println("Catalogo ya existen - seed omitido");
            return;
        }

        seedCacao();
        seedCafe();
        seedPlatano();
        seedMaiz();
        System.out.println(" Cargado correctamente ");
    }

    private void seedCacao() {
        TipoCultivo cacao = TipoCultivo.builder()
                .nombre("Cacao")
                .tipoCiclo("Perenne")
                .variedades(List.of())
                .etapasFenologicas(List.of())
                .build();

        cacao.setVariedades(List.of(
                Variedad.builder().nombre("CCN-51")
                        .distPlantas(3.0).distSurcos(3.0)
                        .densidad(1111).tipoCultivo(cacao).build(),
                Variedad.builder().nombre("ICS-95")
                        .distPlantas(3.5).distSurcos(3.5)
                        .densidad(816).tipoCultivo(cacao).build(),
                Variedad.builder().nombre("Nacional Fino")
                        .distPlantas(4.0).distSurcos(4.0)
                        .densidad(625).tipoCultivo(cacao).build()
        ));

        cacao.setEtapasFenologicas(List.of(
                EtapaFenologica.builder().nombre("Siembra").orden(1)
                        .duracionMinimaDia(1).duracionMaximaDia(7)
                        .tipoCultivo(cacao).build(),
                EtapaFenologica.builder().nombre("Brotación").orden(2)
                        .duracionMinimaDia(14).duracionMaximaDia(30)
                        .tipoCultivo(cacao).build(),
                EtapaFenologica.builder().nombre("Vegetativo").orden(3)
                        .duracionMinimaDia(60).duracionMaximaDia(120)
                        .tipoCultivo(cacao).build(),
                EtapaFenologica.builder().nombre("Floración").orden(4)
                        .duracionMinimaDia(14).duracionMaximaDia(21)
                        .tipoCultivo(cacao).build(),
                EtapaFenologica.builder().nombre("Cuajado").orden(5)
                        .duracionMinimaDia(14).duracionMaximaDia(21)
                        .tipoCultivo(cacao).build(),
                EtapaFenologica.builder().nombre("Desarrollo fruto").orden(6)
                        .duracionMinimaDia(120).duracionMaximaDia(150)
                        .tipoCultivo(cacao).build(),
                EtapaFenologica.builder().nombre("Cosecha").orden(7)
                        .duracionMinimaDia(14).duracionMaximaDia(30)
                        .tipoCultivo(cacao).build()
        ));

        tipoCultivoRepository.save(cacao);
    }

    private void seedCafe(){
        TipoCultivo cafe = TipoCultivo.builder()
                .nombre("Café")
                .tipoCiclo("Perenne")
                .variedades(List.of())
                .etapasFenologicas(List.of())
                .build();

        cafe.setVariedades(List.of(
                // Castillo y Colombia son similares en vigor (porte medio-alto)
                Variedad.builder().nombre("Castillo").distPlantas(1.0).distSurcos(2.0)
                        .densidad(5000).tipoCultivo(cafe).build(),
                Variedad.builder().nombre("Colombia").distPlantas(1.0).distSurcos(2.0)
                        .densidad(5000).tipoCultivo(cafe).build(),
                // Caturra es de porte bajo, permite mayor densidad
                Variedad.builder().nombre("Caturra").distPlantas(1.0).distSurcos(1.8)
                        .densidad(5500).tipoCultivo(cafe).build(),
                // Cenicafe 1 es similar a Castillo
                Variedad.builder().nombre("Cenicafé").distPlantas(1.0).distSurcos(2.0)
                        .densidad(5000).tipoCultivo(cafe).build()
        ));

        cafe.setEtapasFenologicas(List.of(
                EtapaFenologica.builder()
                        .nombre("Germinación")
                        .duracionMinimaDia(45)
                        .duracionMaximaDia(60)
                        .orden(1)
                        .tipoCultivo(cafe)
                        .build(),
                EtapaFenologica.builder().nombre("Almacigo")
                        .duracionMinimaDia(120)
                        .duracionMaximaDia(180)
                        .orden(2)
                        .tipoCultivo(cafe)
                        .build(),
                EtapaFenologica.builder()
                        .nombre("Transplante")
                        .duracionMinimaDia(15)
                        .duracionMaximaDia(30)
                        .orden(3)
                        .tipoCultivo(cafe)
                        .build(),
                EtapaFenologica.builder()
                        .nombre("Vegetativo")
                        .duracionMinimaDia(240)
                        .duracionMaximaDia(300)
                        .orden(4).tipoCultivo(cafe)
                        .build(),
                EtapaFenologica.builder()
                        .nombre("Floración")
                        .duracionMinimaDia(5)
                        .duracionMaximaDia(10)
                        .orden(5)
                        .tipoCultivo(cafe)
                        .build(),
                EtapaFenologica.builder()
                        .nombre("Cuajado")
                        .duracionMinimaDia(30)
                        .duracionMaximaDia(45)
                        .orden(6)
                        .tipoCultivo(cafe)
                        .build(),
                EtapaFenologica.builder()
                        .nombre("Maduración")
                        .duracionMinimaDia(180)
                        .duracionMaximaDia(240)
                        .orden(7)
                        .tipoCultivo(cafe)
                        .build(),
                EtapaFenologica.builder()
                        .nombre("Cosecha")
                        .duracionMinimaDia(30)
                        .duracionMaximaDia(90)
                        .orden(8)
                        .tipoCultivo(cafe)
                        .build()
        ));

        tipoCultivoRepository.save(cafe);
    }

    private void seedPlatano(){
        TipoCultivo platano = TipoCultivo.builder().
                nombre("Plátano").
                tipoCiclo("Perenne").
                variedades(List.of()).
                etapasFenologicas(List.of()).
                build();

        platano.setVariedades(List.of(
                Variedad.builder().nombre("Dominico hartón")
                        .distPlantas(3.0)
                        .distSurcos(3.0)
                        .densidad(1111)
                        .tipoCultivo(platano)
                        .build(),
                Variedad.builder()
                        .nombre("FHIA-20")
                        .distPlantas(2.5)
                        .distSurcos(3.0)
                        .densidad(1333)
                        .tipoCultivo(platano)
                        .build(),
                Variedad.builder()
                        .nombre("Williams")
                        .distPlantas(2.5)
                        .distSurcos(2.5)
                        .densidad(1600)
                        .tipoCultivo(platano)
                        .build()
        ));

        platano.setEtapasFenologicas(List.of(
                EtapaFenologica.builder()
                        .nombre("Siembra Colino")
                        .duracionMinimaDia(15)
                        .duracionMaximaDia(30)
                        .orden(1)
                        .tipoCultivo(platano)
                        .build(),
                EtapaFenologica.builder()
                        .nombre("Macollamiento")
                        .duracionMinimaDia(90)
                        .duracionMaximaDia(150)
                        .orden(2)
                        .tipoCultivo(platano)
                        .build(),
                EtapaFenologica.builder()
                        .nombre("Emisión")
                        .duracionMinimaDia(60)
                        .duracionMaximaDia(90)
                        .orden(3)
                        .tipoCultivo(platano)
                        .build(),
                EtapaFenologica.builder()
                        .nombre("Parición")
                        .duracionMinimaDia(15)
                        .duracionMaximaDia(30)
                        .orden(4)
                        .tipoCultivo(platano)
                        .build(),
                EtapaFenologica.builder()
                        .nombre("Llenado")
                        .duracionMinimaDia(60)
                        .duracionMaximaDia(120)
                        .orden(5)
                        .tipoCultivo(platano)
                        .build(),
                EtapaFenologica.builder()
                        .nombre("Cosecha")
                        .duracionMinimaDia(15)
                        .duracionMaximaDia(30)
                        .orden(6)
                        .tipoCultivo(platano)
                        .build(),
                EtapaFenologica.builder()
                        .nombre("Retoño")
                        .duracionMinimaDia(180)
                        .duracionMaximaDia(300)
                        .orden(7)
                        .tipoCultivo(platano)
                        .build()
        ));

        tipoCultivoRepository.save(platano);
    }

    private void seedMaiz(){
        TipoCultivo maiz = TipoCultivo.builder()
                .nombre("Maíz")
                .tipoCiclo("Anual")
                .variedades(List.of())
                .etapasFenologicas(List.of())
                .build();

        maiz.setVariedades(List.of(
                Variedad.builder().nombre("ICA V-109")
                        .distPlantas(0.40)
                        .distSurcos(0.60)
                        .densidad(41666)
                        .tipoCultivo(maiz)
                        .build(),
                Variedad.builder().nombre("Híbridos comerciales")
                        .distPlantas(0.20)
                        .distSurcos(0.80)
                        .densidad(62500)
                        .tipoCultivo(maiz)
                        .build()
        ));

        maiz.setEtapasFenologicas(List.of(
                EtapaFenologica.builder()
                        .nombre("Germinación")
                        .duracionMinimaDia(4)
                        .duracionMaximaDia(8)
                        .orden(1)
                        .tipoCultivo(maiz)
                        .build(),

                EtapaFenologica.builder()
                        .nombre("Emergencia")
                        .duracionMinimaDia(5)
                        .duracionMaximaDia(10)
                        .orden(2)
                        .tipoCultivo(maiz)
                        .build(),

                EtapaFenologica.builder()
                        .nombre("Crecimiento Vegetativo")
                        .duracionMinimaDia(40)
                        .duracionMaximaDia(60)
                        .orden(3)
                        .tipoCultivo(maiz)
                        .build(),

                EtapaFenologica.builder()
                        .nombre("Floración")
                        .duracionMinimaDia(7)
                        .duracionMaximaDia(14)
                        .orden(4)
                        .tipoCultivo(maiz)
                        .build(),

                EtapaFenologica.builder()
                        .nombre("Formación de Grano")
                        .duracionMinimaDia(15)
                        .duracionMaximaDia(25)
                        .orden(5)
                        .tipoCultivo(maiz)
                        .build(),

                EtapaFenologica.builder()
                        .nombre("Llenado de Grano")
                        .duracionMinimaDia(20)
                        .duracionMaximaDia(35)
                        .orden(6)
                        .tipoCultivo(maiz)
                        .build(),

                EtapaFenologica.builder()
                        .nombre("Madurez y Cosecha")
                        .duracionMinimaDia(15)
                        .duracionMaximaDia(30)
                        .orden(7)
                        .tipoCultivo(maiz)
                        .build()
        ));
        tipoCultivoRepository.save(maiz);

    }


}
