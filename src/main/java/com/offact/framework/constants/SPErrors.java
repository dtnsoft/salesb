package com.offact.framework.constants;

public enum SPErrors {
	
	// Parameter Check Error (E0001 ~ E0099)
	PARAMETER_ID_EMPTY("E0001", "ID를 입력하세요"),
	PARAMETER_PASS_EMPTY("E0002", "Password를 입력하세요"),
	PARAMETER_PASS_INVALID("E003", "Password는 6자이상 18자 이하입니다."),
	
	// Internal Error (E0100 ~ E0300)
	USER_NOT_FOUND("E0100", "유저가 존재하지 않습니다."),
	UNKNOWN_ERROR("E0300", "내부에러가 발생했습니다. 에러상세는 로그파일을 참조하세요."),
	
	EMPTY_STR_FOR_ENC("E0100", "암호화에 필요한 문자열이 없습니다"),
	CRYPTO_ERROR("E0101", "암호화 수행 중 오류가 발생했습니다"),
	NO_WRITE_AUTH("E0102", "게시물 수정은 작성자 본인만 가능합니다"),
	NO_DELETE_AUTH("E0103", "게시물 삭제는 작성자 본인만 가능합니다"),
	
	EMPTY_BOARD_ID("E0104", "게시판 아이디가 필요합니다."),
	EMPTY_TITLE("E0105", "제목이 필요합니다."),
	EMPTY_CONTENT("E0106", "내용이 필요합니다."),
	
	NO_CLUB_MEMBER("E0107", "클럽에 가입한 회원이 아닙니다"),
	NO_CLUB_BOARD_READ_AUTH("E0108", "회원님은 해당 게시판에 읽기 권한이 없습니다"),
	NO_CLUB_BOARD_WRITE_AUTH("E0109", "회원님은 해당 게시판에 쓰기 권한이 없습니다"),
	NO_CLUB_BOARD_RP_WRITE_AUTH("E0110", "회원님은 해당 게시판에 덧글 쓰기 권한이 없습니다"),
	NEED_LOGIN("E0111", "로그인이 필요합니다"),
	
	NOT_FOUND_CLUB("E0200", "존재하지 않는 클럽주소입니다.");
	// System Error (E9000 ~ E9999)
	;
	
	private SPErrors(String code, String description) {
		this.code = code;
		this.description = description;
	}
	
	private final String code;
	private final String description;
	
	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public String toString() {
		return code + ": " + description;
	}
}
