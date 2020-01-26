package expensemanager.core.service.expense;


import expensemanager.core.domain.entity.ExpenseEntity;
import expensemanager.core.domain.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExpenseService {

    @Autowired
    ExpenseRepository repository;

    public List<ExpenseEntity> getAllExpenses() {
        return repository.findAll();
    }

    public Optional<ExpenseEntity> getExpenseEntityById(final UUID id) {
        return repository.findById(id);
    }

    public Optional<ExpenseEntity> createExpenseEntity(final ExpenseEntity expense) {
        Optional<ExpenseEntity> expenseObj = repository.findById(expense.getId());
        if (expenseObj.isPresent()) {
            return Optional.empty();
        }
        repository.save(expense);
        return Optional.of(expense);
    }

    public Optional<ExpenseEntity> updateExpenseEntity(final ExpenseEntity expense) {
        Optional<ExpenseEntity> expenseObj = repository.findById(expense.getId());
        if (!expenseObj.isPresent()) return Optional.empty();

        expenseObj.ifPresent((expenseEntity -> {
            expenseEntity.setAmount(expense.getAmount());
            expenseEntity.setDescription(expense.getDescription());
            repository.save(expenseEntity);
        }));
        return Optional.of(expense);
    }

    public Optional<ExpenseEntity> deleteExpenseById(final UUID id) {
        Optional<ExpenseEntity> expense = repository.findById(id);
        if (!expense.isPresent()) return Optional.empty();
        repository.deleteById(id);
        return expense;
    }

}
