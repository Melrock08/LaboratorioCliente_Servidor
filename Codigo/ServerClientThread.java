/**
 * Clase que maneja la comunicación con un cliente.
 * 
 * Cada objeto de esta clase representa un hilo que atiende a un cliente
 * específico. Se encarga de recibir mensajes y responderlos.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerClientThread extends Thread {
    private Socket socketCliente;

    // Constructor: recibe el socket del cliente
    public ServerClientThread(Socket socketCliente) {
        this.socketCliente = socketCliente;
    }

    @Override
    public void run() {
        try (
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            PrintWriter salida = new PrintWriter(socketCliente.getOutputStream(), true)
        ) {
            String mensajeCliente;
            // Ciclo de comunicación con el cliente
            while ((mensajeCliente = entrada.readLine()) != null) {
                System.out.println("Mensaje recibido: " + mensajeCliente);

                // Respuesta del servidor
                salida.println("Servidor recibió: " + mensajeCliente);

                // Si el cliente envía "salir", se cierra la conexión
                if ("salir".equalsIgnoreCase(mensajeCliente)) {
                    System.out.println("Cliente desconectado.");
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error en la comunicación con el cliente: " + e.getMessage());
        } finally {
            try {
                socketCliente.close();
            } catch (IOException e) {
                System.err.println("No se pudo cerrar el socket del cliente.");
            }
        }
    }
}
