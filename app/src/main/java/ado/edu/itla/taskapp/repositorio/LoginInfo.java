package ado.edu.itla.taskapp.repositorio;

import ado.edu.itla.taskapp.entidad.Usuario;

public class LoginInfo {
    private static final LoginInfo ourInstance = new LoginInfo();
    public Usuario usuario;


  public  static LoginInfo getInstance() {
        return ourInstance;
    }

    private LoginInfo() {
    }
}
