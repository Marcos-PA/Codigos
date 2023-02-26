public class App {
    public static void main(String[] args) throws Exception {
        Lista_Produtos estoque = new Lista_Produtos(15);
        Produto P1 = new Produto("Notebook", 1000, 45, 5);
        Produto P2 = new Produto("Mouse", 120, 45, 5);
        Produto P3 = new Produto("Teclado", 140, 45, 5);
        Produto P4 = new Produto("Roteador", 300, 45, 5);
        Produto P5 = new Produto("Fone de Ouvido", 35, 45, 5);

        estoque.Guardar(P1);
        estoque.Guardar(P2);
        estoque.Guardar(P3);
        estoque.Guardar(P4);
        estoque.Guardar(P5);
        estoque.procurar("Teclado");
        estoque.imprimir();
        System.out.println(P1.preco_revenda);
        
    }
}
