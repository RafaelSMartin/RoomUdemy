package com.rsmartin.roomudemy.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.rsmartin.roomudemy.db.entity.Course;

import java.util.List;

@Dao
public interface CourseDAO {

    @Insert
    void insert(Course course);

    @Query("SELECT * FROM course WHERE professorId = :professorId")
    List<Course> findCoursesForProfessor(int professorId);

    @Update
    void updateCourseById(Course course);

    @Delete
    void deleteCourseById(Course course);
}
