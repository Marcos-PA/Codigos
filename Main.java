class Inteiro {

    private int valor;

    public Inteiro() {
        valor = 0;
    }

    public Inteiro(int i) {
        valor = i;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void imprimir() {
        System.out.println("Valor: " + this.valor);
    }
}

class Celula {

    private Inteiro item;
    private Celula proximo;

    public Celula(Inteiro novo) {
        item = novo;
        proximo = null;
    }

    public Celula() {

        item = new Inteiro();
        proximo = null;
    }

    public Inteiro getItem() {
        return item;
    }

    public void setItem(Inteiro item) {
        this.item = item;
    }

    public Celula getProximo() {
        return proximo;
    }

    public void setProximo(Celula proximo) {
        this.proximo = proximo;
    }
}

class Pilha {

    private Celula fundo;
    private Celula topo;

    public Pilha() {

        Celula sentinela;

        sentinela = new Celula();
        fundo = sentinela;
        topo = sentinela;
    }

    public boolean pilhaVazia() {

        boolean resp;

        if (topo == fundo)
            resp = true;
        else
            resp = false;

        return resp;
    }

    public void empilhar(Inteiro novo) {

        Celula novaCelula;

        novaCelula = new Celula(novo);
        novaCelula.setProximo(topo);
        topo = novaCelula;
    }

    public Inteiro desempilhar() throws Exception {

        Celula desempilhado;

        if (!pilhaVazia()) {
            desempilhado = topo;
            topo = topo.getProximo();
            desempilhado.setProximo(null);
            return (desempilhado.getItem());
        } else
            throw new Exception("Não foi possível desempilhar: a pilha está vazia!");
    }

    public void  mostrar() {
        if (!pilhaVazia()) {
            Celula aux;
            aux = fundo.getProximo();
            while (aux != null) {
                System.out.println( aux.getItem());
                aux = aux.getProximo();
            }
        }
    }

    public Inteiro consultarTopo() throws Exception {

        if (!pilhaVazia()) {
            return (topo.getItem());
        } else
            throw new Exception("Não foi possível consultar o topo da pilha: a pilha está vazia!");
    }
}

class Main {
    public static void main(String[] args) {

        Pilha minhaPilha;
        Inteiro novo;
        Inteiro topo;
        Inteiro desempilhado;

        minhaPilha = new Pilha();

        novo = new Inteiro(25);
        try {
            minhaPilha.empilhar(novo);
        } catch (Exception erro) {
            System.out.println(erro.getMessage());
        }

        novo = new Inteiro(10);
        try {
            minhaPilha.empilhar(novo);
        } catch (Exception erro) {
            System.out.println(erro.getMessage());
        }

        novo = new Inteiro(21);
        try {
            minhaPilha.empilhar(novo);
        } catch (Exception erro) {
            System.out.println(erro.getMessage());
        }

        novo = new Inteiro(3);
        try {
            minhaPilha.empilhar(novo);
        } catch (Exception erro) {
            System.out.println(erro.getMessage());
        }

        try {
            topo = minhaPilha.consultarTopo();
            System.out.print("Elemento do topo da pilha: ");
            topo.imprimir(); // 3
            minhaPilha.mostrar();
        } catch (Exception erro) {
            System.out.println(erro.getMessage());
        }

        try {
            desempilhado = minhaPilha.desempilhar();
            System.out.print("Elemento desempilhado: ");
            desempilhado.imprimir(); // 3
        } catch (Exception erro) {
            System.out.println(erro.getMessage());
        }

        try {
            topo = minhaPilha.consultarTopo();
            System.out.print("Elemento do topo da pilha: ");
            topo.imprimir(); // 21
        } catch (Exception erro) {
            System.out.println(erro.getMessage());
        }
    }
}