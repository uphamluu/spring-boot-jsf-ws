/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.acme.sample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.phamluu.SpringBootFacesApplication;

/**
 * Basic integration tests.
 *
 * @author Phillip Webb
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootFacesApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
@DirtiesContext
public class SpringBootFacesApplicationTests {

	@Value("${local.server.port}")
	private int port;
	
	@Value("${management.context-path:}")
	private String actuatorPath;

	@Test
	public void testJsfWelcomePage() throws Exception {
		ResponseEntity<String> entity = new TestRestTemplate().getForEntity("http://localhost:" + this.port, String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertTrue("Wrong body:\n" + entity.getBody(), entity.getBody().contains("javax.faces.ViewState"));
	}

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Test
    public void testActuatorHealthPage() throws Exception {
        ResponseEntity<Map> entity = new TestRestTemplate().getForEntity("http://localhost:" + this.port + actuatorPath + "/health", Map.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        
        Map<String, Object> body = entity.getBody();
        assertTrue("Wrong body:\n" + entity.getBody(), body.containsKey("status"));
    }

}
