package com.infy.isa.utitlity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.infy.isa.exception.InvalidDetails;
import com.infy.isa.exception.InvalidEmailOrDateOfBirth;
import com.infy.isa.exception.InvalidSimOrServiceNumber;
import com.infy.isa.exception.SimAlreadyActiveException;


/**
 * The Class ExceptionControllerAdvice.
 */

@ControllerAdvice
public class ExceptionControllerAdvice {
	
	@Autowired
	private MessageSource messageSource;
	/**
	 * Exception handler.
	 *
	 * @param ex the ex
	 * @return the client error information
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ClientErrorInformation exceptionHandler(Exception ex) {
		
		ClientErrorInformation errorInfo = new ClientErrorInformation();
		errorInfo.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorInfo.setMessage("An unknown error occured.");
		return errorInfo;
	}
	/*@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ARSServiceException.class)
	@ResponseBody
	public ClientErrorInformation exceptionHandler(ARSServiceException ex) {
		ex.printStackTrace();

		ClientErrorInformation errorInfo = new ClientErrorInformation();
		errorInfo.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorInfo.setMessage(messageSource.getMessage(ex.getErrorCode(),null,Locale.ENGLISH));
		return errorInfo;
	}
*/
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(InvalidSimOrServiceNumber.class)
	@ResponseBody
	public ResponseEntity<InvalidSimOrServiceNumber> exceptionHandler(InvalidSimOrServiceNumber ex) {
		return new ResponseEntity<InvalidSimOrServiceNumber>(ex,HttpStatus.NOT_FOUND);
	}
	
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(SimAlreadyActiveException.class)
	@ResponseBody
	public ResponseEntity<SimAlreadyActiveException> exceptionHandler(SimAlreadyActiveException ex) {
		return new ResponseEntity<SimAlreadyActiveException>(ex,HttpStatus.CONFLICT);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidEmailOrDateOfBirth.class)
    public ResponseEntity<InvalidEmailOrDateOfBirth> handleBindingErrors(InvalidEmailOrDateOfBirth ex) {

		return new ResponseEntity<InvalidEmailOrDateOfBirth>(ex,HttpStatus.BAD_REQUEST);
	}
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidDetails.class)
    public ResponseEntity<InvalidDetails> handleBindingErrors(InvalidDetails ex) {

		return new ResponseEntity<InvalidDetails>(ex,HttpStatus.BAD_REQUEST);
	}
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public ClientErrorInformation handleBindingErrors(Exception ex) {

		ClientErrorInformation errorInfo = new ClientErrorInformation();
		errorInfo.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorInfo.setMessage("Validation failed");
		return errorInfo;
	}
	
	
	

	
	
}
