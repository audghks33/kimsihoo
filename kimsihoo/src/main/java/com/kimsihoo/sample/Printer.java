package com.kimsihoo.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;

@Component
@Data
public class Printer {

	@Setter(onMethod_ = @Autowired)
	private PrintPage printPage;
}
