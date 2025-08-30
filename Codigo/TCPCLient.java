/**
 * Cliente TCP que se conecta al servidor.
 * 
 * Este cliente se conecta al servidor en una dirección IP y puerto dados.
 * Permite al usuario enviar mensajes y recibir respuestas.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) {
        String servidor = "localhost"; // Dirección del servidor (cambiar por IP si es remoto)
        int puerto = 8888; // Puerto donde escucha el servidor

        try (
            Socket socket = new Socket(servidor, puerto);
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)
        ) {
            System.out.println("Conectado al servidor en " + servidor + ":" + puerto);
            String mensaje;

            // Ciclo de envío y recepción de mensajes
            while (true) {
                System.out.print("Escribe un mensaje (o 'salir' para terminar): ");
                mensaje = teclado.readLine();

                salida.println(mensaje); // Enviar mensaje al servidor
                if ("salir".equalsIgnoreCase(mensaje)) {
                    break; // Se termina la comunicación
                }

                // Leer la respuesta del servidor
                String respuesta = entrada.readLine();
                System.out.println("Respuesta del servidor: " + respuesta);
            }
        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}
