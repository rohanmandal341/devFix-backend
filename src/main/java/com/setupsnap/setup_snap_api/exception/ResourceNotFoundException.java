package com.setupsnap.setup_snap_api.exception;

public class ResourceNotFoundException extends  RuntimeException{

    public ResourceNotFoundException(String message){
    super(message);
    }
}
