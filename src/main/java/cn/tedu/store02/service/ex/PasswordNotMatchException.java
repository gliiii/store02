package cn.tedu.store02.service.ex;

/**
 * 密码不匹配异常
 */
public class PasswordNotMatchException extends ServiceException {

	private static final long serialVersionUID = -3401786951348614957L;

	public PasswordNotMatchException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PasswordNotMatchException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public PasswordNotMatchException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public PasswordNotMatchException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public PasswordNotMatchException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
