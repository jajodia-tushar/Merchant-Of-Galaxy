package com.tavisca.workshops;

import java.security.PublicKey;

public class NoSuchWordException extends Exception {

    NoSuchWordException(String message){
        super(message);
    }
}
