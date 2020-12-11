package com.rajesh.e;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testRetrieveStudentCourse() throws JSONException, MalformedURLException {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/findTopTenFrequentWords/url/" + URLEncoder.encode("https://www.314e.com/", StandardCharsets.UTF_8) + "/hierarchy/4/numOfFrequentWords/10"),
                HttpMethod.GET, entity, String.class);
        String expected = "[\"the\",\"to\",\"and\",\"EHR\",\"of\",\"a\",\"Services\",\"Data\",\"in\",\"for\"]";
        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

	@Test
	public void getTopTenFrequentWordParis() throws JSONException, MalformedURLException {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/findTopTenFrequentWordPairs/url/" + URLEncoder.encode("https://www.314e.com/", StandardCharsets.UTF_8) + "/hierarchy/4/numOfFrequentWords/10"),
				HttpMethod.GET, entity, String.class);
		System.out.println("-> "+response.getBody());
		String expected = "[\"Cures Act\",\"EHR Services\",\"Healthcare IT\",\"Services EHR\",\"Staff Augmentation\",\"Data Analytics\",\"Data Science\",\"of the\",\"Digital Learning\",\"Services Healthcare\"]\n";
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}


	private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
