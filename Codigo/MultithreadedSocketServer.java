/**
 * Servidor TCP multihilo.
 * 
 * Este servidor escucha conexiones de clientes en un puerto definido
 * y crea un hilo independiente por cada cliente conectado.
 * 
 * Autor: Juan Santiago Saavedra Holguín
 * Materia: Sistemas Operativos
 * Fecha: Agosto 2025
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultithreadedSocketServer {

    public static void main(String[] args) {
        int puerto = 8888; // Puerto en el que el servidor escuchará las conexiones

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor iniciado en el puerto " + puerto);

            // Bucle infinito: el servidor siempre está a la espera de clientes
            while (true) {
                Socket socketCliente = servidor.accept();
                System.out.println("Cliente conectado desde: " + socketCliente.getInetAddress());

                // Cada cliente se maneja en un hilo independiente
                new ServerClientThread(socketCliente).start();
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}

