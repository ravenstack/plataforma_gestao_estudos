import java.util.Scanner;

public class PomodoroTimer {

    private int focoMinutos;
    private int pausaMinutos;
    private int ciclos;
    private boolean cancelar = false;

    public PomodoroTimer(int focoMinutos, int pausaMinutos, int ciclos) {

        if (focoMinutos <= 0 || pausaMinutos <= 0 || ciclos <= 0) {
            throw new IllegalArgumentException("Todos os valores devem ser maiores que zero.");
        }

        this.focoMinutos = focoMinutos;
        this.pausaMinutos = pausaMinutos;
        this.ciclos = ciclos;
    }

    public void iniciar() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n===== INICIANDO MODO POMODORO =====");

        for (int cicloAtual = 1; cicloAtual <= ciclos; cicloAtual++) {

            if (cancelar) break;

            System.out.println("\n--- CICLO " + cicloAtual + " DE " + ciclos + " ---");
            System.out.println("Tempo de foco: " + focoMinutos + " minutos");

            if (!contagemRegressivaComIntervalo(focoMinutos, sc)) break;

            if (cancelar) break;

            System.out.println("\nPausa de " + pausaMinutos + " minutos...");
            if (!contagemRegressivaComIntervalo(pausaMinutos, sc)) break;
        }

        if (!cancelar) {
            System.out.println("\n🎉 Todos os ciclos finalizados com sucesso!");
        }
    }

    // PERGUNTA SOMENTE A CADA 20 SEGUNDOS
    private boolean contagemRegressivaComIntervalo(int minutos, Scanner sc) {

        int totalSegundos = minutos * 60;
        int intervaloPergunta = 20; // PERGUNTAR A CADA 20 SEGUNDOS

        while (totalSegundos > 0 && !cancelar) {

            int min = totalSegundos / 60;
            int sec = totalSegundos % 60;

            System.out.printf("Tempo restante: %02d:%02d\r", min, sec);
            System.out.flush();

            try {
                Thread.sleep(1000); // espera 1 segundo
            } catch (InterruptedException e) {}

            totalSegundos--;

            // de 20 em 20 segundos → perguntar
            if (totalSegundos % intervaloPergunta == 0 && totalSegundos > 0) {

                System.out.print("\nDeseja encerrar o Pomodoro? (s/n): ");
                String r = sc.nextLine().trim().toLowerCase();

                if (r.equals("s")) {
                    cancelar = true;
                    return false;
                }
            }
        }

        if (!cancelar) {
            System.out.println("\n⏰ Tempo encerrado!");
        }

        return !cancelar;
    }
}
