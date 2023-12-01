package co.com.turbos.response;

public enum ResponseCode {

	  /**
	   *
	   */
	  OK,
	  /**
	   *
	   */
	  NOT_FOUND,
	  /**
	   *
	   */
	  NO_CONTENT,
	  /**
	   *
	   */
	  NOT_IMPLEMENTED,
	  /**
	   *
	   */
	  BAD_REQUEST,
	  /**
	   *
	   */
	  UNAUTHORIZED,
	  /**
	   *
	   */
	  FORBIDDEN,
	  /**
	   *
	   */
	  CONFLICT,
	  /**
	   *
	   */
	  APPLICATION_ERROR,
	  /**
	   *
	   */
	  PAYMENT_APROVED,
	  /**
	   *
	   */
	  PAYMENT_DECLINED,
	  /**
	   *
	   */
	  PAYMENT_REPEAT_TRANSACTION,
	  /**
	   *
	   */
	  PAYMENT_DAILY_ATTEMPTS_EXCEEDED,
	  /**
	   *
	   */
	  PAYMENT_DUPLICATED,
	  /**
	   *
	   */
	  PAYMENT_ERROR,
	  PROVIDER_ERROR,
	  /**
	   *
	   */
	  BUSSINESS_ERROR,
	  /**
	   *
	   */
	  REPEATED_ORDER,
	  /**
	   *
	   */
	  INVALID_CLIENT_ID,
	  /**
	   *
	   */
	  INVALID_CLIENT_SECRET,
	  ERROR,
	  FAIL,
	  NOT_ACCEPTABLE,
	  @Deprecated
	  URI_SYNTAX_ERROR,
	  TOKEN_EXPIRED,
	  TOKEN_REQUIRED,
	  INVALID_TOKEN,
	  CREATED;

	}