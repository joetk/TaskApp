package ado.edu.itla.taskapp.repositorio.db;

public class EstructuraDb
{
    public static final String TABLA_CATEGORIA ="CREATE TABLE categoria ( id INTEGER PRIMARY KEY AUTOINCREMENT, 'nombre' TEXT )";
    public static final String TABLA_USUARIO = "CREATE TABLE usuario ( `Id` INTEGER PRIMARY KEY AUTOINCREMENT, `nombre` VARCHAR NOT NULL, `contrasena` TEXT, `email` TEXT, `tipoUsuario` TEXT )";
    public static final String TABLA_USUARIO_CATEGORIA = "CREATE TABLE usuario_categoria ( usuario_id INTEGER NOT NULL, categoria_id INTEGER NOT NULL )";
    public static final String TABLA_TAREA = "CREATE TABLE tarea ( id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, descripcion TEXT, fecha NUMERIC NOT NULL, fecha_completado NUMERIC, estado TEXT NOT NULL DEFAULT 'PENDIENTE', usuario_creador_id INTEGER NOT NULL, usuario_asignado_id INTEGER NOT NULL, categoria_id INTEGER NOT NULL )";
}
