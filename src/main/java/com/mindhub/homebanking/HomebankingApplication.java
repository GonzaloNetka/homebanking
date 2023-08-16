package com.mindhub.homebanking;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}


	@Bean
	public CommandLineRunner init(ClientRepository clientRepository , AccountRepository accountRepository , TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository, CardRepository cardRepository){
		return args -> {
			Client client1 = new Client("Melba","Morel","melba@mindhub.com");
			Client client2 = new Client("Carlos","Machado","cmachado@mindhub.com");

			clientRepository.save(client1);
			clientRepository.save(client2);

			Account account1 = new Account("VIN001", LocalDate.now(),5000);
			Account account2 = new Account("VIN002", LocalDate.now().plusDays(1),7500);
			Account account3 = new Account("VIN003", LocalDate.now(),10000);

			client1.addAccount(account1);
			client1.addAccount(account2);
			client2.addAccount(account3);

			accountRepository.save(account1);
			accountRepository.save(account2);
			accountRepository.save(account3);

			Transaction transaction1 = new Transaction(TransactionType.CREDITO, 450, "pogo cuota", LocalDateTime.now());
			Transaction transaction2 = new Transaction(TransactionType.DEBITO, -850, "celular", LocalDateTime.now());
			Transaction transaction3 = new Transaction(TransactionType.CREDITO, 1500, "pogo cuota", LocalDateTime.now());
			Transaction transaction4 = new Transaction(TransactionType.DEBITO, -450, "pogo cuota", LocalDateTime.now());
			Transaction transaction5 = new Transaction(TransactionType.CREDITO, 1450, "pogo cuota", LocalDateTime.now());
			Transaction transaction6 = new Transaction(TransactionType.DEBITO, -650, "pogo cuota", LocalDateTime.now());

			account1.addTransaction(transaction1);
			account1.addTransaction(transaction2);
			account2.addTransaction(transaction3);
			account2.addTransaction(transaction4);
			account3.addTransaction(transaction5);
			account3.addTransaction(transaction6);

			transactionRepository.save(transaction1);
			transactionRepository.save(transaction2);
			transactionRepository.save(transaction3);
			transactionRepository.save(transaction4);
			transactionRepository.save(transaction5);
			transactionRepository.save(transaction6);

			Loan loanId1 = new Loan("Hipotecario", 500000, List.of(12,24,36,48,60));
			Loan loanId2= new Loan("Personal", 100000, List.of(6,12,24));
			Loan loanId3= new Loan("Automotriz", 300000, List.of(6,12,24,36));

			loanRepository.save(loanId1);
			loanRepository.save(loanId2);
			loanRepository.save(loanId3);

			ClientLoan clientLoan1 = new ClientLoan(400000, 60);
			ClientLoan clientLoan2 = new ClientLoan(50000, 12);
			ClientLoan clientLoan3 = new ClientLoan(100000, 24);
			ClientLoan clientLoan4 = new ClientLoan(200000, 36);

			client1.addClientLoan(clientLoan1);
			loanId1.addClientLoan(clientLoan1);

			client1.addClientLoan(clientLoan2);
			loanId2.addClientLoan(clientLoan2);

			client2.addClientLoan(clientLoan3);
			loanId2.addClientLoan(clientLoan3);

			client2.addClientLoan(clientLoan4);
			loanId3.addClientLoan(clientLoan4);

			clientLoanRepository.save(clientLoan1);
			clientLoanRepository.save(clientLoan2);
			clientLoanRepository.save(clientLoan3);
			clientLoanRepository.save(clientLoan4);

			Card card1 = new Card("Melba Morel", CardType.DEBIT, CardColor.GOLD, "4523580043128563", 588, LocalDate.now().plusYears(5), LocalDate.now());
			Card card2 = new Card("Melba Morel", CardType.CREDIT, CardColor.TITANIUM, "5281008612539633", 752, LocalDate.now().plusYears(5), LocalDate.now());
			Card card3 = new Card("Carlos Machado", CardType.CREDIT, CardColor.SILVER, "4500204566387829", 357, LocalDate.now().plusYears(5), LocalDate.now());

			client1.addCard(card1);
			client1.addCard(card2);
			client2.addCard(card3);

			cardRepository.save(card1);
			cardRepository.save(card2);
			cardRepository.save(card3);

		};
	}
}
