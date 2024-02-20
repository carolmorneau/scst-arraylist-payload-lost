package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.messaging.support.MessageBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {
		TestChannelBinderConfiguration.class,
		SampleMinimalApplication.class }
)
class SampleMinimalApplicationTests {

	@Autowired
	private InputDestination input;

	@Autowired
	private OutputDestination output;

	@Test
	public void testSpringCloudFunctionIssueWhereArrayListPayloadsLoseTheirElements() {
		ArrayList<String> list = new ArrayList<>();
		list.add("aSimpleStringElement");

		this.input.send(MessageBuilder.withPayload(list).build());
		Object payload = output.receive().getPayload();

		assertThat(payload).isInstanceOf(ArrayList.class);
		List<String> payloadAsList = (ArrayList<String>) payload;
		assertThat(payloadAsList).containsExactly("aSimpleStringElement");
	}

}
