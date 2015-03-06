package cn.ttsales.work.service.msg;

public interface EntStoreResponseService {
	public void sendSignInQCCode(String agentId, String userId,
			String serverName, String contextPath, String realPath);
}
