package com.example.javaee.exame.example.util;

public enum ActionEnum {

  POST("Creazione studente %s"),
  PUT("Aggiornamento studente %s"),
  DELETE("Rimozione studente con id %s");

  ActionEnum(String description) {
    this.description = description;
  }

  private final String description;

  public String getDescription() {
    return description;
  }
}
