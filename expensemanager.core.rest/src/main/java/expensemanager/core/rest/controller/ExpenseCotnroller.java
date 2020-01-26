package expensemanager.core.rest.controller;


import expensemanager.core.domain.entity.ExpenseEntity;
import expensemanager.core.service.expense.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/expenses")
public class ExpenseCotnroller {

    @Autowired
    ExpenseService expenseService;

    @GetMapping
    public ResponseEntity<List<ExpenseEntity>> getAllExpenses(){
        List<ExpenseEntity> list = expenseService.getAllExpenses();
        return new ResponseEntity<>(list, new HttpHeaders(), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseEntity> getExpenseEntityById(@PathVariable("id")UUID id){
        Optional<ExpenseEntity> expense = expenseService.getExpenseEntityById(id);
        return expense
                .map(expenseEntity -> new ResponseEntity<>(expenseEntity, new HttpHeaders(), OK))
                .orElseGet(() -> new ResponseEntity<>(null, new HttpHeaders(), NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ExpenseEntity> createExpense(@RequestBody ExpenseEntity expense){
        Optional<ExpenseEntity> result =  expenseService.createExpenseEntity(expense);
        return result
                .map(expenseEntity -> new ResponseEntity<>(expenseEntity, new HttpHeaders(), HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(expense, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
