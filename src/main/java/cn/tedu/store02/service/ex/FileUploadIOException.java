package cn.tedu.store02.service.ex;
/**
 * 文件上传读写异常
 * @author glii0
 *
 */
public class FileUploadIOException extends FileUploadException{

	private static final long serialVersionUID = -1237358556524106745L;

	public FileUploadIOException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileUploadIOException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public FileUploadIOException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public FileUploadIOException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public FileUploadIOException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
