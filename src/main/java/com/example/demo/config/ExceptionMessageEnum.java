/*******************************************************************************
 * Copyright (C) 2020 Thai Phung
 * 
 * It is Thai Phung's demo to Karros.
 ******************************************************************************/

package com.example.demo.config;

import java.util.Arrays;
import java.util.Optional;

/**
 * Created By			Created Date			Version			Reason
 * Thai Phung			Oct 10, 2020				1.0				Initialize
 */

public enum ExceptionMessageEnum {
	ERROR_CODE_60000(60000, "System have error. Please contact with admin."),
	ERROR_CODE_60001(60001,
			"EX_60001 - Hibernate Config: Entity is read-only. Can't insert, update or delete on this."),
	ERROR_CODE_60002(60002, "Database have issue. Please contact with admin."),
	ERROR_CODE_60003(60003, "Username don't found in system. Please contact with admin."),
	ERROR_CODE_60101(60101, "Can't add this data. Please contact with admin."),
	ERROR_CODE_60102(60102, "Can't edit this data. Please contact with admin."),
	ERROR_CODE_60103(60103, "Can't delete this data. Please contact with admin."),
	ERROR_CODE_60404(60404, "System can't found the data with your parameters. Please try with other parameters."),
	ERROR_INVALID_EMAIL_FORMAT(60104, "Email is invalid. Please follow correct format for email"),
	ERROR_INVALID_FULLNAME(60105, "Name is invalid. Please enter name is alphabet only and less than 100 character "),
	ERROR_INVALID_GPS_FORMAT(60106, "GPS format is invalid. Please try with other GPS files.");


	private int idCode;
	private String messageValue;

	/**
	 * @return the idCode
	 */
	public int getIdCode() {
		return idCode;
	}

	/**
	 * @return the messageValue
	 */
	public String getMessageValue() {
		return messageValue;
	}

	private ExceptionMessageEnum(Integer idCode, String messageValue) {
		this.idCode = idCode;
		this.messageValue = messageValue;
	}

	/**
	 * Find the DatabaseExceptionEnum value by IdCode
	 * 
	 * @param id
	 * @return DatabaseExceptionEnum value
	 */
	public static ExceptionMessageEnum findByIdCode(int id) {
		Optional<ExceptionMessageEnum> objEnum = Arrays.stream(ExceptionMessageEnum.values())
				.filter(t -> t.getIdCode() == id).findFirst();
		return objEnum.orElse(ERROR_CODE_60000);
	}

	public static ExceptionMessageEnum findByIdCode_old(int id) {
		Optional<ExceptionMessageEnum> oEnum = Optional.empty();
		for (ExceptionMessageEnum objEnum : ExceptionMessageEnum.values()) {
			if (objEnum.getIdCode() == id) {
				oEnum = Optional.ofNullable(objEnum);
				break;
			}
		}
		return oEnum.orElse(ERROR_CODE_60000);
	}
}
