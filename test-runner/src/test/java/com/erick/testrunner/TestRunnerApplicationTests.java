package com.erick.testrunner;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.erick.testrunner.domain.Driver;
import com.erick.testrunner.domain.DriverAvailability;
import com.erick.testrunner.domain.DriverTO;
import com.erick.testrunner.domain.Passenger;
import com.erick.testrunner.domain.PassengerTO;
import com.erick.testrunner.domain.TripRequest;
import com.erick.testrunner.util.Utils;

import io.restassured.http.Header;
import io.restassured.response.Response;

@SpringBootTest
class TestRunnerApplicationTests {
	private static final int PORT_DRIVER = 8082;
	//private static final int PORT_TRIP = 8083;
	private static final int PORT_PASSENGER = 8084;
	private static final List<Integer> idDrivers = new ArrayList<>();
	private static final List<Integer> idPassengers = new ArrayList<>();

	@Test
	void startTrips() {
		registerDriversAndPassengers();
		int i = 0;
		while (i < 100) {
		
			passengerRequestTrip(0, "Rua 1", "Rua 2", "Moema");
			driverSignalAvaibility(0, "Rua 1", "Moema");
			
			driverSignalAvaibility(0, "Rua 2", "Suzano");
			driverSignalAvaibility(1, "Rua 10", "Carrão");
			driverSignalAvaibility(2, "Rua 22", "Mocca");
			driverSignalAvaibility(3, "Rua 3", "Alfa Ville");
			driverSignalAvaibility(4, "Rua 5", "Santa Cecilia");
			
			passengerRequestTrip(1, "Rua 10", "Rua 112", "Suzano");
			passengerRequestTrip(2, "Rua 2", "Rua 0", "Carrão");
			passengerRequestTrip(3, "Rua 22", "Rua 10", "Mocca");
			passengerRequestTrip(4, "Rua 3", "Rua 14", "Alfa Ville");
			passengerRequestTrip(0, "Rua 5", "Rua 220", "Santa Cecilia");
			
			i++;
		}
	}

	private void registerDriversAndPassengers() {
		List<Passenger> passengersRegistereds = getPassengersRegistereds();
		List<Driver> driversRegistereds = getDriversRegistereds();
		
		if(driversRegistereds.isEmpty()) {
			registerDrivers();
		} else {
			for (Driver driver: driversRegistereds) {
				idDrivers.add(driver.getId());
			}
		}
		if(passengersRegistereds.isEmpty()) {
			registerPassengers();
		} else {
			for(Passenger passenger: passengersRegistereds) { 
				idPassengers.add(passenger.getId());
			}
		}
	}

	private void registerDrivers() {
		insertDriver(new DriverTO("Erick Luz", getRandomNumber(), getRandomNumber(), "adm", "adm", "asd@asd.com"));
		insertDriver(new DriverTO("Fernando Rubens", getRandomNumber(), getRandomNumber(), "adm", "adm", "asd@asd.com"));
		insertDriver(new DriverTO("Luiz Barreto", getRandomNumber(), getRandomNumber(), "adm", "adm", "asd@asd.com"));
		insertDriver(new DriverTO("Hernesto Filho", getRandomNumber(), getRandomNumber(), "adm", "adm", "asd@asd.com"));
		insertDriver(new DriverTO("Daniel Osorio", getRandomNumber(), getRandomNumber(), "adm", "adm", "asd@asd.com"));
	}

	private void registerPassengers() {
		insertPassenger(new PassengerTO("Maiza Ferraz", getRandomNumber(), getRandomNumber(), "adm", "adm", "asd@asd.com"));
		insertPassenger(new PassengerTO("Diego Silva", getRandomNumber(), getRandomNumber(), "adm", "adm", "asd@asd.com"));
		insertPassenger(new PassengerTO("Denise Giga", getRandomNumber(), getRandomNumber(), "adm", "adm", "asd@asd.com"));
		insertPassenger(new PassengerTO("Carlos Daniel", getRandomNumber(), getRandomNumber(), "adm", "adm", "asd@asd.com"));
		insertPassenger(new PassengerTO("Samuel Silva", getRandomNumber(), getRandomNumber(), "adm", "adm", "asd@asd.com"));
	}
	

	private List<Passenger> getPassengersRegistereds() {
		Response response = given()
		.port(PORT_PASSENGER)
		.basePath("/passenger")
		.when()
		.get();
		
		String json = response.getBody().asString();
		List<Passenger> passengers = Utils.fromJsonListPassenger(json);
		
		response.then()
		.statusCode(HttpStatus.SC_OK);
		return passengers;
	}

	private List<Driver> getDriversRegistereds() {
		Response response = given()
		.port(PORT_DRIVER)
		.basePath("/driver")
		.when()
		.get();
		
		List<Driver> drivers = Utils.fromJsonListDriver(response.getBody().asString());
		
		response.then()
		.statusCode(HttpStatus.SC_OK);
		return drivers;
	}

	private void passengerRequestTrip(Integer indexPassenger, String originAddress, String destinyAddress,String district) {
		TripRequest tripRequest = new TripRequest(idPassengers.get(indexPassenger), originAddress, destinyAddress, district); 
		given()
			.port(PORT_PASSENGER)
			.basePath("/passenger")
			.body(Utils.toJson(tripRequest))
			.when()
			.header(new Header("Content-Type", "application/json"))
			.post("/trip")
			.then()
			.statusCode(HttpStatus.SC_NO_CONTENT);
	}

	private void driverSignalAvaibility(Integer indexDriver, String currentAddress, String district) {
		DriverAvailability driverAvailability = new DriverAvailability(indexDriver, currentAddress, district);
		given()
			.port(PORT_DRIVER)
			.basePath("/driver")
			.body(Utils.toJson(driverAvailability))
			.when()
			.header(new Header("Content-Type", "application/json"))
			.post("/trip")
			.then()
			.statusCode(HttpStatus.SC_NO_CONTENT);
	}

	private Integer insertPassenger(PassengerTO passenger) {
		Response response = given()
				.port(PORT_PASSENGER)
				.basePath("/passenger")
				.when()
				.header(new Header("Content-Type", "application/json"))
				.body(Utils.toJson(passenger))
				.post();			
			
				response.then()
				.statusCode(HttpStatus.SC_CREATED);
				
				String location = response.header("Location");
				Integer idPassenger = Integer.valueOf(location.substring(location.lastIndexOf("/")+1));
				System.out.println("Passenger saved: " + idPassenger);
				idPassengers.add(idPassenger);
				return idPassenger;
	}
	
	private Integer insertDriver(DriverTO driver) {
		Response response = given()
			.port(PORT_DRIVER)
			.basePath("/driver")
			.when()
			.header(new Header("Content-Type", "application/json"))
			.body(Utils.toJson(driver))
			.post();			
		
			response.then()
			.statusCode(HttpStatus.SC_CREATED);
			
			String location = response.header("Location");
			Integer idDriver = Integer.valueOf(location.substring(location.lastIndexOf("/")+1));
			System.out.println("Driver saved: " + idDriver);
			idDrivers.add(idDriver);
			return idDriver;
	}
	
	public String getRandomNumber() {
		int min = 1000;
		int max = 5000;
		return String.valueOf((int) Math.floor(Math.random()*(max-min+1)+min));
	}

}
