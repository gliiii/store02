package cn.tedu.store02.service.ex;
/**
 * 地址不存在异常
 * @author glii0
 *
 */
public class AddressNotFoundException extends ServiceException{

	private static final long serialVersionUID = -2851192607578956891L;

	public AddressNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddressNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public AddressNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public AddressNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public AddressNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
