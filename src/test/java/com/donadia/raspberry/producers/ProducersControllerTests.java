package com.donadia.raspberry.producers;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.donadia.raspberry.producers.Responses.ProducerAward;

@SpringBootTest
@AutoConfigureMockMvc
class ProducersControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void ShouldValidateReturnAccordingToCSVFile() throws Exception {
		ProducerAward joelSilver = new ProducerAward("Joel Silver", 1, 1990, 1991);
		ProducerAward matthewVaughn = new ProducerAward("Matthew Vaughn", 13, 2002, 2015);
	
		this.mockMvc.perform(get("/producers/award-interval").accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.min", hasSize(1)))
				.andExpect(jsonPath("$.max", hasSize(1)))
				.andExpect(jsonPath("$.min[0]").value(joelSilver))
				.andExpect(jsonPath("$.max[0]").value(matthewVaughn));
	}
}
