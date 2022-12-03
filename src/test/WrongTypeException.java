package test;


public class WrongTypeException extends Exception {

	WrongTypeException(){
		super();

	}
	
	
	WrongTypeException(String errorMessage){
		super(errorMessage);
	}
}
