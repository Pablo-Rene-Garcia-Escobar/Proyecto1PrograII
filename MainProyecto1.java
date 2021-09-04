package proyecto1prograii;

import java.util.Scanner;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.PDPage;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDPageContentStream;



/**
 * Esta es la clase main que compila el codigo
 * @author Albert Castañeda
 * @author Pablo Garcia
 * @version 1.0
 */
public class MainProyecto1 {

    private static int contMoto = 0;
    private static int contCarro = 0;
    private static int contCamion = 0;
    private static int noEstacionamientos = 0;
    private static int noEstacionamientosC = 0;
    private static int noEstacionamientosCa = 0;

    /**
     * En este metodo se compila el codigo
     * @throws En este metodo puede saltar una Exception de tipo IOException
     */
    public static void main(String[] args) throws IOException {

        int opcion = 0, opcion2 = 0, opcion3 = 0, moto = 0, carro = 0, camion = 0, parqueo = 0;
        double tarifa = 0, segundos = 0, monto = 0, montoTotal = 0, descuento = 0;
        String placa, fechaYHora;

        Scanner tec = new Scanner(System.in);

        System.out.println("--- Ingrese la cantidad de estacionamientos que hay para las motos ---");
        noEstacionamientos = tec.nextInt();

        System.out.println("--- Ingrese la cantidad de estacionamientos que hay para los carros ---");
        noEstacionamientosC = tec.nextInt();

        System.out.println("--- Ingrese la cantidad de estacionamientos que hay para los camiones ---");
        noEstacionamientosCa = tec.nextInt();

        ArrayVehiculo av = new ArrayVehiculo() {};
        TicketMoto tm[] = new TicketMoto[noEstacionamientos];
        TicketCarro tmc[] = new TicketCarro[noEstacionamientosC];
        TicketCamion tmca[] = new TicketCamion[noEstacionamientosCa];

        do {

            System.out.println("----- MENU PRINCIPAL -----"
                    + "\n Escoja  el tipo de vehiculo que desea gestionar"
                    + "\n 1.Moto"
                    + "\n 2.Carro"
                    + "\n 3.Camion"
                    + "\n 4.Salir ");
            opcion = tec.nextInt();

            switch (opcion) {

                case 1:

                    System.out.println("----- MENU MOTO -----"
                            + "\n Escoja  la accion que desea gestionar"
                            + "\n 1.Ingresar Moto"
                            + "\n 2.Retirar Moto"
                            + "\n 3.Salir ");

                    opcion2 = tec.nextInt();

                    switch (opcion2) {

                        case 1:

                            if (contMoto < noEstacionamientos) {

                                for (contMoto = contMoto; contMoto < noEstacionamientos; contMoto++) {
                                    tec.nextLine();
                                    System.out.println("----- Por favor ingrese su No. de placa -----");
                                    placa = tec.nextLine();

                                    System.out.println("-------- Por favor ingrese su fecha y hora de entrada");
                                    fechaYHora = tec.nextLine();

                                    System.out.println("---- Por favor ingrese el numero de segundos que estara en el estacionamiento ----");
                                    segundos = tec.nextInt();

                                    System.out.println("---- Por favor ingrese la tarifa que pagara por segundo ----");
                                    tarifa = tec.nextDouble();

                                    monto = tarifa * segundos;

                                    TicketMoto tim = new TicketMoto(contMoto + 1, placa, fechaYHora, monto, TipoVehiculo.MOTO);
                                    tm[contMoto] = tim;
                                    av.addVehiculo(tim);
                                    contMoto++;

                                    
                                    
                                    PDDocument documento = new PDDocument();
                                    PDPage pagina = new PDPage(PDRectangle.A6);
                                    documento.addPage(pagina);
                                    PDPageContentStream contenido = new PDPageContentStream(documento, pagina);

                                    String cont = Integer.toString(contMoto);

                                    for (int i = 1; i <= 5; i++) {
                                        contenido.beginText();
                                        contenido.setFont(PDType1Font.TIMES_BOLD, 12);
                                        if (i == 1) {
                                            contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                            contenido.showText("--------------------------Ticket--------------------------");
                                        }
                                        if (i == 2 && cont.length() == 1) {
                                            contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                            contenido.showText("Numero de estacionamiento: 00" + tim.getNoEstacionamiento());
                                        }
                                        if (i == 2 && cont.length() == 2) {
                                            contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                            contenido.showText("Numero de estacionamiento: 0" + tim.getNoEstacionamiento());
                                        }
                                        if (i == 2 && cont.length() == 3) {
                                            contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                            contenido.showText("Numero de estacionamiento: " + tim.getNoEstacionamiento());
                                        }
                                        if (i == 3) {
                                            contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                            contenido.showText("Placa del vehiculo: " + placa);
                                        }
                                        if (i == 4) {
                                            contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                            contenido.showText("Fecha y Hora de entrada: " + fechaYHora);
                                        }
                                        if (i == 5) {
                                            contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                            contenido.showText("Precio por tarifa: Q" + monto + ".00");
                                        }

                                        contenido.endText();
                                    }
                                    contenido.close();

                                    documento.save("C:/Facturas/ticket.pdf");

                                    System.out.println("Ticket creado");

                                    break;
                                }

                            } else {
                                System.out.println("-----Ya no hay estacionamientos disponibles para este vehiculo-----");
                            }

                            break;

                        case 2:
                            tec.nextLine();
                            int contador = 0;
                            String nit = "";

                            TicketMoto ticMo = new TicketMoto();
                            System.out.println("--- Por favor ingrese su numero de placa ---");
                            placa = tec.nextLine();
                            for (int x = 0; x < noEstacionamientos; x++) {
                                if (placa.equals(tm[x].getPlaca())) {
                                    descuento = tm[x].getPrecioTarifa() * 0.10;
                                    montoTotal = ticMo.monto(tm[x].getPrecioTarifa());

                                    try {
                                        System.out.println("Ingrese su nit");
                                        nit = tec.nextLine();

                                        PDDocument documento = new PDDocument();
                                        PDPage pagina = new PDPage(PDRectangle.A6);
                                        documento.addPage(pagina);
                                        PDPageContentStream contenido = new PDPageContentStream(documento, pagina);

                                        for (int i = 1; i <= 6; i++) {
                                            contenido.beginText();
                                            contenido.setFont(PDType1Font.TIMES_BOLD, 12);
                                            if (i == 1) {
                                                contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                                contenido.showText("--------------------------Factura--------------------------");
                                            }
                                            if (i == 2) {
                                                contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                                contenido.showText("NIT de factura:" + nit);
                                            }
                                            if (i == 3) {
                                                contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                                contenido.showText("Precio por tarifa: " + tm[x].getPrecioTarifa());
                                            }
                                            if (i == 4) {
                                                contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                                contenido.showText("Descuento: " + descuento);
                                            }
                                            if (i == 5) {
                                                contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                                contenido.showText("Recargo: 0");
                                            }
                                            if (i == 6) {
                                                contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                                contenido.showText("Monto a pagar: " + montoTotal);
                                            }

                                            contenido.endText();
                                        }
                                        contenido.close();

                                        documento.save("C:/Facturas/factura.pdf");

                                        System.out.println("Factura creada");

                                    } catch (Exception e) {
                                        System.out.println(e);
                                    }
                                    contador++;
                                    break;
                                }

                            }

                            if (contador == 0) {
                                System.out.println("--- En el estacionamiento no hay ninguna moto con la placa que ingreso---");
                            }

                            break;

                        case 3:
                            System.out.println("----- GRACIAS -----");
                            break;
                    }
                    break;

                case 2:

                    System.out.println("----- MENU CARRO -----"
                            + "\n Escoja  la accion que desea gestionar"
                            + "\n 1.Ingresar Carro"
                            + "\n 2.Retirar Carro"
                            + "\n 3.Salir ");

                    opcion2 = tec.nextInt();
                    tec.nextLine();

                    switch (opcion2) {

                        case 1:
                            if (contCarro < noEstacionamientosC) {

                                for (contCarro = contCarro; contCarro < noEstacionamientosC; contCarro++) {
                                    System.out.println("----- Por favor ingrese su No. de placa -----");
                                    placa = tec.nextLine();

                                    System.out.println("-------- Por favor ingrese su fecha y hora de entrada");
                                    fechaYHora = tec.nextLine();

                                    System.out.println("---- Por favor ingrese el numero de segundos que estara en el estacionamiento ----");
                                    segundos = tec.nextDouble();

                                    System.out.println("---- Por favor ingrese la tarifa que pagara por segundo ----");
                                    tarifa = tec.nextDouble();

                                    monto = segundos * tarifa;
                                    
                                    TicketCarro tic = new TicketCarro(contCarro + 1, placa, fechaYHora, monto, TipoVehiculo.CARRO);
                                    tmc[contCarro] = tic;
                                    av.addVehiculo(tic);
                                    contCarro++;
                                    
                                    PDDocument documento = new PDDocument();
                                    PDPage pagina = new PDPage(PDRectangle.A6);
                                    documento.addPage(pagina);
                                    PDPageContentStream contenido = new PDPageContentStream(documento, pagina);

                                    String cont = Integer.toString(contMoto);

                                    for (int i = 1; i <= 5; i++) {
                                        contenido.beginText();
                                        contenido.setFont(PDType1Font.TIMES_BOLD, 12);
                                        if (i == 1) {
                                            contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                            contenido.showText("--------------------------Ticket--------------------------");
                                        }
                                        if (i == 2 && cont.length() == 1) {
                                            contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                            contenido.showText("Numero de estacionamiento: 00" + tic.getNoEstacionamiento());
                                        }
                                        if (i == 2 && cont.length() == 2) {
                                            contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                            contenido.showText("Numero de estacionamiento: 0" + tic.getNoEstacionamiento());
                                        }
                                        if (i == 2 && cont.length() == 3) {
                                            contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                            contenido.showText("Numero de estacionamiento: " + tic.getNoEstacionamiento());
                                        }
                                        if (i == 3) {
                                            contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                            contenido.showText("Placa del vehiculo: " + placa);
                                        }
                                        if (i == 4) {
                                            contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                            contenido.showText("Fecha y Hora de entrada: " + fechaYHora);
                                        }
                                        if (i == 5) {
                                            contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                            contenido.showText("Precio por tarifa: Q" + monto + "0");
                                        }

                                        contenido.endText();
                                    }
                                    contenido.close();

                                    documento.save("C:/Facturas/ticket.pdf");

                                    System.out.println("Ticket creado");
                                    
                                    break;
                                }

                            } else {
                                System.out.println("-----Ya no hay estacionamientos disponibles para este vehiculo-----");
                            }
                            break;

                        case 2:

                            System.out.println("--- Por favor ingrese su numero de placa ---");
                            placa = tec.nextLine();

                            int contador = 0;
                            String nit = "";

                            TicketCarro tca = new TicketCarro();

                            for (int x = 0; x < noEstacionamientosC; x++) {
                                if (placa.equals(tmc[x].getPlaca())) {
                                    montoTotal = tca.monto(tmc[x].getPrecioTarifa());

                                    try {
                                        System.out.println("Ingrese su nit");
                                        nit = tec.nextLine();

                                        PDDocument documento = new PDDocument();
                                        PDPage pagina = new PDPage(PDRectangle.A6);
                                        documento.addPage(pagina);
                                        PDPageContentStream contenido = new PDPageContentStream(documento, pagina);

                                        for (int i = 1; i <= 6; i++) {
                                            contenido.beginText();
                                            contenido.setFont(PDType1Font.TIMES_BOLD, 12);
                                            if (i == 1) {
                                                contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                                contenido.showText("--------------------------Factura--------------------------");
                                            }
                                            if (i == 2) {
                                                contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                                contenido.showText("NIT de factura:" + nit);
                                            }
                                            if (i == 3) {
                                                contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                                contenido.showText("Precio por tarifa: " + tmc[x].getPrecioTarifa());
                                            }
                                            if (i == 4) {
                                                contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                                contenido.showText("Descuento: 0");
                                            }
                                            if (i == 5) {
                                                contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                                contenido.showText("Recargo: 0");
                                            }
                                            if (i == 6) {
                                                contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                                contenido.showText("Monto a pagar: " + montoTotal);
                                            }

                                            contenido.endText();
                                        }
                                        contenido.close();

                                        documento.save("C:/Facturas/factura.pdf");

                                        System.out.println("Factura creada");

                                    } catch (Exception e) {
                                        System.out.println(e);
                                    }
                                    contador++;
                                    break;
                                }
                            }

                            if (contador == 0) {
                                System.out.println("--- En el estacionamiento no hay ningun carro con la placa que ingreso---");
                            }
                            break;

                        case 3:
                            System.out.println("----- GRACIAS -----");
                            break;
                    }

                    break;

                case 3:

                    System.out.println("----- MENU CAMION -----"
                            + "\n Escoja  la accion que desea gestionar"
                            + "\n 1.Ingresar Camion"
                            + "\n 2.Retirar Camion"
                            + "\n 3.Salir ");

                    opcion2 = tec.nextInt();
                    tec.nextLine();

                    switch (opcion2) {

                        case 1:

                            System.out.println("Solo puede estar un periodo de 15 segundos dentro del estacionamiento con un costo de Q10.00");
                            System.out.println("Se le cobrara un recargo de Q5.00 por cada periodo adicional que se encuentre dentro del estacionamiento");

                            if (contCamion < noEstacionamientosCa) {

                                for (contCamion = contCamion; contCamion < noEstacionamientosCa; contCamion++) {

                                    System.out.println("----- Por favor ingrese su No. de placa -----");
                                    placa = tec.nextLine();

                                    System.out.println("-------- Por favor ingrese su fecha y hora de entrada");
                                    fechaYHora = tec.nextLine();

                                    TicketCamion tica = new TicketCamion(contCamion + 1, placa, fechaYHora, 10, TipoVehiculo.CAMION);
                                    tmca[contCamion] = tica;
                                    av.addVehiculo(tica);
                                    contCamion++;
                                    
                                    PDDocument documento = new PDDocument();
                                    PDPage pagina = new PDPage(PDRectangle.A6);
                                    documento.addPage(pagina);
                                    PDPageContentStream contenido = new PDPageContentStream(documento, pagina);

                                    String cont = Integer.toString(contMoto);

                                    for (int i = 1; i <= 5; i++) {
                                        contenido.beginText();
                                        contenido.setFont(PDType1Font.TIMES_BOLD, 12);
                                        if (i == 1) {
                                            contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                            contenido.showText("--------------------------Ticket--------------------------");
                                        }
                                        if (i == 2 && cont.length() == 1) {
                                            contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                            contenido.showText("Numero de estacionamiento: 00" + tica.getNoEstacionamiento());
                                        }
                                        if (i == 2 && cont.length() == 2) {
                                            contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                            contenido.showText("Numero de estacionamiento: 0" + tica.getNoEstacionamiento());
                                        }
                                        if (i == 2 && cont.length() == 3) {
                                            contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                            contenido.showText("Numero de estacionamiento: " + tica.getNoEstacionamiento());
                                        }
                                        if (i == 3) {
                                            contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                            contenido.showText("Placa del vehiculo: " + placa);
                                        }
                                        if (i == 4) {
                                            contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                            contenido.showText("Fecha y Hora de entrada: " + fechaYHora);
                                        }
                                        if (i == 5) {
                                            contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                            contenido.showText("Precio por tarifa: Q10.00");
                                        }

                                        contenido.endText();
                                    }
                                    contenido.close();

                                    documento.save("C:/Facturas/ticket.pdf");

                                    System.out.println("Ticket creado");
                                    
                                    break;

                                }

                            }
                            break;

                        case 2:

                            System.out.println("--- Por favor ingrese su numero de placa ---");
                            placa = tec.nextLine();

                            int contador = 0;
                            String nit = "";

                            TicketCamion tcam = new TicketCamion();

                            for (int x = 0; x < noEstacionamientosCa; x++) {
                                if (placa.equals(tmca[x].getPlaca())) {
                                    double periodos = 0;
                                    System.out.println("--- Por favor ingrese la cantidad de periodos que estuvo en el estacionamiento ---");
                                    periodos = tec.nextDouble();
                                    double recargo = (periodos - 1) * 5;
                                    montoTotal = tcam.monto(periodos);

                                    try {
                                        tec.nextLine();
                                        System.out.println("Ingrese su nit");
                                        nit = tec.nextLine();

                                        PDDocument documento = new PDDocument();
                                        PDPage pagina = new PDPage(PDRectangle.A6);
                                        documento.addPage(pagina);
                                        PDPageContentStream contenido = new PDPageContentStream(documento, pagina);

                                        for (int i = 1; i <= 6; i++) {
                                            contenido.beginText();
                                            contenido.setFont(PDType1Font.TIMES_BOLD, 12);
                                            if (i == 1) {
                                                contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                                contenido.showText("--------------------------Factura--------------------------");
                                            }
                                            if (i == 2) {
                                                contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                                contenido.showText("NIT de factura:" + nit);
                                            }
                                            if (i == 3) {
                                                contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                                contenido.showText("Precio por tarifa: " + tmca[x].getPrecioTarifa());
                                            }
                                            if (i == 4) {
                                                contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                                contenido.showText("Descuento: 0");
                                            }
                                            if (i == 5) {
                                                contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                                contenido.showText("Recargo: " + recargo);
                                            }
                                            if (i == 6) {
                                                contenido.newLineAtOffset(20, pagina.getMediaBox().getHeight() - (52 * i));
                                                contenido.showText("Monto a pagar: " + montoTotal);
                                            }

                                            contenido.endText();
                                        }
                                        contenido.close();

                                        documento.save("C:/Facturas/factura.pdf");

                                        System.out.println("Factura creada");

                                    } catch (Exception e) {
                                        System.out.println(e);
                                    }
                                    contador++;
                                    break;
                                }
                            }

                            if (contador == 0) {
                                System.out.println("--- En el estacionamiento no hay ningun camion con la placa que ingreso---");
                            }
                            break;

                        case 3:
                            System.out.println("----- GRACIAS -----");
                            break;
                    }

                    break;

                case 4:
                    System.out.println("----- GRACIAS -----");
                    break;

            }

        } while (opcion != 4);
    }

}
