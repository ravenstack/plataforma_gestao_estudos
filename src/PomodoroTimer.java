import java.io.IOException;
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

            if (!contagemRegressivaComCancelamento(focoMinutos, sc)) break;

            if (cancelar) break;

            System.out.println("\nPausa de " + pausaMinutos + " minutos...");
            if (!contagemRegressivaComCancelamento(pausaMinutos, sc)) break;
        }

        if (!cancelar) {
            System.out.println("\n🎉 Todos os ciclos finalizados com sucesso!");
        }
    }

    private boolean contagemRegressivaComCancelamento(int minutos, Scanner sc) {

        int totalSegundos = minutos * 60;

        System.out.println("Digite 'c' e pressione Enter a qualquer momento para cancelar o ciclo (ele não será contabilizado).");

        while (totalSegundos > 0 && !cancelar) {

            int min = totalSegundos / 60;
            int sec = totalSegundos % 60;

            System.out.printf("Tempo restante: %02d:%02d\r", min, sec);
            System.out.flush();

            try {
                Thread.sleep(1000); // espera 1 segundo
            } catch (InterruptedException e) {}

            totalSegundos--;

            if (verificarCancelamento(sc)) {
                break;
            }
        }

        if (cancelar) {
            System.out.println("\nPomodoro cancelado pelo usuário. Este ciclo não será contabilizado.");
            return false;
        }

        System.out.println("\n⏰ Tempo encerrado!");
        return true;
    }

    private boolean verificarCancelamento(Scanner sc) {
        try {
            if (System.in.available() > 0) {
                String r = sc.nextLine().trim().toLowerCase();

                if (r.equals("c")) {
                    cancelar = true;
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("\nNão foi possível ler o comando de cancelamento. Continue acompanhando manualmente o ciclo.");
        }

        return false;
    }
}