package org.cretal.expense.manager.api.types;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "expense")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "user_id")
    @ManyToOne(targetEntity = User.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_userId")
    private User user;

    @Column(name = "amount")
    private int amount;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "expenses")
    private Set<Tag> tags = new HashSet<>();

    public Expense(int amount, String description, Set<Tag> tags) {
        this.amount = amount;
        this.description = description;
        this.tags = tags;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void addTag(Set<Tag> tags) {
        if (this.tags == null) {
            this.tags = tags;
        } else {
            this.tags.addAll(tags);
        }
    }

    @Override
    public String toString() {
        return "Expense{" +
                "amount=" + amount +
                ", description='" + description + '\'' +
                ", tags=" + tags +
                '}';
    }
}
