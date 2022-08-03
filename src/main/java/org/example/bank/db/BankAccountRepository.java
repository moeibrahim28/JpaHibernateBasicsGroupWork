package org.example.bank.db;

import org.example.bank.model.BankAccount;
import org.example.shared.io.db.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

public class BankAccountRepository implements Repository<BankAccount> {
    @Override
    public BankAccount save(BankAccount bankAccount) {
        return null;
    }

    @Override
    public Optional<BankAccount> findById(Long id) {
        return Optional.empty();
    }

}
