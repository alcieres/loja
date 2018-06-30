
package loja;

import java.io.Serializable;


public class Livro implements Produto, Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4939615648465082288L;
	private int Id;
    private String descricao;
    private double preco;
    private int quantidade;
    private static int novoId = 3000;
    /*
    public Livro(){
        Scanner in = new Scanner(System.in);
        System.out.print("\nDescreva o produto: ");
        String descricao = in.nextLine();
        setDescricao(descricao);
        System.out.print("\nDigite o preço do produto: ");
        double preco = in.nextDouble();
        while(!setPreco(preco)){
            System.out.print("\nPreço inválido. Digite novamente: ");
            preco = in.nextDouble();  
        }
        System.out.print("\nDigite a quantidade: ");
        int quantidade = in.nextInt();
        while(!adicionaEstoque(quantidade)){
            System.out.println("\nQuantidade inválida. Digite novamente: ");
            quantidade = in.nextInt();
        }      
        this.Id = ++novoId;
    }
    */
    public Livro(int nId, String descricao, double preco, int quantidade){
        Id = nId;
        setDescricao(descricao);
        setPreco(preco);
        adicionaEstoque(quantidade);
      
    }
    
    public Livro(String descricao, double preco, int quantidade){
        setDescricao(descricao);
        setPreco(preco);
        adicionaEstoque(quantidade);
        this.Id = ++novoId;
    }
    @Override
    public int getId() {
        return Id;
    }

    @Override
    public double getPreco() {
        return preco;
    }

    @Override
    public boolean setPreco(double preco) {
        if (preco >= 0){
            this.preco = preco;
            return true;
        } else{
            return false;
        }
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    @Override
    public int getEstoque() {
        return quantidade;
    }
    
    @Override
    public boolean adicionaEstoque(int quantidade) {
        if (quantidade > 0){
            this.quantidade += quantidade;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean removeEstoque(int quantidade) {
        if (quantidade <= this.quantidade){
            this.quantidade -= quantidade;
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public String toString(){
        return "Livro: " + getDescricao() +
               " ID: " + getId() + 
               " Preço: R$ " + getPreco() +
               " Quantidade: " + quantidade;
        
    }

    
    

    
}
