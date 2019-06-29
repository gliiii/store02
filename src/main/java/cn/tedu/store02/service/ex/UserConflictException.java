package cn.tedu.store02.service.ex;
/**
 * 用户冲突异常
 * @author glii0
 *
 */
public class UserConflictException extends ServiceException{


	private static final long serialVersionUID = 8576337277167463874L;

	public UserConflictException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserConflictException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public UserConflictException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserConflictException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserConflictException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
