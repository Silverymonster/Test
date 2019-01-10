package myx.Service;
import java.util.Map;
import org.springframework.beans.factory.BeanFactoryAware;
public interface service extends BeanFactoryAware {
	//无参GET请求
	public String doGet(String url) throws Exception;
	//有参GET请求
	public String doGet(String url, Map<String, String> params) throws Exception;
	//无参POST请求
	public String doPost(String url) throws Exception;
	//有参POST请求
	public String doPost(String url, Map<String, String> params) throws Exception;
	//有参POST的JSON请求
	public String doPostJson(String url, String json) throws Exception;
	
}
