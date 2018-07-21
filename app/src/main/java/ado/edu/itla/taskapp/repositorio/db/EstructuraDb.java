package ado.edu.itla.taskapp.repositorio.db;

public class EstructuraDb
{
    public static final String TABLA_CATEGORIA ="CREATE TABLE categoria ( id INTEGER PRIMARY KEY AUTOINCREMENT, descripcion TEXT )";
    public static final String TABLA_USUARIO = "CREATE TABLE usuario ( `Id` INTEGER PRIMARY KEY AUTOINCREMENT, `nombre` VARCHAR NOT NULL, `contrasena` TEXT, `email` TEXT, `tipoUsuario` TEXT )";
    public static final String TABLA_USUARIO_CATEGORIA = "CREATE TABLE usuario_categoria ( usuario_id INTEGER NOT NULL, categoria_id INTEGER NOT NULL )";

}
