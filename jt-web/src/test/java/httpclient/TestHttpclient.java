package httpclient;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TestHttpclient {
	@Test
	public void getInfo() throws ClientProtocolException, IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		String url = "http://manage.jt.com/page/index";
		HttpGet get = new HttpGet(url);	
		 CloseableHttpResponse response = httpClient.execute(get);
		 String body = EntityUtils.toString(response.getEntity(),"utf-8");
		 System.out.println(body);
	}
}
