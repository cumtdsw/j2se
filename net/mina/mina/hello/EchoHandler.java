package mina.hello;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class EchoHandler extends IoHandlerAdapter {

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		System.out.printf("session[%d] 有错误发生：%s\n", session.getId(), cause);
		session.close(true);
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		System.out.printf("session[%d] ->%s\n", session.getId(), message);
		session.write(message);
		// session.close(true); // 关闭掉
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.printf("session[%d] 已关闭\n", session.getId());
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		System.out.printf("session[%d] 被创建\n", session.getId());
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		System.out.printf("session[%d] 处于空闲\n", session.getId());
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.printf("session[%d] 被打开\n", session.getId());
	}
}
