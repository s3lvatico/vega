package org.gmnz.vega.web.context;


public interface ContextProperty {

	String ORIGINAL_REQUEST = "originalRequest";

	String ORIGINAL_RESPONSE = "originalResponse";

	String SERVLET_CONTEXT = "servletContext";

	String COMMAND_NAME = "commandName";

	String STATUS_CODE = "statusCode";

	String OUTCOME = "processingOutcome";

	String ERROR_MESSAGE = "errorMessage";

	String THROWABLE_CLASS_NAME = "throwableClassName";

	String STACK_TRACE = "stackTrace";

	String CONTEXT_ROOT = "contextRoot";

	String USER_IS_LOGGED = "userIsLogged";

}
