import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class Serie {
    // ATRIBUTOS PRIVADOS
    private String name;
    private String format;
    private String duration;
    private String fromCountry;
    private String originalLanguage;
    private String originalBroadcaster;
    private String inicialTransmissionDate;
    private int seasonQuantity;
    private int episodeQuantity;

    // CONSTRUTORES

    public Serie() {
    }

    public Serie(String name, String format, String duration, String fromCountry, String originalLanguage,
            String originalBroadcaster, String initialTransmissionDate, int seasonQuantity,
            int episodeQuantity) {
        this.name = name;
        this.format = format;
        this.duration = duration;
        this.fromCountry = fromCountry;
        this.originalLanguage = originalLanguage;
        this.originalBroadcaster = originalBroadcaster;
        this.inicialTransmissionDate = initialTransmissionDate;
        this.seasonQuantity = seasonQuantity;
        this.episodeQuantity = episodeQuantity;
    }

    public void read(String stringSerie) {

        // How I Met Your Mother;Sitcom;23 min. aprox.;Estados Unidos;Inglês; CBS;19 de
        // setembro de 2005–31 de março de 2014;9;208

        if (stringSerie != null) {
            String[] serieData = stringSerie.split(";");
            setName(serieData[0]);
            setFormat(serieData[1]);
            setDuration(serieData[2]);
            setFromCountry(serieData[3]);
            setOriginalLanguage(serieData[4]);
            setOriginalBroadcaster(serieData[5]);
            setInitialTransmissionDate(serieData[6]);
            setSeasonQuantity(Integer.parseInt(serieData[7]));
            setEpisodeQuantity(Integer.parseInt(serieData[8]));
        }

    }

    public void print() {
        MyIO.println(this.getName() + " ## " + this.getFormat() + " ## " + this.getDuration() + " ## "
                + this.getFromCountry()
                + " ## " + this.getOriginalLanguage() + " ## " + this.getOriginalBroadcaster() + " ## "
                + this.getInitialTransmissionDate() + " ## "
                + this.getSeasonQuantity() + " ## " + this.getEpisodeQuantity());
    }

    // GETTERS e SETTERS

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFromCountry() {
        return fromCountry;
    }

    public void setFromCountry(String fromCountry) {
        this.fromCountry = fromCountry;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalBroadcaster() {
        return originalBroadcaster;
    }

    public void setOriginalBroadcaster(String originalBroadcaster) {
        this.originalBroadcaster = originalBroadcaster;
    }

    public String getInitialTransmissionDate() {
        return inicialTransmissionDate;
    }

    public void setInitialTransmissionDate(String InitialTransmissionDate) {
        this.inicialTransmissionDate = InitialTransmissionDate;
    }

    public int getSeasonQuantity() {
        return seasonQuantity;
    }

    public void setSeasonQuantity(int seasonQuantity) {
        this.seasonQuantity = seasonQuantity;
    }

    public int getEpisodeQuantity() {
        return episodeQuantity;
    }

    public void setEpisodeQuantity(int episodeQuantity) {
        this.episodeQuantity = episodeQuantity;
    }
}

class Reader {
    private BufferedReader input;

    public Reader(String fileName) {
        try {
            input = new BufferedReader(new FileReader(fileName));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void closeFile() {

        try {
            input.close();
        } catch (Exception e) {
            System.out.println("Error while closing : " + e);
        }
    }

    @SuppressWarnings("finally")
    public String read() {

        String inputText = null;

        try {
            inputText = input.readLine();
        } catch (EOFException e) {
            inputText = null;
        } catch (IOException e) {
            System.out.println("Reading error: " + e);
            inputText = null;
        } finally {
            return inputText;
        }
    }
}

class ArquivoTextoEscrita {

    private BufferedWriter saida;

    ArquivoTextoEscrita(String nomeArquivo) {

        try {
            saida = new BufferedWriter(new FileWriter(nomeArquivo));
        } catch (FileNotFoundException excecao) {
            System.out.println("Arquivo nao encontrado");
        } catch (IOException excecao) {
            System.out.println("Erro na abertura do arquivo de escrita: " + excecao);
        }
    }

    public void fecharArquivo() {

        try {
            saida.close();
        } catch (IOException excecao) {
            System.out.println("Erro no fechamento do arquivo de escrita: " + excecao);
        }
    }

    public void escrever(String textoEntrada) {

        try {
            saida.write(textoEntrada);
            saida.newLine();
        } catch (IOException excecao) {
            System.out.println("Erro de entrada/saída " + excecao);
        }
    }
}

class No {

    private Serie item;
    private No esquerda;
    private No direita;

    public No() {

        item = new Serie();
        esquerda = null;
        direita = null;
    }

    public No(Serie registro) {

        item = registro;
        esquerda = null;
        direita = null;
    }

    public Serie getItem() {
        return item;
    }

    public void setItem(Serie item) {
        this.item = item;
    }

    public No getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No esquerda) {
        this.esquerda = esquerda;
    }

    public No getDireita() {
        return direita;
    }

    public void setDireita(No direita) {
        this.direita = direita;
    }
}

class ABB {

    public static int COMPARACOES_INSERÇÃO = 0;
    public static int COMPARACOES_REMOÇÃO = 0;
    public static int COMPARACOES_PESQUISA = 0;
  
    private No raiz;

    public ABB() {

        raiz = null;
    }

    public Serie pesquisar(String chave) {
        return pesquisar(this.raiz, chave);
    }

    private Serie pesquisar(No raizSubarvore, String chave) {
      COMPARACOES_PESQUISA++;
        if (raizSubarvore == null) {

            return null;
        }
        if (raizSubarvore.getItem().getName().compareTo(chave) == 0) {
            return raizSubarvore.getItem();
        } else if (raizSubarvore.getItem().getName().compareTo(chave) < 0) {
            return pesquisar(raizSubarvore.getDireita(), chave);
        } else {
            return pesquisar(raizSubarvore.getEsquerda(), chave);
        }
    }

    public Serie procurar(String chave) {
        return procurar(this.raiz, chave);
    }

    public Serie procurar(No raizSubarvore, String chave) {
        if (raizSubarvore == null) {
            System.out.println("NAO");
            return null;
        }
        if (raizSubarvore.getItem().getName().compareTo(chave) == 0) {
            String saida = raizSubarvore.getItem().getName();
            System.out.println("[" + saida + "] - SIM ");
            return raizSubarvore.getItem();
        } else if (raizSubarvore.getItem().getName().compareTo(chave) < 0) {
            String saida = raizSubarvore.getItem().getName();
            System.out.print("[" + saida + "] - ");
            return procurar(raizSubarvore.getDireita(), chave);
        } else {
            String saida = raizSubarvore.getItem().getName();
            System.out.print("[" + saida + "] - ");
            return procurar(raizSubarvore.getEsquerda(), chave);
        }
    }

    public void inserir(Serie novo) {
        this.raiz = inserir(this.raiz, novo);
    }

    private No inserir(No raizSubarvore, Serie novo) {
      COMPARACOES_INSERÇÃO++;
        if (raizSubarvore == null) {
            raizSubarvore = new No(novo);
        } else if (raizSubarvore.getItem().getName().compareTo(novo.getName()) > 0) {
            raizSubarvore.setEsquerda(inserir(raizSubarvore.getEsquerda(), novo));
        } else {
            raizSubarvore.setDireita(inserir(raizSubarvore.getDireita(), novo));
        }

        return raizSubarvore;
    }

    public void remover(String chaveRemover) throws Exception {
        this.raiz = remover(this.raiz, chaveRemover);
    }

    private No remover(No raizSubarvore, String chaveRemover) throws Exception {
      COMPARACOES_REMOÇÃO++;
        if (raizSubarvore == null)
            throw new Exception("Não foi possível remover o item da árvore: chave não encontrada!");
        else if (chaveRemover == raizSubarvore.getItem().getName()) {
            if (raizSubarvore.getEsquerda() == null)
                raizSubarvore = raizSubarvore.getDireita();
            else if (raizSubarvore.getDireita() == null)
                raizSubarvore = raizSubarvore.getEsquerda();
            else
                raizSubarvore.setEsquerda(antecessor(raizSubarvore, raizSubarvore.getEsquerda()));
        } else if (raizSubarvore.getItem().getName().compareTo(chaveRemover) > 0)
            raizSubarvore.setDireita(remover(raizSubarvore.getDireita(), chaveRemover));
        else
            raizSubarvore.setEsquerda(remover(raizSubarvore.getEsquerda(), chaveRemover));

        return raizSubarvore;
    }

    private No antecessor(No noRetirar, No raizSubarvore) {

        if (raizSubarvore.getDireita() != null)
            raizSubarvore.setDireita(antecessor(noRetirar, raizSubarvore.getDireita()));
        else {
            noRetirar.setItem(raizSubarvore.getItem());
            raizSubarvore = raizSubarvore.getEsquerda();
        }

        return raizSubarvore;
    }

    public Boolean findIN(String chave) {
        return findIN(this.raiz, chave);
    }

    private Boolean findIN(No raizSubarvore, String chave) {
        if (raizSubarvore == null) {
            return null;
        }
        if (raizSubarvore.getItem().getName().compareTo(chave) == 0) {
            return true;
        } else if (raizSubarvore.getItem().getName().compareTo(chave) < 0) {
            return findIN(raizSubarvore.getDireita(), chave);
        } else {
            return findIN(raizSubarvore.getEsquerda(), chave);
        }
    }

    public void caminhamentoEmOrdem() {
        caminhamentoEmOrdem(this.raiz);
    }

    private void caminhamentoEmOrdem(No raizSubarvore) {
        if (raizSubarvore != null) {
            caminhamentoEmOrdem(raizSubarvore.getEsquerda());
            String saida = raizSubarvore.getItem().getName();
            System.out.print("[" + saida + "] - ");
            caminhamentoEmOrdem(raizSubarvore.getDireita());
        }
    }
  
}

class Main {
  public static long tempoInicial; 
    public static void main(String[] args) throws Exception {
        MyIO.setCharset("UTF-8");
        ABB auxiliar = new ABB();
        Reader reader = new Reader("//tmp/data.txt");
      ArquivoTextoEscrita output = new ArquivoTextoEscrita("//tmp/746639_arvoreBinaria.txt");
        // ArquivoTextoEscrita output = new
        // ArquivoTextoEscrita("//tmp/746639_arvoreBinaria.txt");

        String serieData;

        // Discards the first pilha
        reader.read();
        // Reads the second pilha
        serieData = reader.read();

        // This pilha bellow was excrutiating

        while (serieData != null && !serieData.equals("FIM")) {
            Serie serieInstance = new Serie();
            serieInstance.read(serieData);
            try {
                if (auxiliar.findIN(serieInstance.getName()) == null) {
                    auxiliar.inserir(serieInstance);
                } else if (auxiliar.findIN(serieInstance.getName()) != true) {
                    auxiliar.inserir(serieInstance);
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            serieData = reader.read();

        }
        
        ABB minhaArvore = new ABB();
        String l;
        
        while ((l = MyIO.readLine()) != null && !l.equals("FIM")) {
          tempoInicial = System.currentTimeMillis();  
            Serie leitura = auxiliar.pesquisar(l);
            if (minhaArvore.findIN(l) == null) {
                minhaArvore.inserir(leitura);
            } else if (minhaArvore.findIN(l) != true) {
                minhaArvore.inserir(leitura);
            }

        }
        String entrada;
        while ((entrada = MyIO.readLine()) != null && !entrada.equals("FIM")) {
            Serie procurado = minhaArvore.procurar(entrada);
        }
        double result = ((System.currentTimeMillis() - tempoInicial));

          output.escrever("746639" + "\t" + "Tempo decorrido: " + result + "\t" + "Comparações de inserções: " + minhaArvore.COMPARACOES_INSERÇÃO + "\t" + "Comparações de remoções: " + minhaArvore.COMPARACOES_REMOÇÃO + "\t" + "Comparações de pesquisa: " + minhaArvore.COMPARACOES_PESQUISA);
          output.fecharArquivo();

    }
}