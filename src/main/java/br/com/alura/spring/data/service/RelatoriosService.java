package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatoriosService {
    private final FuncionarioRepository funcionarioRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private boolean system = true;


    public RelatoriosService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner) {
        while (system) {
            System.out.println("Qual ação de cargos deseja executar");
            System.out.println("0 - Sair");
            System.out.println("1 - Busca funcionario por nome");
            System.out.println("2 - Busca funcionario por nome, data contatação e salario maior");
            System.out.println("3 - Busca funcionario por data contatação maior");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    buscaFuncionarioNome(scanner);
                    break;
                case 2:
                    buscaFuncionarioNomeSalarioMaiorData(scanner);
                    break;
                case 3:
                    buscaFuncionarioDataContratacao(scanner);
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    private void buscaFuncionarioNome(Scanner scanner) {
        System.out.println("Qual nome deseja pesquisar");
        String nome = scanner.next();
        List<Funcionario> list = funcionarioRepository.findByNome(nome);
        list.forEach(System.out::println);
    }

    private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner) {
        System.out.println("Qual nome deseja pesquisar");
        String nome = scanner.next();
        System.out.println("Qual data contratação deseja pesquisar");
        String data = scanner.next();
        LocalDate dataContratacao = LocalDate.parse(data, formatter);
        System.out.println("Qual salario deseja pesquisar");
        Double salario = scanner.nextDouble();
        List<Funcionario> list = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario, dataContratacao);
        list.forEach(System.out::println);
    }

    private void buscaFuncionarioDataContratacao(Scanner scanner) {
        System.out.println("Qual data contratação deseja pesquisar");
        String data = scanner.next();
        LocalDate dataContratacao = LocalDate.parse(data, formatter);
        List<Funcionario> list = funcionarioRepository.findDataContratacaoMaior(dataContratacao);
        list.forEach(System.out::println);
    }
}
