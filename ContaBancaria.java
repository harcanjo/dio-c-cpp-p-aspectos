// Classe base para as contas bancárias
public class ContaBancaria {
    protected double saldo;
    
    // Construtor
    public ContaBancaria(double saldoInicial) {
        this.saldo = saldoInicial;
    }
    
    // Método de saque
    public void sacar(double valor) {
        System.out.println("Realizando saque de " + valor + " da conta.");
        this.saldo -= valor;
    }
}

// Classe específica para conta corrente
public class ContaCorrente extends ContaBancaria {
    // Construtor
    public ContaCorrente(double saldoInicial) {
        super(saldoInicial);
    }
}

// Aspecto para a verificação de saldo
public aspect VerificacaoSaldoAspect {
    // Ponto de corte para o método de saque em todas as contas bancárias
    pointcut saqueOperation(): execution(* ContaBancaria.sacar(double));
    
    // Ação executada antes do método de saque
    before(): saqueOperation() {
        double valor = ((ContaBancaria) thisJoinPoint.getTarget()).saldo;
        if (valor < 0) {
            System.out.println("Saldo insuficiente. Saque não permitido.");
        }
    }
}

// Classe de teste
public class Main {
    public static void main(String[] args) {
        ContaCorrente conta1 = new ContaCorrente(1000);
        ContaBancaria conta2 = new ContaBancaria(500);
        
        conta1.sacar(200);
        conta2.sacar(700);
    }
}