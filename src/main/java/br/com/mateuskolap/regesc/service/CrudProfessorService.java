package br.com.mateuskolap.regesc.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.mateuskolap.regesc.orm.Professor;
import br.com.mateuskolap.regesc.repository.ProfessorRepository;

@Service
public class CrudProfessorService {
    private ProfessorRepository professorRepository;  // Dependência para o repositório

    // O Spring cria automaticamente um objeto desse tipo e disponibiliza para quem precisar (Injeção de dependência)
    public CrudProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public void menu(Scanner scanner) {
        Boolean isTrue = true;

        while (isTrue) {
            System.out.println("Qual ação deseja executar?");
            System.out.println("0 - Voltar");
            System.out.println("1 - Cadastrar novo professor");

            int opcao = scanner.nextInt();
            scanner.nextLine();  // Consome a quebra de linha

            switch (opcao) {
                case 1:
                    this.cadastrar(scanner);
                    break;
            
                default:
                    isTrue = false;
                    break;
            }
        }

        System.out.println();
    }

    private void cadastrar(Scanner scanner) {
        System.out.print("Digite o nome do professor: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o prontuario do professor: ");
        String prontuario = scanner.next();

        Professor professor = new Professor(nome, prontuario);
        this.professorRepository.save(professor);
        System.out.println("Professor cadastrado com sucesso!\n");
    }
}
