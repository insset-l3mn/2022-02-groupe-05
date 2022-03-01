/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projet.entities;

/**
 *
 * @author valen
 */
public class Message {
    
    String type, message;
    
    public Message(String TYPE, String MESSAGE){
        this.type = TYPE;
        this.message = MESSAGE;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
