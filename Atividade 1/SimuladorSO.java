public class SimuladorSO { 
    public static void main(String[] args) {
        Processo[] processos = new Processo[10];
        int[] temposExecucao = {10000, 5000, 7000, 3000, 3000, 8000, 2000, 5000, 4000, 10000};

        for (int pid = 0; pid < 10; pid++) {
            processos[pid] = new Processo(pid, temposExecucao[pid]);
        }

        for (Processo processo : processos) {
            if (processo.ep.equals("PRONTO")) {
                System.out.println("Troca de contexto: PRONTO >>> EXECUTANDO (PID: " + processo.pid + ")");
                processo.executar();
                processo.salvarDados();
            }
        }

        for (Processo processo : processos) {
            if (processo.ep.equals("TERMINADO")) {
                processo.imprimirResultado();
            }
        }
    }

}