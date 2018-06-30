package loja;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

public class Loja {
    
    public static ArrayList<Produto> Produtos = new ArrayList<Produto>();
    public static ArrayList<Produto> Carrinho = new ArrayList<Produto>();
	private static Scanner entrada;
	private static Scanner entrada2;
    
    
    public static int buscaProduto(ArrayList<Produto> Lista, int idProduto){
        for (Produto produto : Lista){
            if (produto.getId() == idProduto){
                return Lista.indexOf(produto);
            }
        }
        return -1;
    }
    
    public static void listarProdutos(ArrayList<Produto> aImprimir){
        System.out.println("-------------------------------------------------------------------------------------");
        for (Produto produto : aImprimir){
            if(produto.getEstoque() > 0){
            System.out.println(produto.toString());
            System.out.println("-------------------------------------------------------------------------------------");
            }
        }
    }
    
    public static void comprarProdutos(){
        entrada2 = new Scanner(System.in);
        int idProduto;
        int quantidade;
        System.out.println("Digite o ID do Produto que deseja comprar: ");
        idProduto = entrada2.nextInt();
        int selecionado = buscaProduto(Produtos, idProduto);
        if(selecionado == -1){
            System.out.println("Produto não localizado");
            return;
        }
        System.out.println("Digite a quantidade desejada:");
        quantidade = entrada2.nextInt();
        if((Produtos.get(selecionado).getEstoque() < quantidade) || (quantidade <=0)){
            System.out.println("Quantidade inválida ou estoque insuficiente!");
            return;
        } 
        Produtos.get(selecionado).removeEstoque(quantidade);
        if(Produtos.get(selecionado) instanceof Bebida){
            Carrinho.add(new Bebida(Produtos.get(selecionado).getId(), Produtos.get(selecionado).getDescricao(), Produtos.get(selecionado).getPreco(), quantidade));
        }
        if(Produtos.get(selecionado) instanceof Eletronico){
            Carrinho.add(new Eletronico(Produtos.get(selecionado).getId(), Produtos.get(selecionado).getDescricao(), Produtos.get(selecionado).getPreco(), quantidade));
        }
        if(Produtos.get(selecionado) instanceof Livro){
            Carrinho.add(new Livro(Produtos.get(selecionado).getId(), Produtos.get(selecionado).getDescricao(), Produtos.get(selecionado).getPreco(), quantidade));
        }
        System.out.println("Produto adicionado ao carrinho!");
        
        
        
    }
    
    public static void finalizarCompra(){
        double total=0;
        if(Carrinho.isEmpty()){
            System.out.println("Nenhum produto foi comprado!");
            return;
        }
        
        try{ //bloco que gera os dados da compra e nota fiscal no arquivo txt
            FileOutputStream nota = new FileOutputStream("notafiscal.txt", false);
            PrintWriter pnota = new PrintWriter(nota);
            System.out.println("------------------------------------------------------------------------------");
            pnota.println("------------------------------------------------------------------------------");
            pnota.println("-----------------------------Lojão do Programador-----------------------------");
            pnota.println("----------------------NOTA FISCAL DE VENDA AO CONSUMIDOR----------------------");
            pnota.println("---------------------------CNPJ: 01.001.001/0001-01---------------------------");
            pnota.println("-----------------------DESCRIÇÃO DOS PRODUTOS COMPRADOS-----------------------");
            pnota.println("------------------------------------------------------------------------------");
            
            for (Produto produto : Carrinho){
                System.out.println(produto.toString());
                System.out.println("------------------------------------------------------------------------------");
                pnota.println(produto.toString());
                pnota.println("------------------------------------------------------------------------------");
                total += produto.getPreco() * produto.getEstoque();
                    }
           String resultado = String.format("%.2f", total);
           System.out.println("O Valor total da compra foi: R$ " + resultado);
           pnota.println("-----------------O Valor total da compra foi: R$ " + resultado + "------------"); 
           pnota.close();
           System.out.println("Obrigado pela compra. Volte Sempre!\n");
       }
       catch(FileNotFoundException e){
           System.out.println("Arquivo não encontrado.");
       }

        Carrinho.clear();
        
        try{ //Bloco que grava as alterações de estoque após a compra 
           FileOutputStream arquivo = new FileOutputStream("produtos.loj", false);
           ObjectOutputStream o = new ObjectOutputStream(arquivo);
           for (Produto produto : Produtos){
               o.writeObject(produto);
            }
            o.close();
           System.out.println("Arquivo gravado com sucesso!\n");
       }
       catch(FileNotFoundException e){
           System.out.println("Arquivo não encontrado.");
       }
       catch(IOException e){
           System.out.println("Erro ao escrever no arquivo.");
       }

    }
        
    public static void restauraProdutos(){
       Bebida b1 = new Bebida("Cerveja Brahma", 6.49, 500);
       Bebida b2 = new Bebida("Cachaça", 10.90, 100);
       Bebida b3 = new Bebida("Coca-Cola", 5.49, 300);
       Bebida b4 = new Bebida("Pepsi", 4.99, 260);
       Eletronico e1 = new Eletronico("Televisor Sony 50\"", 2594.99, 30);
       Eletronico e2 = new Eletronico("Microondas Panasonic 30 Litros", 499.99, 60);
       Eletronico e3 = new Eletronico("Notebook DELL Inspiron", 1549.90, 20);
       Eletronico e4 = new Eletronico("Smartphone Samsung Galaxy S8", 3594.99, 150);
       Livro l1 = new Livro("Crônicas de Gelo e Fogo", 99.99, 36);
       Livro l2 = new Livro("Use a Cabeça! Java", 109.90, 57);
       Livro l3 = new Livro("Harry Potter e a criança amaldiçoada", 158.99, 76);
       Livro l4 = new Livro("Estruturas de Dados", 209.99, 15);
       try{
           FileOutputStream arquivo = new FileOutputStream("produtos.loj", false);
           ObjectOutputStream o = new ObjectOutputStream(arquivo);
           o.writeObject(b1);
           o.writeObject(b2);
           o.writeObject(b3);
           o.writeObject(b4);
           o.writeObject(e1);
           o.writeObject(e2);
           o.writeObject(e3);
           o.writeObject(e4);
           o.writeObject(l1);
           o.writeObject(l2);
           o.writeObject(l3);
           o.writeObject(l4);

           o.close();
           System.out.println("Arquivo gravado com sucesso!\n");
       }
       catch(FileNotFoundException e){
           System.out.println("Arquivo não encontrado.");
       }
       catch(IOException e){
           System.out.println("Erro ao escrever no arquivo.");
       }
    } //Fim restaurarProdutos
    
    public static void leProdutos(){
         try{
            FileInputStream arquivo = new FileInputStream("produtos.loj");
            ObjectInputStream o = new ObjectInputStream(arquivo);
            try{
                while(true){
                    Produto novo = (Produto) o.readObject();
                    Produtos.add (novo);
                    //System.out.println(novo);
                }
             }catch(EOFException e){
                System.out.println("Seja Bem-Vindo!");
             }
             catch(ClassNotFoundException e){
                System.out.println("Erro de sistema. Contate o suporte.");
             }
             finally{
                o.close();
             }
        }catch(FileNotFoundException e){
            System.out.println("Erro de sistema. Contate o suporte.");;
        }catch(IOException e){
            System.out.println("Erro de leitura dos produtos Contate o suporte.");
        }
    
    } //Fim leObjeto
    
    public static void main(String[] args) {
    
       //restauraProdutos(); // Somente habilitar para restaurar o estoque original
       leProdutos();
 
        char opcao = ' ';
        entrada = new Scanner(System.in);
        while(opcao != '0'){

            System.out.println("    ----====== LOJÃO DO PROGRAMADOR ======----\n");
            System.out.println("Digite a opção desejada:");
            System.out.println("    1 - Para listar os produtos disponíveis");
            System.out.println("    2 - Comprar produtos");
            System.out.println("    3 - Listar produtos do carrinho");
            System.out.println("    4 - Para finalizar a compra");
            System.out.println("    0 - Para sair");
            opcao = entrada.next().charAt(0);
                        
            switch (opcao) {
                case '1':
                    listarProdutos(Produtos);
                break;

                case '2':
                    listarProdutos(Produtos);
                    comprarProdutos();
                break;

                case '3':
                   if(Carrinho.isEmpty()){
                       System.out.println(" O Carrinho está vazio!");
                   }else{
                       listarProdutos(Carrinho);
                   }
                break;

                case '4':
                    finalizarCompra();
                break;

                case '6':
                    System.out.println("Sexta-feira");
                break;

                case '7':
                    System.out.println("Sábado");
                break;

                default:
                    if (opcao != '0'){
                        System.out.println("Opção inválida!");
                        System.out.println("Tente novamente");
                    }
                }// Fim Switch
            System.out.println("\n\n");
        } //Fim While Menu
    } //Fim Main
    
}
