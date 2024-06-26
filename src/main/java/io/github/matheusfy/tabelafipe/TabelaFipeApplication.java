package io.github.matheusfy.tabelafipe;

import io.github.matheusfy.tabelafipe.service.FipeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Scanner;

@SpringBootApplication
public class TabelaFipeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TabelaFipeApplication.class, args);
    }

    public void run(String [] args) {

        Scanner scanner = new Scanner(System.in);

        //TODO: Classe de aplicação irá chamar somente a classe de serviço da tabela Fipe

//        TODO: Separar o menu em outra classe.

        FipeService fipeService = new FipeService();
        String fipeAPI = "https://parallelum.com.br/fipe/api/v1/%s/marcas";

        // *********************************** Busca marcas
        System.out.println(" Escolha uma opção entre: carros, motos, caminhões ");
        String automovel = scanner.nextLine();
        fipeAPI = fipeAPI.formatted(automovel);
        fipeService.getMarcas(fipeAPI);


        // *********************************** Busca modelos
        System.out.println("Digite o codigo da marca desejada: ");
        Integer codigoModelos = scanner.nextInt();
        scanner.nextLine();

        fipeAPI = fipeAPI + "/%d/modelos".formatted(codigoModelos);
        fipeService.getModelos(fipeAPI);

        // *********************************** Busca Anos

        System.out.println("Digite o codigo do modelo desejado: ");
        Integer codigoAno = scanner.nextInt();
        scanner.nextLine();

        fipeAPI = fipeAPI + "/%d/anos".formatted(codigoAno);
        fipeService.getAnos(fipeAPI);

    }
}
