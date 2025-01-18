package org.iesalandalus.programacion.matriculacion.negocio;

import java.time.LocalDate;
import org.iesalandalus.programacion.matriculacion.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;

import javax.naming.OperationNotSupportedException;
import java.util.Arrays;

public class Matriculas {

    private Matricula[] coleccionMatriculas;
    private int capacidad;
    private int tamano;

    public Matriculas(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
        this.tamano = 0;
        this.coleccionMatriculas = new Matricula[capacidad];
    }


    public Matricula[] get() throws OperationNotSupportedException {
        return copiaProfundaMatriculas();
    }


    private Matricula[] copiaProfundaMatriculas() throws OperationNotSupportedException {
        Matricula[] copia = new Matricula[tamano];

        for (int i = 0; i < tamano; i++) {
            copia[i] = new Matricula(coleccionMatriculas[i]);
        }
            return copia;

    }

    public int getTamano() {
        return tamano;
    }


    public int getCapacidad() {
        return capacidad;
    }


    public void insertar(Matricula matricula)throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede insertar una matrícula nula.");
        }
        if (buscar(matricula) != null) {
            throw new OperationNotSupportedException("ERROR: Ya existe una matrícula con ese identificador.");
        }

        if (tamano >= capacidad) {
            throw new OperationNotSupportedException("ERROR: No se aceptan más matrículas.");
        }

        coleccionMatriculas[tamano++] = new Matricula(matricula);
    }


    public Matricula buscar(Matricula matricula) {
        if (matricula == null) {
            throw new NullPointerException("La matricula no puede ser nula.");
        }

        int indice = buscarIndice(matricula);
        if (indice != -1) {
            return coleccionMatriculas[indice];
        }
        return null;
    }


    public void borrar(Matricula matricula) throws OperationNotSupportedException {
        if (matricula == null) {
            throw new NullPointerException("ERROR: No se puede borrar una matrícula nula.");
        }

        int indice = buscarIndice(matricula);
        if (indice != -1) {
            desplazarUnaPosicionHaciaIzquierda(indice);
            tamano--;
        } else {
            throw new OperationNotSupportedException("ERROR: No existe ninguna matrícula como la indicada.");
        }
    }


    private int buscarIndice(Matricula matricula) {
        for (int i = 0; i < tamano; i++) {
            if (coleccionMatriculas[i].equals(matricula)) {
                return i;
            }
        }
        return -1;
    }


    private boolean tamanoSuperado(int indice) {
        return indice >= tamano;
    }


    private boolean capacidadSuperada(int indice) {
        return indice >= capacidad;
    }


    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; i < tamano - 1; i++) {
            coleccionMatriculas[i] = coleccionMatriculas[i + 1];
        }
        coleccionMatriculas[tamano - 1] = null;
    }


    public Matricula[] get(Alumno alumno) {
        int contador = 0;
        for (int i = 0; i < tamano; i++) {
            if (coleccionMatriculas[i].getAlumno().equals(alumno)) {
                contador++;
            }
        }

        // Crear el arreglo resultante del tamaño exacto
        Matricula[] result = new Matricula[contador];
        int indice = 0;

        // Segunda pasada: llenar el arreglo con las coincidencias
        for (int i = 0; i < tamano; i++) {
            if (coleccionMatriculas[i].getAlumno().equals(alumno)) {
                result[indice++] = coleccionMatriculas[i];
            }
        }

        return result;
    }


    public Matricula[] get(String cursoAcademico) throws OperationNotSupportedException {

        int contador = 0;
        for (int i = 0; i < tamano; i++) {
            if (coleccionMatriculas[i].getCursoAcademico().equals(cursoAcademico)) {
                contador++;
            }
        }

        Matricula[] result = new Matricula[contador];
        int indice = 0;
        for (int i = 0; i < tamano; i++) {
            if (coleccionMatriculas[i].equals(cursoAcademico)) {
                result[indice++] = coleccionMatriculas[i];
            }

        }
        return result;
    }
    public Matricula[] get(CicloFormativo cicloFormativo)  {
        int contador = 0;
        for (int i = 0; i < tamano; i++) {
            if (coleccionMatriculas[i].equals(cicloFormativo)) {
                contador++;
            }
        }

        Matricula[] result = new Matricula[contador];
        int indice = 0;

        for (int i = 0; i < tamano; i++) {
            if (coleccionMatriculas[i].equals(cicloFormativo)) {
                result[indice++] = coleccionMatriculas[i];
            }
        }

        return result;
    }
}


