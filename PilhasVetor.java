import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileReader;
import java.io.IOException;

class Pilha {
    private Serie[] pilha;
    private int topo;

    // Initializes the line
    public Pilha(int tamanho) {
        pilha = new Serie[tamanho];
        topo = 0;

    }

    public boolean pilhaCheia() {
        return (topo == pilha.length);
    }

    public boolean pilhaVazia() {
        return (topo == 0);
    }

    public Serie desempilhar() throws Exception {

        Serie desempilhado;

        if (!pilhaVazia()) {
            topo--;
            desempilhado = pilha[topo];
            return desempilhado;
        } else
            throw new Exception("Não foi possível desempilhar: a pilha está vazia!");
    }

    public void empilhar(Serie serie) throws Exception {

        if (!pilhaCheia()) {
            pilha[topo] = serie;
            topo++;
        } else
            throw new Exception("Não foi possível empilhar: a pilha está cheia!");
    }

    public void mostrar() throws Exception {
        if (!pilhaVazia()) {
            for (int i = 0; i < topo; i++) {
                int position = i;
                System.out.print("[" + position + "] ");
                pilha[position].print();
            }
        } else {
            throw new Exception("The line is already empty, no print will be conducted");
        }
    }

    public void mostrarDesempilados() throws Exception {
        if (!pilhaVazia()) {
            for (int i = 0; i < topo; i++) {
                int position = i;
                System.out.print("(D) ");
                String Saida = pilha[position].getName();
                System.out.println(Saida);
            }
        } else {
            throw new Exception("The line is already empty, no print will be conducted");
        }
    }

    public Serie procurar(String nome) {
        if (!pilhaVazia()) {
            for (int i = 0; i < pilha.length; i++) {
                int position = i;
                if (pilha[position].getName().equals(nome)) {
                    return pilha[position];
                }
            }
        }
        return null;

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

public class Main {
    public static void main(String[] args) throws Exception {

        MyIO.setCharset("UTF-8");
        Pilha auxiliar = new Pilha(62);
        Reader reader = new Reader("tmp/data.txt");
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
        Pilha Series = new Pilha(62);
        String l;

        while ((l = MyIO.readLine()) != null && !l.equals("FIM")) {
            Serie leitura = auxiliar.procurar(l);
            Series.empilhar(leitura);

        }
        /// Reading the number of queue operations I(inpilha), R(remove)
        int quantidade = MyIO.readInt();
        Pilha desenpilhadoSeries = new Pilha(28);

        for (int i = 0; i < quantidade; i++) {
            String command = MyIO.readLine();
            try {
                if (command.equals("D")) {
                    Serie desenpilhado = new Serie();
                    desenpilhado = Series.desempilhar();
                    desenpilhadoSeries.empilhar(desenpilhado);

                } else {
                    command = command.replace("E ", "");
                    Serie leitura = auxiliar.procurar(command);
                    Series.empilhar(leitura);

                }
            } catch (Exception e) {
            }

        }
        desenpilhadoSeries.mostrarDesempilados();
        Series.mostrar();

    }
}
