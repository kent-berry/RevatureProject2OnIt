package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "task_table")
@Component("Task")
public class Task {
	
	@Column(name = "name")
	String name = "";
<<<<<<< HEAD
	
=======
>>>>>>> hshallal

}
