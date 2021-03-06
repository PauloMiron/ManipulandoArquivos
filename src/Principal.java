

import Entities.Produto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Principal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Produto> produtos = new ArrayList<>();

        System.out.println("Informe o caminho do documento: ");
        String caminho = scanner.nextLine();

        File caminhoInicial = new File(caminho);

        String caminhoCriado = caminhoInicial.getParent();

        boolean sucesso = new File(caminhoCriado + "\\out").mkdir();

        String caminhoFinal = caminhoCriado + "\\out\\summary.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String leitor = br.readLine();

            while (leitor != null) {

                String[] vect = leitor.split(";");
                String nomeProduto = vect[0];
                Double valorProduto = Double.parseDouble(vect[1]);
                int quantidadeProduto = Integer.parseInt(vect[2]);

                produtos.add(new Produto(nomeProduto, valorProduto, quantidadeProduto));

                leitor = br.readLine();
            } try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoFinal))) {
                for (Produto prod : produtos) {
                    bw.write(prod.getNomeProduto() + "  " + prod.getValorProduto() * prod.getQuantidadeProduto());
                    bw.newLine();
                }

                System.out.println(caminhoFinal + " CRIADO");

        } catch (IOException e){
            System.out.println(e.getMessage());
        }

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
