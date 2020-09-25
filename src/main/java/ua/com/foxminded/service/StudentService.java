package ua.com.foxminded.service;

import ua.com.foxminded.domain.CrudOperations;
import ua.com.foxminded.domain.dao.StudentsDao;
import ua.com.foxminded.domain.entity.StudentEntity;

import java.util.List;

public class StudentService implements CrudOperations<StudentEntity, Integer> {
    private final StudentsDao service;

    public StudentService(StudentsDao service) {
        this.service = service;
    }

    @Override
    public StudentEntity create(StudentEntity entity) {
        return service.create(entity);
    }

    @Override
    public StudentEntity findById(Integer id) {
        return service.findById(id);
    }

    @Override
    public List<StudentEntity> readAll() {
        return service.readAll();
    }

    @Override
    public StudentEntity update(StudentEntity entity) {
        return service.update(entity);
    }

    @Override
    public void delete(Integer id) {
        service.delete(id);
    }

    public void addCourse(int idStudent, int idCourse) {
        service.additionCourseToStudent(idStudent, idCourse);
    }

    public List<StudentEntity> searchByCourse(String course){return service.searchStudentByCourse(course);}

    public void deleteCourseFromStudent (int idStudent,int idCourse){ service.deleteCourseFromStudent(idStudent,idCourse); }

}
