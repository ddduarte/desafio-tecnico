package com.desfio.tec.ca.resources;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RobotResourceTest {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	@DirtiesContext
	public void testInvalidCoords() {
		ResponseEntity<String> entity = this.restTemplate.postForEntity("/rest/mars/MMMAA", null, String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		assertThat(entity.getBody()).isEqualTo("Coordenadas contem caracteres invalidos!");
	}
	
	@Test
	@DirtiesContext
	public void testBlankCoords() {
		ResponseEntity<String> entity = this.restTemplate.postForEntity("/rest/mars/", null, String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@Test
	@DirtiesContext
	public void testInvalidCoordsWithBlankCommand() {
		ResponseEntity<String> entity = this.restTemplate.postForEntity("/rest/mars/M M", null, String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		assertThat(entity.getBody()).isEqualTo("Coordenadas contem caracteres invalidos!");
	}
	
	@Test
	@DirtiesContext
	public void testRepeatedCoords() {
		ResponseEntity<String> entity = this.restTemplate.postForEntity("/rest/mars/MMM", null, String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody()).isEqualTo("(0,3,N)");
		entity = this.restTemplate.postForEntity("/rest/mars/MMM", null, String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody()).isEqualTo("(0,3,N)");
	}
	
	@Test
	@DirtiesContext
	public void testCaseCoords() {
		ResponseEntity<String> entity = this.restTemplate.postForEntity("/rest/mars/mmm", null, String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody()).isEqualTo("(0,3,N)");
	}
	
	@Test
	@DirtiesContext
	public void testInvalidCoordsMaxBoundsY() {
		ResponseEntity<String> entity = this.restTemplate.postForEntity("/rest/mars/MMMMMM", null, String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		assertThat(entity.getBody()).isEqualTo("Coordenadas invalidas!");
	}
	
	@Test
	@DirtiesContext
	public void testInvalidCoordsMaxBoundsX() {
		ResponseEntity<String> entity = this.restTemplate.postForEntity("/rest/mars/RMMMMMM", null, String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		assertThat(entity.getBody()).isEqualTo("Coordenadas invalidas!");
	}
	
	@Test
	@DirtiesContext
	public void testMoveRight() {
		ResponseEntity<String> entity = this.restTemplate.postForEntity("/rest/mars/RMMM", null, String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody()).isEqualTo("(3,0,W)");
	}
	
	@Test
	@DirtiesContext
	public void testMoveRightAndLeft() {
		ResponseEntity<String> entity = this.restTemplate.postForEntity("/rest/mars/RMMMLMMMM", null, String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody()).isEqualTo("(3,4,N)");
	}
	
	@Test
	@DirtiesContext
	public void testMove360() {
		ResponseEntity<String> entity = this.restTemplate.postForEntity("/rest/mars/RMMMMMLMMMMMLMMMMMLMMMMM", null, String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody()).isEqualTo("(0,0,S)");
	}
	
	@Test
	@DirtiesContext
	public void testSeveralCoords() {
		ResponseEntity<String> entity = this.restTemplate.postForEntity("/rest/mars/RMMMLMMMM", null, String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody()).isEqualTo("(3,4,N)");
		entity = this.restTemplate.postForEntity("/rest/mars/LM", null, String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody()).isEqualTo("(2,4,E)");
	}

	@Test
	@DirtiesContext
	public void testGetCoords() { 
		ResponseEntity<String> entity = this.restTemplate.postForEntity("/rest/mars/RMMMLMMMM", null, String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody()).isEqualTo("(3,4,N)");
		entity = this.restTemplate.getForEntity("/rest/mars", String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody()).isEqualTo("(3,4,N)");
	}
	
}
