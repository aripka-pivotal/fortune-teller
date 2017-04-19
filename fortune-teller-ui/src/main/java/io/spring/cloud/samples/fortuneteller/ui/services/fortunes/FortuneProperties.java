package io.spring.cloud.samples.fortuneteller.ui.services.fortunes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@RefreshScope
@Component
public class FortuneProperties {

	@Value("${fortune.fallbackFortune:Your future is unclear.}")
	private String fallbackFortune;

	public String getFallbackFortune() {
		return fallbackFortune;
	}

	public void setFallbackFortune(String fallbackFortune) {
		this.fallbackFortune = fallbackFortune;
	}

}
