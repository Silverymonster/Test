package myx.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;
@Service("service")
public class serviceimp implements service {

	
	private RequestConfig requestConfig; 
	public void setRequestConfig(RequestConfig requestConfig) {
		this.requestConfig = requestConfig;
	}

	private BeanFactory beanFactory;
	/**
     * �������������ʹ�ö������������
     * �磺����ApiServiceʹ��@Serviceע��Ĭ���ǵ���ģʽ���������ö���httpclientֻʵ����һ�Σ�
     * ��httpclient��Ҫ�������ɲο�����Spring������scope="prototype"��
     * ����bean�����ֶ���ȡhttpclientʵ������֤ÿ�ζ��ǲ�ͬʵ����
     */ 
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory=beanFactory;
		
	} 
	
	
	 private CloseableHttpClient getHttpClient() {
	        return (CloseableHttpClient) this.beanFactory.getBean("closeableHttpClient");
	  }

	/**
     * GET���� �޲�
     * 
     * @param url
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */ 
	public String doGet(String url) throws Exception { 
		// ����http GET���� 
		HttpGet httpGet = new HttpGet(url); 
		httpGet.setConfig(this.requestConfig); 
		CloseableHttpResponse response = null; 
		try { 
			// ִ������ 
			response = getHttpClient().execute(httpGet); 
			// �жϷ���״̬�Ƿ�Ϊ200 
			if (response.getStatusLine().getStatusCode() == 200) { 
				return EntityUtils.toString(response.getEntity(), "UTF-8"); 
				} 
			} finally { 
				if (response != null) { 
					response.close(); 
					} 
				} 
		return null; 
	} 
	 /**
     * GET �����в���
     * 
     * @param url
     * @param params
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     * @throws URISyntaxException
     */ 
     public String doGet(String url, Map<String, String> params) throws Exception { 
    	 if (null == params) return this.doGet(url); 
    	 URIBuilder uriBuilder = new URIBuilder(); 
    	 for (Map.Entry<String, String> entry : params.entrySet()) { 
    		 uriBuilder.addParameter(entry.getKey(), entry.getValue()); 
    	} 
    	 return this.doGet(uriBuilder.build().toString()); 
     }

     
     /**
      * POST����
      * 
      * @param url
      * @return
      * @throws ClientProtocolException
      * @throws IOException
      */ 
     public String doPost(String url) throws Exception { 
    	 return doPost(url, null); 
    }

     
     /**
      * POST���󣬴�����
      * 
      * @param url
      * @return
      * @throws ClientProtocolException
      * @throws IOException
      */ 
     public String doPost(String url, Map<String, String> params) throws Exception { 
    	 // ����http POST���� 
    	 HttpPost httpPost = new HttpPost(url); 
    	 if (null != params) { 
    		 ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>(); 
    		 
    		 for (Map.Entry<String, String> entry : params.entrySet()) { 
    			 pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue())); 
    		 } 
    		 
    		 // ����һ��form��ʽ��ʵ�� 
    		 UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairs); 
    		 // ������ʵ�����õ�httpPost������ 
    		 httpPost.setEntity(entity); 
    	 } 
    	 httpPost.setConfig(this.requestConfig); 
    	 // αװ������� 
    		/*httpPost.setHeader("Accept","application/json");
    		httpPost.addHeader("Content-Type","application/json;charset=UTF-8");*/
    		//httpPost.setEntity(new StringEntity(url, Charset.forName("UTF-8")));
    		httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36"); 
    	 CloseableHttpResponse response = null; 
    	 try { // ִ������ 
    		 response = getHttpClient().execute(httpPost);
    		 if(response.getStatusLine().getStatusCode()==200) {
    			 return EntityUtils.toString(response.getEntity(), "UTF-8");
    		 }else {
 				return null;
 			}
    	  }finally { 
    			 if (response != null) { 
    				 response.close(); 
    				 } 
    	 } 
    }

	/**
	 * POST�����ύjson����
	 * 
	 * @param url
	 * @param json
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */ 
	public String doPostJson(String url, String json) throws Exception { 
		// ����http POST���� 
		HttpPost httpPost = new HttpPost(url); 
		httpPost.setConfig(this.requestConfig); 
		if (json != null) { 
			// ����һ��form��ʽ��ʵ�� 
			StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON); 
			// ������ʵ�����õ�httpPost������ 
			httpPost.setEntity(stringEntity); 
		} 
		CloseableHttpResponse response = null; 
		try { 
			// ִ������ 
			response = this.getHttpClient().execute(httpPost); 
			System.out.println("stats:"+response.getStatusLine().getStatusCode());
			if(response.getStatusLine().getStatusCode()==200) {
				return EntityUtils.toString(response.getEntity(), "UTF-8"); 
			}else {
				return "δ�ܻ�ȡ���ݣ�";
			}
		} finally { 
				if (response != null) { 
					response.close(); 
				} 
		} 
	 }





}
