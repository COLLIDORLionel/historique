package fr.lionelcollidor.historique.exception;

public class ApiErrorResponse {
    private String error;
    private String message;

    public ApiErrorResponse(){
        super();
    }

    public ApiErrorResponse(String error, String message) {
        this();
        this.error = error;
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
