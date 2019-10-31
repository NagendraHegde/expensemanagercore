package org.cretal.expense.manager.api.types;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tag")
public class Tag {


    @Column(name = "tag")
    @Id
    private String tag;

    @ManyToMany( cascade = CascadeType.ALL)
    @JoinTable(
            name = "expense_tag",
            joinColumns = { @JoinColumn(name = "tag")},
            inverseJoinColumns = { @JoinColumn( name = "expense_id")}
    )
    private Set<Expense> expenses = new HashSet<>();



    public Tag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tag='" + tag + '\'' +
                '}';
    }
}
