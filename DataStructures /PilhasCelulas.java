import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileReader;
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

    // Metodos Publicos

    public Serie clone() {
        return new Serie(
                name,
                format,
                duration,
                fromCountry,
                originalLanguage,
                originalBroadcaster,
                inicialTransmissionDate,
                seasonQuantity,
                episodeQuantity);
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

class Celula {

    private Serie item;
    private Celula proximo;

    public Celula(Serie novo) {
        item = novo;
        proximo = null;
    }

    public Celula() {

        item = new Serie();
        proximo = null;
    }

    public Serie getItem() {
        return item;
    }

    public void setItem(Serie item) {
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
    private Celula primeira;
    public int tamanho;

    public Pilha() {

        Celula sentinela;

        sentinela = new Celula();
        fundo = sentinela;
        topo = sentinela;
    }

    public boolean pilhaVazia() {
        return (topo == fundo);
    }

    public void empilhar(Serie novo) {

        Celula novaCelula;

        novaCelula = new Celula(novo);
        novaCelula.setProximo(topo);
        topo = novaCelula;
        tamanho = tamanho + 1;
        primeira = topo;
        if (tamanho == 1) {
            novaCelula = new Celula(novo);
            novaCelula.setProximo(topo);
            primeira.setProximo(null);
            topo = novaCelula;
        }
    }

    public Serie desempilhar() throws Exception {

        Celula desempilhado;
        tamanho--;
        if (!pilhaVazia()) {
            desempilhado = topo;
            topo = topo.getProximo();
            desempilhado.setProximo(null);
            return (desempilhado.getItem());
        } else
            throw new Exception("Não foi possível desempilhar: a pilha está vazia!");
    }

    public void mostrarDesempilados() throws Exception {
        Celula aux;
        if (!pilhaVazia()) {
            aux = topo;
            while (aux != null && tamanho != 0) {
                System.out.print("(D) ");
                String Saida = aux.getItem().getName();
                System.out.println(Saida);
                aux = aux.getProximo();
                tamanho--;
            }
        } else {
            throw new Exception("Não deu pra imprimir");
        }
    }

    public Serie procurar(String nome) {
        Celula aux;
        if (!pilhaVazia()) {
            aux = topo;
            while (aux != null) {
                if (aux.getItem().getName().equals(nome)) {
                    return aux.getItem();
                }
                aux = aux.getProximo();
            }

        }

        return null;
    }

    public void mostrar() throws Exception {
        Celula aux;
        int j=0;
        if (!pilhaVazia()) {
            aux = topo;
            while (aux != null && tamanho != 0) {
                System.out.print("["+j+"]");
                aux.getItem().print();
                aux = aux.getProximo();
                tamanho--;
                j=j+1;
            }

        } else {
            throw new Exception("The line is already empty, no print will be conducted");
        }
    }

    public void copiar() throws Exception {
        Pilha copia = new Pilha();
        Celula aux;
        if (!pilhaVazia()) {
            aux = topo;
            while (aux != null && tamanho != 0) {
                copia.empilhar(aux.getItem());
                aux = aux.getProximo();
                tamanho--;
            }
        } else {
            throw new Exception("The line is already empty, no print will be conducted");
        }

    }

}

class PilhasCelulas {
    public static void main(String[] args) throws Exception {

        MyIO.setCharset("UTF-8");
        Pilha auxiliar = new Pilha();
        Reader reader = new Reader("//tmp/data.txt");
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
                auxiliar.empilhar(serieInstance);
            } catch (Exception e) {
            }

            serieData = reader.read();

        }

        // Making a new array to storage the privious amont of Series
        Pilha Invertida = new Pilha();
        String l;

        while ((l = MyIO.readLine()) != null && !l.equals("FIM")) {
            Serie leitura = auxiliar.procurar(l);
            Invertida.empilhar(leitura);

        }
        /// Reading the number of queue operations I(inpilha), R(remove)
        int quantidade = MyIO.readInt();
        Pilha desenpilhadoSeries = new Pilha();
        Pilha desemInverter = new Pilha();
        Pilha Series = new Pilha();

        for (int i = 0; i < quantidade; i++) {
            String command = MyIO.readLine();
            if (command != null) {
                try {
                    if (command.equals("D")) {
                        Serie desenpilhado = new Serie();
                        desenpilhado = Invertida.desempilhar();
                        desenpilhadoSeries.empilhar(desenpilhado);

                    } else {
                        command = command.replace("E ", "");
                        Serie leitura = auxiliar.procurar(command);
                        Invertida.empilhar(leitura);
                    }

                } catch (Exception e) {
                    System.out.println("Por favor digite algo antes de enviar");
                }

            }

        }
        for( int i = Invertida.tamanho; i >0 ; i-- ){
            Series.empilhar(Invertida.desempilhar());
        }
        for( int i=desenpilhadoSeries.tamanho; i >0 ; i-- ){
            desemInverter.empilhar(desenpilhadoSeries.desempilhar());

        }
        desemInverter.mostrarDesempilados();
        Series.mostrar();
    }
}