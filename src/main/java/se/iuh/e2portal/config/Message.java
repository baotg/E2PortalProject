package se.iuh.e2portal.config;

public enum Message {
	
	FILE_NOT_CORRECT("Tệp tin tải lên không hợp lệ, vui lòng kiểm tra lại!"),
	FILE_NOT_CHOSEN("Vui lòng chọn tệp tải lên!"),
	RESET_PASSWORD_SUCCESSFUL("Đặt lại mật khẩu thành công cho ID :");
	
	private final String message;
	
	Message(String message) {
		this.message=message;
	}
	public String getMessage() {
		return message;
	}
	

}
