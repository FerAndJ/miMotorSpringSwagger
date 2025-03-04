package com.projectoFinalMotorsport.demo;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.projectoFinalMotorsport.demo.model.*;
import com.projectoFinalMotorsport.demo.service.AutoService;
import com.projectoFinalMotorsport.demo.service.CarreraService;
import com.projectoFinalMotorsport.demo.service.EquipoService;
import com.projectoFinalMotorsport.demo.service.PilotoService;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private AutoService autoService;

	@Autowired
	private CarreraService carreraService;

	@Autowired
	private EquipoService equipoService;

	@Autowired
	private PilotoService pilotoService;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	

	@Override
	public void run(String... args) throws Exception {
		
		
		Equipo astonMartin = Equipo.builder()
			.nombre("Aramco Aston Martin")
			.nacionalidad("Inglesa")
			.victoriasTotales(85)
			.sede("Silverstone")
			.esMotorista(false)
			.build();
		
		
		Equipo mercedes = Equipo.builder()
    		.nombre("Mercedes-AMG")
    		.nacionalidad("Alemana")
    		.victoriasTotales(125)
    		.sede("Brackley, Reino Unido")
    		.esMotorista(true)
    		.build();

		Equipo mazda = Equipo.builder()
			.nombre("Mazda speed")
			.nacionalidad("Japonesa")
			.victoriasTotales(1)
			.sede("Fucho, Japon")
			.esMotorista(true)
			.build();
			
			equipoService.save(astonMartin);
			equipoService.save(mercedes);
			equipoService.save(mazda);
		
		
		Auto amValkyrie1 = new Auto();
			amValkyrie1.setCargaAerodinamica(8000);
			amValkyrie1.setEquipo(astonMartin);
			amValkyrie1.setEsHibrido(false);
			amValkyrie1.setMarca("Aston Martin");
			amValkyrie1.setModelo("Valkyrie");
			amValkyrie1.setMotor(Motor.V12);
			amValkyrie1.setPeso(980);
			amValkyrie1.setPotencia(900);
			amValkyrie1.setTorque(700);

		Auto c12 = new Auto();
			c12.setCargaAerodinamica(7500);
			c12.setEquipo(mercedes);
			c12.setEsHibrido(true);
			c12.setMarca("Mercedes");
			c12.setModelo("W14");
			c12.setMotor(Motor.V6_TURBO);
			c12.setPeso(795);
			c12.setPotencia(950);
			c12.setTorque(800);

			autoService.save(amValkyrie1);
			autoService
			.save(c12);

		Auto mazda787B = new Auto();
			mazda787B.setCargaAerodinamica(6000); 
			mazda787B.setEquipo(mazda); 
			mazda787B.setEsHibrido(false); 
			mazda787B.setMarca("Mazda");
			mazda787B.setModelo("787B");
			mazda787B.setMotor(Motor.ROTATIVO_WANKEL); 
			mazda787B.setPeso(830); 
			mazda787B.setPotencia(700); 
			mazda787B.setTorque(600); 
			
			autoService.save(mazda787B);

		Carrera sebring = Carrera.builder()
            .autodromo("Sebring International Raceway")
            .gamaNeumaticos(GamaNeumaticos.MEDIA)
            .horario(LocalDateTime.of(2025, 3, 15, 14, 0))
            .kmVuelta(8)
            .nombre("Gran premio de Sebring")
            .numerosVueltas(60)
            .pilotos(null) // O una lista de pilotos, si la tienes
            .probabilidadSafetyCar(0.4)
            .temperaturaPromedio(18)
            .ubicacion("Florida, Estados Unidos")
            .build();


		Carrera monaco = Carrera.builder()
			.autodromo("Circuito de Mónaco")
			.gamaNeumaticos(GamaNeumaticos.BLANDA)
			.horario(LocalDateTime.of(2025, 5, 26, 15, 0))
			.kmVuelta(3400)
			.nombre("Gran Premio de Mónaco")
			.numerosVueltas(78)
			.pilotos(null) // O una lista de pilotos, si la tienes
			.probabilidadSafetyCar(0.7)
			.temperaturaPromedio(25)
			.ubicacion("Mónaco")
			.build();
	
			carreraService.save(sebring);
			carreraService.save(monaco);
		
		Piloto alonso = Piloto.builder()
			.nombre("Fernando Alonso")
			.peso(68)
			.numero(14)
			.nacionalidad("Española")
			.categoria(Categoria.FORMULA_1)
			.equipo(astonMartin)
			.carreras(Arrays.asList(sebring, monaco))
			.auto(amValkyrie1)
			.build();

		

		Piloto hamilton = Piloto.builder()
    		.nombre("Lewis Hamilton")
    		.peso(73)
    		.numero(44)
			.nacionalidad("Inglesa")
    		.categoria(Categoria.FORMULA_1)
    		.equipo(mercedes)
    		.carreras(Arrays.asList(sebring, monaco))
    		.auto(c12)
    		.build();

			pilotoService.save(alonso);
			pilotoService.save(hamilton);
		
		/*
		 NuevoPilotoDTO para POST en POSTMAN

		 {
        "id": 3,
        "nombre": "Kamui Kobayashi",
        "peso": 63,
        "numero": 15,
        "nacionalidad": "Japonesa",
        "categoria": "IMSA",
        "equipo": "Mazda speed",
        "carreras": [
            "Gran premio de Sebring",
            "Gran Premio de Mónaco"
        ],
        "auto": "787B"
    }


		 */
		

		
	}
	
}
