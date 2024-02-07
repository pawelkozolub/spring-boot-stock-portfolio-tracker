package com.example.stockportfoliotracker.domain.portfolio;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;

@Entity
@Table(name = "transactions")
@NoArgsConstructor
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private String created;
    @Size(max = 4)
    private String type;
    @Size(min = 3, max = 3)
    private String stock;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private String date;
    @Min(1)
    private long quantity;
    @Column(precision = 10, scale = 2)
    @Min(0)
    private BigDecimal price;
}
