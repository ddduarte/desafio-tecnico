package com.desfio.tec.ca.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.desfio.tec.ca.model.Coords;
import com.desfio.tec.ca.model.DirectionEnum;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class Controller {

	private RobotController controller = mock(RobotController.class);
	
	//@Test(expected = Exception.class)
	@Test
	public void testCoords() throws Exception {
		Coords result = new Coords(0, 0, DirectionEnum.N);
		when(controller.move(anyString())).thenReturn(result);
		Coords c = controller.move("FAKE_COORDS");
		assertThat(c.toString()).isEqualTo(result.toString());
	}
	
	
}
