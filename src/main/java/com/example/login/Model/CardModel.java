package com.example.login.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "card_db")
@Data
public class CardModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;
    private String name;
    private String price;
}
