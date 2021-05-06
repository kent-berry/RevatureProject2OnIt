package com.revature.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;



@Component
@Entity
@Table(name= "label_table")
public class Label {

	@Id
	@Column(name = "amount")
	private double amount;
}
