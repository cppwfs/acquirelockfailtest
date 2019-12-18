/*
 * Copyright 2017 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.spring.taskfaildemo;

import org.junit.Rule;
import org.junit.Test;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.test.rule.OutputCapture;

import static junit.framework.Assert.assertTrue;

public class TestSignal {

	@Rule
	public OutputCapture outputCapture = new OutputCapture();
	@Test
	public void testMe() {
		SpringApplicationBuilder app = new SpringApplicationBuilder(TaskfaildemoApplication.class);
		app.build().addListeners(new ApplicationPidFileWriter("/Users/glennrenfro/project/samples/taskfaildemo/shutdown.pid"));
		app.run();

		assertTrue(outputCapture.toString().contains("TaskExecution with executionId=1 with the following {exitCode=130"));
	}

	@Test
	public void anothertest() {
	}
}