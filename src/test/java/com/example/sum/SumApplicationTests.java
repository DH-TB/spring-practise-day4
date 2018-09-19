package com.example.sum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

class SumApplicationTests {
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		mockMvc = standaloneSetup(new DivisionController()).build();
	}

	@Test
	void should_get_success() throws Exception {
		ResultActions perform = mockMvc.perform(get("/division")
				.param("divider", "10.0")
				.param("dividend", "5.0"));

		perform.andDo(print());
		perform.andExpect(status().isOk());
		perform.andExpect(content().string("2.0"));
	}

	@Test
	void should_get_4XX_error_when_dividend_is_zero() throws Exception {
		ResultActions perform = mockMvc.perform(get("/division")
				.param("divider", "10.0")
				.param("dividend", "0.0"));

		perform.andDo(print());
		perform.andExpect(status().is4xxClientError());
	}

	@Test
	void should_get_4XX_error_when_input_contains_NaN() throws Exception {
		ResultActions perform = mockMvc.perform(get("/division")
				.param("divider", "NaN")
				.param("dividend", "2"));

		perform.andDo(print());
		perform.andExpect(status().is4xxClientError());
	}

	@Test
	void should_get_4XX_error_when_input_contains_Infinite() throws Exception {
		ResultActions perform = mockMvc.perform(get("/division")
				.param("divider", "Infinite")
				.param("dividend", "2"));

		perform.andDo(print());
		perform.andExpect(status().is4xxClientError());
	}
}
