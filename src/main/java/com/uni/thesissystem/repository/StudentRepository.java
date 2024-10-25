package com.uni.thesissystem.repository;
import com.uni.thesissystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Repository interface for managing student entities in the database.
 * This interface extends the JpaRepository interface, providing CRUD (Create, Read, Update, Delete) operations
 * for student entities. It allows for easy interaction with the database to perform operations such as
 * saving, retrieving, updating, and deleting student records.
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 */

public interface StudentRepository extends JpaRepository<Student, Long>{

}
