package com.example.stockportfoliotracker.domain.portfolio;

import com.example.stockportfoliotracker.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "portfolios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Portfolio {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    @Size(min = 1, max = 20)
    private String name;
    @Size(max = 600)
    @Column(length = 1024)
    private String description;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "portfolio_id")
    private List<Transaction> transactions = new ArrayList<>();
    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.REMOVE)
    private List<Balance> balances = new ArrayList<>();
    @ManyToOne
    private User user;
}
