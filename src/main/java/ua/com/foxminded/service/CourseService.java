package ua.com.foxminded.service;

import ua.com.foxminded.config.DBConnection;
import ua.com.foxminded.domain.CrudOperations;
import ua.com.foxminded.domain.dao.CoursesDao;
import ua.com.foxminded.domain.entity.CourseEntity;

import java.util.List;

public class CourseService implements CrudOperations<CourseEntity,Integer>{

    private final CoursesDao service;


    public CourseService(CoursesDao service) {
        this.service = service;
    }

    @Override
    public CourseEntity create(CourseEntity entity) {
        return service.create(entity);
    }

    @Override
    public CourseEntity findBuId(Integer id) {
        return service.findById(id);
    }

    @Override
    public List<CourseEntity> readAll() {
        return service.readAll();
    }

    @Override
    public CourseEntity update(CourseEntity entity) {
        return service.update(entity);
    }

    @Override
    public void delete(Integer entity) {
        service.deleteById(entity);
    }

    public void addCourseToStudent(int idStudent,int idCourse){service.addCourseToStudent(idStudent, idCourse);}

    public void deleteCourseFromStudent(int idStudent,int idCourse){service.deleteCourseFromStudent(idStudent, idCourse);}

}
