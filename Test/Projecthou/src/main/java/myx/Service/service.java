package myx.Service;
import java.util.Map;
import org.springframework.beans.factory.BeanFactoryAware;
public interface service extends BeanFactoryAware {
	//�޲�GET����
	public String doGet(String url) throws Exception;
	//�в�GET����
	public String doGet(String url, Map<String, String> params) throws Exception;
	//�޲�POST����
	public String doPost(String url) throws Exception;
	//�в�POST����
	public String doPost(String url, Map<String, String> params) throws Exception;
	//�в�POST��JSON����
	public String doPostJson(String url, String json) throws Exception;
	
}
