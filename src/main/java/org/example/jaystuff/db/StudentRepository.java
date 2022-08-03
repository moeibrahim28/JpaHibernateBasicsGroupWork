package org.example.jaystuff.db;

import org.example.jaystuff.model.Student;
import org.example.shared.io.db.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

public class StudentRepository implements Repository<Student> {

    @Override
    public Student save(Student student) {
        return null;
    }

    @Override
    public Optional<Student> findById(Long id) {
        return Optional.empty();
    }

}
