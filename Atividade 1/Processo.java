import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

class Processo {
    int pid, tp, cp, nes, nCpu;
    String ep;

    public Processo(int pid, int tp) {
        this.pid = pid;
        this.tp = tp;
        this.cp = 0;
        this.ep = "PRONTO";
        this.nes = 0;
        this.nCpu = 0;
    }

    public void executar() {
        this.ep = "EXECUTANDO";
        int quantum = 1000;
        Random random = new Random();

        while (this.cp < this.tp) {
            if (random.nextDouble() < 0.05) {
                this.ep = "BLOQUEADO";
                this.nes++;
                break;
            } else {
                this.nCpu += quantum;
                this.cp += quantum;

                if (this.cp >= this.tp) {
                    this.ep = "TERMINADO";
                    break;
                }

                this.ep = "PRONTO";
                System.out.println("Troca de contexto: EXECUTANDO >>> PRONTO");
                salvarDados();
            }
        }
    }

    public void salvarDados() {
        try (FileWriter arquivo = new FileWriter("tabela_processos.txt", true);
             PrintWriter gravador = new PrintWriter(arquivo)) {
            gravador.println("PID: " + pid + ", TP: " + tp + ", CP: " + cp + ", EP: " + ep + ", NES: " + nes + ", N_CPU: " + nCpu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void imprimirResultado() {
        System.out.println("PID: " + pid + ", TP: " + tp + ", CP: " + cp + ", EP: " + ep + ", NES: " + nes + ", N_CPU: " + nCpu);
    }
}